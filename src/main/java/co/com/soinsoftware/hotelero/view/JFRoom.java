package co.com.soinsoftware.hotelero.view;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import org.apache.commons.lang3.time.DateUtils;

import co.com.soinsoftware.hotelero.controller.CompanyController;
import co.com.soinsoftware.hotelero.controller.InvoiceController;
import co.com.soinsoftware.hotelero.controller.MenuController;
import co.com.soinsoftware.hotelero.controller.UserController;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoicestatus;
import co.com.soinsoftware.hotelero.entity.Roomstatus;
import co.com.soinsoftware.hotelero.entity.User;
import co.com.soinsoftware.hotelero.util.InvoiceBookedTableModel;

import com.toedter.calendar.JTextFieldDateEditor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Carlos Rodriguez
 * @since 17/07/2016
 * @version 1.0
 */
public class JFRoom extends JFrame {

	private static final long serialVersionUID = 6731386919573869650L;

	private static final String MSG_CLIENT_ID_REQUIRED = "Complete el campo cedula";

	private static final String MSG_CLIENT_NAME_REQUIRED = "Complete el campo nombre";

	private static final String MSG_CLIENT_PHONE_REQUIRED = "Complete el campo teléfono";

	private static final String MSG_FINAL_DATE_EQUALS_INITIAL_DATE = "La fecha de salida debe ser mayor a la fecha de llegada";

	private static final String MSG_FINAL_DATE_REQUIRED = "Complete el campo fecha de salida";

	private static final String MSG_INITIAL_DATE_REQUIRED = "Complete el campo fecha de llegada";

	private static final String MSG_INITIAL_DATE_TO_BOOK = "La fecha de llegada debe ser mayor a la fecha actual para efectuar una reserva";

	private static final String MSG_INITIAL_DATE_TO_CHECKIN = "La fecha de llegada debe ser igual a la fecha actual para efectuar el check-in";

	private static final String MSG_ROOM_REQUIRED = "Seleccione una habitación";

	private final CompanyController companyController;

	private final InvoiceController invoiceController;

	private final UserController userController;

	private final JFRoomDetail roomDetail;

	private List<Company> companyList;

	private Set<Invoice> notEnabledSet;

	private int roomName;

	private User user;

	public JFRoom() {
		this.companyController = new CompanyController();
		this.invoiceController = new InvoiceController();
		this.userController = new UserController();
		this.roomDetail = new JFRoomDetail(this, this.invoiceController);
		this.initComponents();
		this.disableJTextFieldDateEditor();
		this.setTextFieldLimits();
		this.refresh();
		this.watchPropertyChangeForJDateChooserControls();
	}

	public void addController(final MenuController controller) {
		final JMenuBar menuBar = new JMBAppMenu(controller);
		this.setJMenuBar(menuBar);
	}

	public void refresh() {
		Calendar calendar = Calendar.getInstance();
		this.jtfIdentification.setText("");
		this.jtfName.setText("");
		this.jtfPhone.setText("");
		this.jdcInitialDate.setDate(calendar.getTime());
		this.setFinalDate(calendar);
		this.jtfCareer.setText("");
		this.jtfSiteFrom.setText("");
		this.jtfSiteTo.setText("");
		this.refreshTableData();
		this.setCompanyModel();
	}

	public void setCompanyModel() {
		this.companyList = this.companyController.selectCompanies();
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Ninguna");
		for (final Company company : this.companyList) {
			model.addElement(company.getName());
		}
		this.jcbCompany.setModel(model);
	}

	public void refreshRoomData() {
		this.refreshNotEnabledSet();
		this.refreshTableData();
	}

	private void setFinalDate(final Calendar calendar) {
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		this.jdcFinalDate.setDate(calendar.getTime());
		refreshNotEnabledSet();
	}

	private void setTextFieldLimits() {
		this.jtfName.setDocument(new JTextFieldLimit(90));
		this.jtfCareer.setDocument(new JTextFieldLimit(45));
		this.jtfSiteFrom.setDocument(new JTextFieldLimit(45));
		this.jtfSiteTo.setDocument(new JTextFieldLimit(45));
		this.jdcInitialDate.setMinSelectableDate(new Date());
		this.jdcFinalDate.setMinSelectableDate(new Date());
	}

	private void disableJTextFieldDateEditor() {
		final JTextFieldDateEditor initialDateEditor = (JTextFieldDateEditor) this.jdcInitialDate
				.getDateEditor();
		initialDateEditor.setEditable(false);
		final JTextFieldDateEditor finalDateEditor = (JTextFieldDateEditor) this.jdcFinalDate
				.getDateEditor();
		finalDateEditor.setEditable(false);
	}

	private void refreshTableData() {
		final List<Invoice> invoiceList = this.invoiceController.selectBooked();
		final TableModel model = new InvoiceBookedTableModel(invoiceList);
		this.jtbReservationList.setModel(model);
		this.jtbReservationList.setFillsViewportHeight(true);
	}

	private void watchPropertyChangeForJDateChooserControls() {
		this.jdcInitialDate.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName())) {
							final Date selectedDate = (Date) e.getNewValue();
							final Calendar calendar = Calendar.getInstance();
							calendar.setTime(selectedDate);
							setFinalDate(calendar);
						}
					}
				});

		this.jdcFinalDate.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName())) {
							if (validateFinalDate()) {
								refreshNotEnabledSet();
							}
						}
					}
				});
	}

	private boolean selectRoom(final JButton button) {
		boolean canSelect = false;
		if (button.getBackground().equals(Color.WHITE)) {
			this.setBackgroundColor(button, Color.GREEN);
			canSelect = true;
		}
		return canSelect;
	}

	private void releaseRoomSelected() {
		if (this.roomName == 101) {
			this.setBackgroundColor(this.jbt101, Color.WHITE);
		} else if (this.roomName == 102) {
			this.setBackgroundColor(this.jbt102, Color.WHITE);
		} else if (this.roomName == 103) {
			this.setBackgroundColor(this.jbt103, Color.WHITE);
		} else if (this.roomName == 104) {
			this.setBackgroundColor(this.jbt104, Color.WHITE);
		} else if (this.roomName == 105) {
			this.setBackgroundColor(this.jbt105, Color.WHITE);
		} else if (this.roomName == 106) {
			this.setBackgroundColor(this.jbt106, Color.WHITE);
		} else if (this.roomName == 107) {
			this.setBackgroundColor(this.jbt107, Color.WHITE);
		} else if (this.roomName == 201) {
			this.setBackgroundColor(this.jbt201, Color.WHITE);
		} else if (this.roomName == 202) {
			this.setBackgroundColor(this.jbt202, Color.WHITE);
		} else if (this.roomName == 203) {
			this.setBackgroundColor(this.jbt203, Color.WHITE);
		} else if (this.roomName == 204) {
			this.setBackgroundColor(this.jbt204, Color.WHITE);
		} else if (this.roomName == 205) {
			this.setBackgroundColor(this.jbt205, Color.WHITE);
		} else if (this.roomName == 206) {
			this.setBackgroundColor(this.jbt206, Color.WHITE);
		} else if (this.roomName == 207) {
			this.setBackgroundColor(this.jbt207, Color.WHITE);
		}
	}

	private void releaseAllRooms() {
		roomName = 0;
		this.setBackgroundColor(this.jbt101, Color.WHITE);
		this.setBackgroundColor(this.jbt102, Color.WHITE);
		this.setBackgroundColor(this.jbt103, Color.WHITE);
		this.setBackgroundColor(this.jbt104, Color.WHITE);
		this.setBackgroundColor(this.jbt105, Color.WHITE);
		this.setBackgroundColor(this.jbt106, Color.WHITE);
		this.setBackgroundColor(this.jbt107, Color.WHITE);
		this.setBackgroundColor(this.jbt201, Color.WHITE);
		this.setBackgroundColor(this.jbt202, Color.WHITE);
		this.setBackgroundColor(this.jbt203, Color.WHITE);
		this.setBackgroundColor(this.jbt204, Color.WHITE);
		this.setBackgroundColor(this.jbt205, Color.WHITE);
		this.setBackgroundColor(this.jbt206, Color.WHITE);
		this.setBackgroundColor(this.jbt207, Color.WHITE);
	}

	private void setBackgroudForNotEnabledRooms() {
		this.releaseAllRooms();
		final Roomstatus statusCheckIn = this.invoiceController
				.selectRoomStatusDisabled();
		if (this.notEnabledSet != null) {
			for (final Invoice invoice : this.notEnabledSet) {
				final String roomName = invoice.getRoom().getName();
				final Roomstatus roomStatus = invoice.getRoomstatus();
				final Color color = (roomStatus.equals(statusCheckIn)) ? Color.RED
						: Color.YELLOW;
				this.setBackgroudForNotEnabledRooms(roomName, color);
			}
		}
	}

	private void setBackgroudForNotEnabledRooms(final String roomName,
			final Color color) {
		if (roomName.equals("101")) {
			this.setBackgroundColor(this.jbt101, color);
		} else if (roomName.equals("102")) {
			this.setBackgroundColor(this.jbt102, color);
		} else if (roomName.equals("103")) {
			this.setBackgroundColor(this.jbt103, color);
		} else if (roomName.equals("104")) {
			this.setBackgroundColor(this.jbt104, color);
		} else if (roomName.equals("105")) {
			this.setBackgroundColor(this.jbt105, color);
		} else if (roomName.equals("106")) {
			this.setBackgroundColor(this.jbt106, color);
		} else if (roomName.equals("107")) {
			this.setBackgroundColor(this.jbt107, color);
		} else if (roomName.equals("201")) {
			this.setBackgroundColor(this.jbt201, color);
		} else if (roomName.equals("202")) {
			this.setBackgroundColor(this.jbt202, color);
		} else if (roomName.equals("203")) {
			this.setBackgroundColor(this.jbt203, color);
		} else if (roomName.equals("204")) {
			this.setBackgroundColor(this.jbt204, color);
		} else if (roomName.equals("205")) {
			this.setBackgroundColor(this.jbt205, color);
		} else if (roomName.equals("206")) {
			this.setBackgroundColor(this.jbt206, color);
		} else if (roomName.equals("207")) {
			this.setBackgroundColor(this.jbt207, color);
		}
	}

	private void setBackgroundColor(final JButton button, final Color color) {
		button.setBackground(color);
	}

	private void showRoomDetail(final String selectedRoomName) {
		if (this.notEnabledSet != null) {
			for (final Invoice invoice : this.notEnabledSet) {
				final String roomName = invoice.getRoom().getName();
				if (selectedRoomName.equals(roomName)) {
					this.roomDetail.refresh(invoice, this.companyList);
					this.roomDetail.setVisible(true);
					break;
				}
			}
		}
	}

	private long getClientIdentification() {
		String identification = this.jtfIdentification.getText();
		identification = (identification.equals("")) ? "0" : identification;
		return Long.parseLong(identification.replace(".", "").replace(",", ""));
	}

	private long getClientPhone() {
		String phone = this.jtfPhone.getText();
		phone = (phone.equals("")) ? "0" : phone;
		return Long.parseLong(phone.replace(".", "").replace(",", ""));
	}

	private boolean validateDataForSave() {
		boolean valid = true;
		final long identification = this.getClientIdentification();
		final String name = this.jtfName.getText();
		final long phone = this.getClientPhone();
		final Date initialDate = this.jdcInitialDate.getDate();
		final Date finalDate = this.jdcFinalDate.getDate();
		if (identification == 0) {
			valid = false;
			ViewUtils.showMessage(this, MSG_CLIENT_ID_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (name.trim().equals("")) {
			valid = false;
			ViewUtils.showMessage(this, MSG_CLIENT_NAME_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (phone == 0) {
			valid = false;
			ViewUtils.showMessage(this, MSG_CLIENT_PHONE_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (initialDate == null) {
			valid = false;
			ViewUtils.showMessage(this, MSG_INITIAL_DATE_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (finalDate == null) {
			valid = false;
			ViewUtils.showMessage(this, MSG_FINAL_DATE_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (this.roomName == 0) {
			valid = false;
			ViewUtils.showMessage(this, MSG_ROOM_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		}
		return valid;
	}

	private boolean validateInitialDate(final boolean isCheckIn,
			final String errorMessage) {
		boolean isValid = false;
		final Date currentDate = new Date();
		final Date initialDate = this.jdcInitialDate.getDate();
		if (DateUtils.isSameDay(currentDate, initialDate)) {
			isValid = (isCheckIn) ? true : false;
		} else {
			isValid = (isCheckIn) ? false : true;
		}
		if (!isValid) {
			ViewUtils.showMessage(this, errorMessage,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		}
		return isValid;
	}

	private boolean validateFinalDate() {
		boolean isValid = true;
		final Date initialDate = this.jdcInitialDate.getDate();
		final Date finalDate = this.jdcFinalDate.getDate();
		if (DateUtils.isSameDay(initialDate, finalDate)
				|| initialDate.after(finalDate)) {
			isValid = false;
		}
		if (!isValid) {
			ViewUtils.showMessage(this, MSG_FINAL_DATE_EQUALS_INITIAL_DATE,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		}
		return isValid;
	}

	private void refreshNotEnabledSet() {
		final Date initialDate = this.jdcInitialDate.getDate();
		final Date finalDate = this.jdcFinalDate.getDate();
		this.notEnabledSet = this.invoiceController.selectNotEnabled(
				initialDate, finalDate);
		this.setBackgroudForNotEnabledRooms();
	}

	private Company getSelectedCompany() {
		Company company = null;
		if (this.jcbCompany.getSelectedIndex() > 0) {
			final int index = this.jcbCompany.getSelectedIndex() - 1;
			company = this.companyList.get(index);
		}
		return company;
	}

	private void saveUserInformation() {
		final long identification = this.getClientIdentification();
		final String name = this.jtfName.getText();
		final long phone = this.getClientPhone();
		final String career = this.jtfCareer.getText();
		final Company company = this.getSelectedCompany();
		this.user = this.userController.saveUser(company, identification, name,
				phone, career);
	}

	private List<Invoice> getInvoiceListFromTable() {
		final TableModel model = this.jtbReservationList.getModel();
		return ((InvoiceBookedTableModel) model).getInvoiceList();
	}

	private boolean hasInvoiceToBeDeleted(final List<Invoice> invoiceList) {
		boolean hasElements = false;
		for (final Invoice invoice : invoiceList) {
			if (invoice.isDelete()) {
				hasElements = true;
				break;
			}
		}
		return hasElements;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jpClient = new javax.swing.JPanel();
		jlbIdentification = new javax.swing.JLabel();
		jtfIdentification = new javax.swing.JFormattedTextField();
		jlbName = new javax.swing.JLabel();
		jtfName = new javax.swing.JTextField();
		jlbPhone = new javax.swing.JLabel();
		jtfPhone = new javax.swing.JFormattedTextField();
		jlbCareer = new javax.swing.JLabel();
		jtfCareer = new javax.swing.JTextField();
		jlbSiteFrom = new javax.swing.JLabel();
		jtfSiteFrom = new javax.swing.JTextField();
		jlbSiteTo = new javax.swing.JLabel();
		jtfSiteTo = new javax.swing.JTextField();
		jlbCompany = new javax.swing.JLabel();
		jlbInitialDate = new javax.swing.JLabel();
		jdcInitialDate = new com.toedter.calendar.JDateChooser();
		jcbCompany = new javax.swing.JComboBox<String>();
		jlbFinalDate = new javax.swing.JLabel();
		jdcFinalDate = new com.toedter.calendar.JDateChooser();
		jpTitle = new javax.swing.JPanel();
		jlbTitle = new javax.swing.JLabel();
		jpFirstFloor = new javax.swing.JPanel();
		jbt101 = new javax.swing.JButton();
		jbt102 = new javax.swing.JButton();
		jbt103 = new javax.swing.JButton();
		jbt104 = new javax.swing.JButton();
		jbt105 = new javax.swing.JButton();
		jbt106 = new javax.swing.JButton();
		jbt107 = new javax.swing.JButton();
		jpSecondFloor = new javax.swing.JPanel();
		jbt201 = new javax.swing.JButton();
		jbt202 = new javax.swing.JButton();
		jbt203 = new javax.swing.JButton();
		jbt204 = new javax.swing.JButton();
		jbt205 = new javax.swing.JButton();
		jbt206 = new javax.swing.JButton();
		jbt207 = new javax.swing.JButton();
		lbImage = new javax.swing.JLabel();
		jpAction = new javax.swing.JPanel();
		jbtBook = new javax.swing.JButton();
		jbtCheckIn = new javax.swing.JButton();
		jbtClean = new javax.swing.JButton();
		jpReservationList = new javax.swing.JPanel();
		jspReservationList = new javax.swing.JScrollPane();
		jtbReservationList = new javax.swing.JTable();
		jbtDeleteInvoice = new javax.swing.JButton();
		jpColorDesc = new javax.swing.JPanel();
		jbtWhite = new javax.swing.JButton();
		jbtGreen = new javax.swing.JButton();
		jbtRed = new javax.swing.JButton();
		jbtYellow = new javax.swing.JButton();
		jlbColorDesc = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hotelero");
		setExtendedState(6);
		setMinimumSize(new java.awt.Dimension(1320, 690));

		jpClient.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Cliente",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jlbIdentification.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbIdentification.setText("Cedula(*):");

		jtfIdentification
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#,##0"))));
		jtfIdentification.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jtfIdentification.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				jtfIdentificationOnFocusLost(evt);
			}
		});

		jlbName.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbName.setText("Nombre(*):");

		jtfName.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbPhone.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbPhone.setText("Teléfono(*):");

		jtfPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
				new javax.swing.text.NumberFormatter(
						new java.text.DecimalFormat("#0"))));
		jtfPhone.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbCareer.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbCareer.setText("Profesión:");

		jtfCareer.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbSiteFrom.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbSiteFrom.setText("Procedencia:");

		jtfSiteFrom.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbSiteTo.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbSiteTo.setText("Destino:");

		jtfSiteTo.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbCompany.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbCompany.setText("Empresa:");

		jlbInitialDate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbInitialDate.setText("Fecha de llegada(*):");

		jdcInitialDate.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jcbCompany.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbFinalDate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbFinalDate.setText("Fecha de salida(*):");

		jdcFinalDate.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		javax.swing.GroupLayout jpClientLayout = new javax.swing.GroupLayout(
				jpClient);
		jpClient.setLayout(jpClientLayout);
		jpClientLayout
				.setHorizontalGroup(jpClientLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpClientLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpClientLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jpClientLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																		.addComponent(
																				jtfCareer,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				200,
																				Short.MAX_VALUE)
																		.addComponent(
																				jlbCareer)
																		.addComponent(
																				jlbIdentification)
																		.addComponent(
																				jtfIdentification,
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addComponent(
																				jlbName)
																		.addComponent(
																				jtfName,
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addComponent(
																				jlbPhone)
																		.addComponent(
																				jtfPhone,
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addComponent(
																				jlbSiteFrom)
																		.addComponent(
																				jtfSiteFrom)
																		.addComponent(
																				jlbSiteTo)
																		.addComponent(
																				jtfSiteTo)
																		.addComponent(
																				jlbCompany)
																		.addComponent(
																				jlbInitialDate)
																		.addComponent(
																				jcbCompany,
																				0,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addComponent(
																jlbFinalDate)
														.addComponent(
																jdcFinalDate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																200,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jdcInitialDate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																200,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(20, Short.MAX_VALUE)));
		jpClientLayout
				.setVerticalGroup(jpClientLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpClientLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jlbIdentification)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfIdentification,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbName)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfName,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbPhone)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfPhone,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbInitialDate)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jdcInitialDate,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbFinalDate)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jdcFinalDate,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbCareer)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfCareer,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbSiteFrom)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfSiteFrom,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbSiteTo)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfSiteTo,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbCompany)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jcbCompany,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(22, Short.MAX_VALUE)));

		jpTitle.setBackground(new java.awt.Color(255, 255, 255));

		jlbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
		jlbTitle.setText("Habitaciones (Check-in, Reservar)");

		javax.swing.GroupLayout jpTitleLayout = new javax.swing.GroupLayout(
				jpTitle);
		jpTitle.setLayout(jpTitleLayout);
		jpTitleLayout.setHorizontalGroup(jpTitleLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jpTitleLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlbTitle)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		jpTitleLayout.setVerticalGroup(jpTitleLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jpTitleLayout.createSequentialGroup().addGap(32, 32, 32)
						.addComponent(jlbTitle)
						.addContainerGap(34, Short.MAX_VALUE)));

		jpFirstFloor.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Primer Piso",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jbt101.setBackground(new java.awt.Color(255, 255, 255));
		jbt101.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt101.setText("101");
		jbt101.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt101ActionPerformed(evt);
			}
		});

		jbt102.setBackground(new java.awt.Color(255, 255, 255));
		jbt102.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt102.setText("102");
		jbt102.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt102ActionPerformed(evt);
			}
		});

		jbt103.setBackground(new java.awt.Color(255, 255, 255));
		jbt103.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt103.setText("103");
		jbt103.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt103ActionPerformed(evt);
			}
		});

		jbt104.setBackground(new java.awt.Color(255, 255, 255));
		jbt104.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt104.setText("104");
		jbt104.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt104ActionPerformed(evt);
			}
		});

		jbt105.setBackground(new java.awt.Color(255, 255, 255));
		jbt105.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt105.setText("105");
		jbt105.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt105ActionPerformed(evt);
			}
		});

		jbt106.setBackground(new java.awt.Color(255, 255, 255));
		jbt106.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt106.setText("106");
		jbt106.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt106ActionPerformed(evt);
			}
		});

		jbt107.setBackground(new java.awt.Color(255, 255, 255));
		jbt107.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt107.setText("107");
		jbt107.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt107ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpFirstFloorLayout = new javax.swing.GroupLayout(
				jpFirstFloor);
		jpFirstFloor.setLayout(jpFirstFloorLayout);
		jpFirstFloorLayout
				.setHorizontalGroup(jpFirstFloorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpFirstFloorLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpFirstFloorLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jpFirstFloorLayout
																		.createSequentialGroup()
																		.addComponent(
																				jbt101,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt102,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt103,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt104,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jpFirstFloorLayout
																		.createSequentialGroup()
																		.addComponent(
																				jbt105,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt106,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt107,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jpFirstFloorLayout
				.setVerticalGroup(jpFirstFloorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpFirstFloorLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpFirstFloorLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jbt101,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt102,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt103,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt104,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jpFirstFloorLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jbt105,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt106,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt107,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jpSecondFloor.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Segundo Piso",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jbt201.setBackground(new java.awt.Color(255, 255, 255));
		jbt201.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt201.setText("201");
		jbt201.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt201ActionPerformed(evt);
			}
		});

		jbt202.setBackground(new java.awt.Color(255, 255, 255));
		jbt202.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt202.setText("202");
		jbt202.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt202ActionPerformed(evt);
			}
		});

		jbt203.setBackground(new java.awt.Color(255, 255, 255));
		jbt203.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt203.setText("203");
		jbt203.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt203ActionPerformed(evt);
			}
		});

		jbt204.setBackground(new java.awt.Color(255, 255, 255));
		jbt204.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt204.setText("204");
		jbt204.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt204ActionPerformed(evt);
			}
		});

		jbt205.setBackground(new java.awt.Color(255, 255, 255));
		jbt205.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt205.setText("205");
		jbt205.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt205ActionPerformed(evt);
			}
		});

		jbt206.setBackground(new java.awt.Color(255, 255, 255));
		jbt206.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt206.setText("206");
		jbt206.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt206ActionPerformed(evt);
			}
		});

		jbt207.setBackground(new java.awt.Color(255, 255, 255));
		jbt207.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbt207.setText("207");
		jbt207.setPreferredSize(new java.awt.Dimension(89, 23));
		jbt207.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbt207ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpSecondFloorLayout = new javax.swing.GroupLayout(
				jpSecondFloor);
		jpSecondFloor.setLayout(jpSecondFloorLayout);
		jpSecondFloorLayout
				.setHorizontalGroup(jpSecondFloorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpSecondFloorLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpSecondFloorLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jpSecondFloorLayout
																		.createSequentialGroup()
																		.addComponent(
																				jbt201,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt202,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt203,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jpSecondFloorLayout
																		.createSequentialGroup()
																		.addComponent(
																				jbt205,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt206,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				100,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jbt207,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jbt204,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												100,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jpSecondFloorLayout
				.setVerticalGroup(jpSecondFloorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpSecondFloorLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpSecondFloorLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jbt201,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt202,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt203,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbt204,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jpSecondFloorLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jpSecondFloorLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jbt205,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				40,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jbt206,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				40,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jbt207,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/images/soin.png"))); // NOI18N

		jpAction.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 0, 11))); // NOI18N

		jbtBook.setBackground(new java.awt.Color(16, 135, 221));
		jbtBook.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtBook.setForeground(new java.awt.Color(255, 255, 255));
		jbtBook.setText("Reservar");
		jbtBook.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtBook.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtBookActionPerformed(evt);
			}
		});

		jbtCheckIn.setBackground(new java.awt.Color(16, 135, 221));
		jbtCheckIn.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtCheckIn.setForeground(new java.awt.Color(255, 255, 255));
		jbtCheckIn.setText("Check-In");
		jbtCheckIn.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtCheckIn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtCheckInActionPerformed(evt);
			}
		});

		jbtClean.setBackground(new java.awt.Color(16, 135, 221));
		jbtClean.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtClean.setForeground(new java.awt.Color(255, 255, 255));
		jbtClean.setText("Limpiar");
		jbtClean.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtClean.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtCleanActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpActionLayout = new javax.swing.GroupLayout(
				jpAction);
		jpAction.setLayout(jpActionLayout);
		jpActionLayout
				.setHorizontalGroup(jpActionLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jpActionLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jbtClean,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jbtBook,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jbtCheckIn,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(23, 23, 23)));
		jpActionLayout
				.setVerticalGroup(jpActionLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpActionLayout
										.createSequentialGroup()
										.addGap(23, 23, 23)
										.addGroup(
												jpActionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jbtBook,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtCheckIn,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtClean,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jpReservationList.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Proximas reservas",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jtbReservationList.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jtbReservationList.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		jspReservationList.setViewportView(jtbReservationList);

		jbtDeleteInvoice.setBackground(new java.awt.Color(16, 135, 221));
		jbtDeleteInvoice.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtDeleteInvoice.setForeground(new java.awt.Color(255, 255, 255));
		jbtDeleteInvoice.setText("Eliminar");
		jbtDeleteInvoice.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtDeleteInvoice.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtDeleteInvoiceActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpReservationListLayout = new javax.swing.GroupLayout(
				jpReservationList);
		jpReservationList.setLayout(jpReservationListLayout);
		jpReservationListLayout.setHorizontalGroup(jpReservationListLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jspReservationList,
						javax.swing.GroupLayout.DEFAULT_SIZE, 546,
						Short.MAX_VALUE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						jpReservationListLayout
								.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jbtDeleteInvoice,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		jpReservationListLayout
				.setVerticalGroup(jpReservationListLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpReservationListLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jspReservationList,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												0, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jbtDeleteInvoice,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		jpColorDesc.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Descripción de colores",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jbtWhite.setBackground(new java.awt.Color(255, 255, 255));
		jbtWhite.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbtWhite.setText("Habitaciones disponibles");

		jbtGreen.setBackground(java.awt.Color.green);
		jbtGreen.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbtGreen.setText("Habitación seleccionada");

		jbtRed.setBackground(java.awt.Color.red);
		jbtRed.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbtRed.setText("Habitaciones ocupadas");

		jbtYellow.setBackground(java.awt.Color.yellow);
		jbtYellow.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jbtYellow.setText("Habitaciones reservadas");

		jlbColorDesc.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbColorDesc
				.setText("Los colores indican el estado de la habitación según la fecha de llegada");

		javax.swing.GroupLayout jpColorDescLayout = new javax.swing.GroupLayout(
				jpColorDesc);
		jpColorDesc.setLayout(jpColorDescLayout);
		jpColorDescLayout
				.setHorizontalGroup(jpColorDescLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpColorDescLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpColorDescLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jpColorDescLayout
																		.createSequentialGroup()
																		.addGroup(
																				jpColorDescLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jbtYellow,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								200,
																								Short.MAX_VALUE)
																						.addComponent(
																								jbtWhite,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jpColorDescLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jbtGreen,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								200,
																								Short.MAX_VALUE)
																						.addComponent(
																								jbtRed,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addComponent(
																jlbColorDesc))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jpColorDescLayout
				.setVerticalGroup(jpColorDescLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpColorDescLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jlbColorDesc)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jpColorDescLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jbtWhite)
														.addComponent(jbtGreen))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jpColorDescLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jbtRed)
														.addComponent(jbtYellow))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jpTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jpClient,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jpColorDesc,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jpFirstFloor,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jpSecondFloor,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jpAction,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jpReservationList,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addGap(18, 18, 18))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(lbImage,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										388,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jpTitle,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jpClient,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jpColorDesc,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jpFirstFloor,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jpSecondFloor,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jpAction,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														jpReservationList,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										37, Short.MAX_VALUE)
								.addComponent(lbImage,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										35,
										javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jbtDeleteInvoiceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtDeleteInvoiceActionPerformed
		final List<Invoice> invoiceList = this.getInvoiceListFromTable();
		if (invoiceList != null && this.hasInvoiceToBeDeleted(invoiceList)) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_DELETE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				final Roomstatus roomStatusEnabled = this.invoiceController
						.selectRoomStatusEnabled();
				final Invoicestatus invoiceStatusDeleted = this.invoiceController
						.selectInvoiceStatusDeleted();
				for (final Invoice invoice : invoiceList) {
					if (invoice.isDelete()) {
						invoice.setEnabled(false);
						invoice.setUpdated(new Date());
						invoice.setRoomstatus(roomStatusEnabled);
						invoice.setInvoicestatus(invoiceStatusDeleted);
						this.invoiceController.saveInvoice(invoice);
					}
				}
				ViewUtils.showMessage(this, ViewUtils.MSG_DELETED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.refreshRoomData();
			}
		} else {
			ViewUtils.showMessage(this, ViewUtils.MSG_UNSELECTED,
					ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
		}
	}// GEN-LAST:event_jbtDeleteInvoiceActionPerformed

	private void jtfIdentificationOnFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jtfIdentificationOnFocusLost
		if (!evt.isTemporary()) {
			final long identification = this.getClientIdentification();
			if (identification > 0) {
				this.user = this.userController.selectUser(identification);
				if (this.user != null) {
					this.jtfName.setText(this.user.getName());
					this.jtfPhone.setText(String.valueOf(this.user.getPhone()));
					if (this.user.getCareer() != null) {
						this.jtfCareer.setText(this.user.getCareer());
					}
					if (this.user.getCompany() != null) {
						this.jcbCompany.setSelectedItem(this.user.getCompany()
								.getName());
					}
				}
			}
		}
	}// GEN-LAST:event_jtfIdentificationOnFocusLost

	private void jbtCleanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCleanActionPerformed
		this.refresh();
	}// GEN-LAST:event_jbtCleanActionPerformed

	private void jbtBookActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtBookActionPerformed
		if (this.validateDataForSave()) {
			if (this.validateInitialDate(false, MSG_INITIAL_DATE_TO_BOOK)
					&& this.validateFinalDate()) {
				final int confirmation = ViewUtils.showConfirmDialog(this,
						ViewUtils.MSG_SAVE_QUESTION, ViewUtils.TITLE_SAVED);
				if (confirmation == JOptionPane.OK_OPTION) {
					this.saveUserInformation();
					final Company company = this.getSelectedCompany();
					final Date initialDate = this.jdcInitialDate.getDate();
					final Date finalDate = this.jdcFinalDate.getDate();
					final String siteFrom = this.jtfSiteFrom.getText();
					final String siteTo = this.jtfSiteTo.getText();
					this.invoiceController.saveInvoiceBooking(this.user,
							String.valueOf(this.roomName), initialDate,
							finalDate, siteFrom, siteTo, company);
					ViewUtils.showMessage(this, ViewUtils.MSG_SAVED,
							ViewUtils.TITLE_SAVED,
							JOptionPane.INFORMATION_MESSAGE);
					this.refresh();
				}
			}
		}
	}// GEN-LAST:event_jbtBookActionPerformed

	private void jbtCheckInActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCheckInActionPerformed
		if (this.validateDataForSave()) {
			if (this.validateInitialDate(true, MSG_INITIAL_DATE_TO_CHECKIN)
					&& this.validateFinalDate()) {
				final int confirmation = ViewUtils.showConfirmDialog(this,
						ViewUtils.MSG_SAVE_QUESTION, ViewUtils.TITLE_SAVED);
				if (confirmation == JOptionPane.OK_OPTION) {
					this.saveUserInformation();
					final Company company = this.getSelectedCompany();
					final Date initialDate = this.jdcInitialDate.getDate();
					final Date finalDate = this.jdcFinalDate.getDate();
					final String siteFrom = this.jtfSiteFrom.getText();
					final String siteTo = this.jtfSiteTo.getText();
					this.invoiceController.saveInvoiceCheckIn(this.user,
							String.valueOf(this.roomName), initialDate,
							finalDate, siteFrom, siteTo, company);
					ViewUtils.showMessage(this, ViewUtils.MSG_SAVED,
							ViewUtils.TITLE_SAVED,
							JOptionPane.INFORMATION_MESSAGE);
					this.refresh();
				}
			}
		}
	}// GEN-LAST:event_jbtCheckInActionPerformed

	private void jbt101ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt101ActionPerformed
		if (this.selectRoom(this.jbt101)) {
			this.releaseRoomSelected();
			this.roomName = 101;
		} else {
			this.showRoomDetail("101");
		}
	}// GEN-LAST:event_jbt101ActionPerformed

	private void jbt102ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt102ActionPerformed
		if (this.selectRoom(this.jbt102)) {
			this.releaseRoomSelected();
			this.roomName = 102;
		} else {
			this.showRoomDetail("102");
		}
	}// GEN-LAST:event_jbt102ActionPerformed

	private void jbt103ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt103ActionPerformed
		if (this.selectRoom(this.jbt103)) {
			this.releaseRoomSelected();
			this.roomName = 103;
		} else {
			this.showRoomDetail("103");
		}
	}// GEN-LAST:event_jbt103ActionPerformed

	private void jbt104ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt104ActionPerformed
		if (this.selectRoom(this.jbt104)) {
			this.releaseRoomSelected();
			this.roomName = 104;
		} else {
			this.showRoomDetail("104");
		}
	}// GEN-LAST:event_jbt104ActionPerformed

	private void jbt105ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt105ActionPerformed
		if (this.selectRoom(this.jbt105)) {
			this.releaseRoomSelected();
			this.roomName = 105;
		} else {
			this.showRoomDetail("105");
		}
	}// GEN-LAST:event_jbt105ActionPerformed

	private void jbt106ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt106ActionPerformed
		if (this.selectRoom(this.jbt106)) {
			this.releaseRoomSelected();
			this.roomName = 106;
		} else {
			this.showRoomDetail("106");
		}
	}// GEN-LAST:event_jbt106ActionPerformed

	private void jbt107ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt107ActionPerformed
		if (this.selectRoom(this.jbt107)) {
			this.releaseRoomSelected();
			this.roomName = 107;
		} else {
			this.showRoomDetail("107");
		}
	}// GEN-LAST:event_jbt107ActionPerformed

	private void jbt201ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt201ActionPerformed
		if (this.selectRoom(this.jbt201)) {
			this.releaseRoomSelected();
			this.roomName = 201;
		} else {
			this.showRoomDetail("201");
		}
	}// GEN-LAST:event_jbt201ActionPerformed

	private void jbt202ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt202ActionPerformed
		if (this.selectRoom(this.jbt202)) {
			this.releaseRoomSelected();
			this.roomName = 202;
		} else {
			this.showRoomDetail("202");
		}
	}// GEN-LAST:event_jbt202ActionPerformed

	private void jbt203ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt203ActionPerformed
		if (this.selectRoom(this.jbt203)) {
			this.releaseRoomSelected();
			this.roomName = 203;
		} else {
			this.showRoomDetail("203");
		}
	}// GEN-LAST:event_jbt203ActionPerformed

	private void jbt204ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt204ActionPerformed
		if (this.selectRoom(this.jbt204)) {
			this.releaseRoomSelected();
			this.roomName = 204;
		} else {
			this.showRoomDetail("204");
		}
	}// GEN-LAST:event_jbt204ActionPerformed

	private void jbt205ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt205ActionPerformed
		if (this.selectRoom(this.jbt205)) {
			this.releaseRoomSelected();
			this.roomName = 205;
		} else {
			this.showRoomDetail("205");
		}
	}// GEN-LAST:event_jbt205ActionPerformed

	private void jbt206ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt206ActionPerformed
		if (this.selectRoom(this.jbt206)) {
			this.releaseRoomSelected();
			this.roomName = 206;
		} else {
			this.showRoomDetail("206");
		}
	}// GEN-LAST:event_jbt206ActionPerformed

	private void jbt207ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbt207ActionPerformed
		if (this.selectRoom(this.jbt207)) {
			this.releaseRoomSelected();
			this.roomName = 207;
		} else {
			this.showRoomDetail("207");
		}
	}// GEN-LAST:event_jbt207ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbt101;
	private javax.swing.JButton jbt102;
	private javax.swing.JButton jbt103;
	private javax.swing.JButton jbt104;
	private javax.swing.JButton jbt105;
	private javax.swing.JButton jbt106;
	private javax.swing.JButton jbt107;
	private javax.swing.JButton jbt201;
	private javax.swing.JButton jbt202;
	private javax.swing.JButton jbt203;
	private javax.swing.JButton jbt204;
	private javax.swing.JButton jbt205;
	private javax.swing.JButton jbt206;
	private javax.swing.JButton jbt207;
	private javax.swing.JButton jbtBook;
	private javax.swing.JButton jbtCheckIn;
	private javax.swing.JButton jbtClean;
	private javax.swing.JButton jbtDeleteInvoice;
	private javax.swing.JButton jbtGreen;
	private javax.swing.JButton jbtRed;
	private javax.swing.JButton jbtWhite;
	private javax.swing.JButton jbtYellow;
	private javax.swing.JComboBox<String> jcbCompany;
	private com.toedter.calendar.JDateChooser jdcFinalDate;
	private com.toedter.calendar.JDateChooser jdcInitialDate;
	private javax.swing.JLabel jlbCareer;
	private javax.swing.JLabel jlbColorDesc;
	private javax.swing.JLabel jlbCompany;
	private javax.swing.JLabel jlbFinalDate;
	private javax.swing.JLabel jlbIdentification;
	private javax.swing.JLabel jlbInitialDate;
	private javax.swing.JLabel jlbName;
	private javax.swing.JLabel jlbPhone;
	private javax.swing.JLabel jlbSiteFrom;
	private javax.swing.JLabel jlbSiteTo;
	private javax.swing.JLabel jlbTitle;
	private javax.swing.JPanel jpAction;
	private javax.swing.JPanel jpClient;
	private javax.swing.JPanel jpColorDesc;
	private javax.swing.JPanel jpFirstFloor;
	private javax.swing.JPanel jpReservationList;
	private javax.swing.JPanel jpSecondFloor;
	private javax.swing.JPanel jpTitle;
	private javax.swing.JScrollPane jspReservationList;
	private javax.swing.JTable jtbReservationList;
	private javax.swing.JTextField jtfCareer;
	private javax.swing.JFormattedTextField jtfIdentification;
	private javax.swing.JTextField jtfName;
	private javax.swing.JFormattedTextField jtfPhone;
	private javax.swing.JTextField jtfSiteFrom;
	private javax.swing.JTextField jtfSiteTo;
	private javax.swing.JLabel lbImage;
	// End of variables declaration//GEN-END:variables
}
