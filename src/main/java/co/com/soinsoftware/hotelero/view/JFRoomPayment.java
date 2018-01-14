package co.com.soinsoftware.hotelero.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import org.apache.commons.lang3.time.DateUtils;

import co.com.soinsoftware.hotelero.util.InvoiceItemNotEditableTableModel;

import com.soinsoftware.hotelero.core.controller.InvoiceController;
import com.soinsoftware.hotelero.core.controller.InvoiceItemController;
import com.soinsoftware.hotelero.core.controller.InvoiceStatusController;
import com.soinsoftware.hotelero.core.controller.RoomStatusController;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceItem;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;
import com.soinsoftware.hotelero.persistence.entity.Room;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;
import com.soinsoftware.hotelero.persistence.entity.User;

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
public class JFRoomPayment extends JDialog {

	private static final long serialVersionUID = -6177006948912730912L;

	private static final String MSG_INVOICE_STATUS_REQUIRED = "Seleccione un estado de cuenta diferente a \"Sin pago\"";

	private static final String MSG_ROOM_REQUIRED = "Seleccione una habitación";

	private final RoomStatus roomStatusEnabled;

	private final InvoiceStatus statusBillToCompany;

	private final JFRoom jfRoom;

	private InvoiceController invoiceController;

	private InvoiceStatusController invoiceStatusController;

	private InvoiceItemController invoiceItemController;

	private RoomStatusController roomStatusController;

	private List<Invoice> invoiceList;

	private List<InvoiceStatus> invoiceStatusList;

	public JFRoomPayment(final JFRoom jfRoom) {
		this.jfRoom = jfRoom;
		this.invoiceController = new InvoiceController();
		invoiceStatusController = new InvoiceStatusController();
		invoiceItemController = new InvoiceItemController();
		roomStatusController = new RoomStatusController();
		this.roomStatusEnabled = roomStatusController.selectEnabled();
		this.statusBillToCompany = invoiceStatusController
				.selectBillToCompany();
		this.initComponents();
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setLocation((int) (screenSize.getWidth() / 2 - 350),
				(int) (screenSize.getHeight() / 2 - 350));
		this.setModal(true);
		this.setInvoiceStatusModel();
		this.jcbAccountState.setEnabled(false);
	}

	public void refresh() {
		this.setRoomModel();
		this.refreshInvoiceData();
	}

	private void refreshInvoiceData() {
		this.jcbAccountState.setSelectedIndex(0);
		this.jtfIdentification.setText("");
		this.jtfName.setText("");
		this.jdcInitialDate.setDate(null);
		this.jdcFinalDate.setDate(null);
		this.jtfTotal.setText("");
		this.refreshTableData();
	}

	private void refreshTableData() {
		final Invoice invoice = this.getInvoiceSelected();
		final List<InvoiceItem> invoiceItemList = (invoice != null) ? invoiceItemController
				.selectByInvoice(invoice) : new ArrayList<>();
		final TableModel model = new InvoiceItemNotEditableTableModel(
				invoiceItemList);
		this.jtbService.setModel(model);
		this.jtbService.setFillsViewportHeight(true);
	}

	private void setRoomModel() {
		this.invoiceList = this.invoiceController.selectNotEnabled();
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Seleccione uno...");
		for (final Invoice invoice : this.invoiceList) {
			final Room room = invoice.getRoom();
			model.addElement(room.getName());
		}
		this.jcbRoom.setModel(model);
	}

	private void setInvoiceStatusModel() {
		final InvoiceStatus statusNoPaid = invoiceStatusController
				.selectNoPaid();
		final InvoiceStatus statusPaid = invoiceStatusController.selectPaid();
		this.invoiceStatusList = new ArrayList<>();
		this.invoiceStatusList.add(statusNoPaid);
		this.invoiceStatusList.add(statusPaid);
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (final InvoiceStatus invoiceStatus : this.invoiceStatusList) {
			model.addElement(invoiceStatus.getName());
		}
		this.jcbAccountState.setModel(model);
	}

	private Invoice getInvoiceSelected() {
		Invoice invoice = null;
		if (this.jcbRoom.getSelectedIndex() > 0) {
			final int index = this.jcbRoom.getSelectedIndex() - 1;
			invoice = this.invoiceList.get(index);
		}
		return invoice;
	}

	private void validateBillToCompany(final Invoice invoice) {
		if (invoice.getCompany() != null) {
			if (!this.invoiceStatusList.contains(this.statusBillToCompany)) {
				this.invoiceStatusList.add(statusBillToCompany);
			}
		} else {
			if (this.invoiceStatusList.contains(this.statusBillToCompany)) {
				this.invoiceStatusList.remove(statusBillToCompany);
			}
		}
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (final InvoiceStatus invoiceStatus : this.invoiceStatusList) {
			model.addElement(invoiceStatus.getName());
		}
		this.jcbAccountState.setModel(model);
	}

	private void calculateTotalValue(final Invoice invoice) {
		final Room room = invoice.getRoom();
		final long numDays = this.calculateDaysToBeBilled(invoice
				.getInitialDate());
		// final long total = invoice.getValue() + room.getValue() * numDays;
		// this.jtfTotal.setText(String.valueOf(total));
	}

	private long calculateDaysToBeBilled(final Date initialDate) {
		final Date finalDate = this.getFinalDate(initialDate, 14);
		final long diff = finalDate.getTime() - initialDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	private Date getFinalDate(final Date initialDate, final int hour) {
		final LocalDate today = LocalDate.now();
		LocalDateTime finalDateTime = null;
		if (DateUtils.isSameDay(initialDate, new Date())) {
			finalDateTime = today.plusDays(1).atTime(LocalTime.of(hour, 0));
		} else {
			finalDateTime = today.atTime(LocalTime.of(hour, 0));
		}
		final ZonedDateTime zdt = finalDateTime.atZone(ZoneId.systemDefault());
		return Date.from(zdt.toInstant());
	}

	private InvoiceStatus getInvoiceStatusSelected() {
		final int index = this.jcbAccountState.getSelectedIndex();
		return this.invoiceStatusList.get(index);
	}

	private boolean validateDataForSave() {
		boolean valid = true;
		final Invoice invoice = this.getInvoiceSelected();
		final InvoiceStatus status = this.getInvoiceStatusSelected();
		if (invoice == null) {
			valid = false;
			ViewUtils.showMessage(this, MSG_ROOM_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (status.getName().equals("Sin pago")) {
			valid = false;
			ViewUtils.showMessage(this, MSG_INVOICE_STATUS_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		}
		return valid;
	}

	private long getTotalValue() {
		final String valStr = this.jtfTotal.getText();
		return Long.parseLong(valStr.replace(".", "").replace(",", ""));
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
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jpTitle = new javax.swing.JPanel();
		jlbTitle = new javax.swing.JLabel();
		jpRoom = new javax.swing.JPanel();
		jlbRoom = new javax.swing.JLabel();
		jcbRoom = new javax.swing.JComboBox<String>();
		jlbIdentification = new javax.swing.JLabel();
		jtfIdentification = new javax.swing.JFormattedTextField();
		jlbName = new javax.swing.JLabel();
		jtfName = new javax.swing.JTextField();
		jlbFinalDate = new javax.swing.JLabel();
		jdcFinalDate = new com.toedter.calendar.JDateChooser();
		jlbInitialDate = new javax.swing.JLabel();
		jdcInitialDate = new com.toedter.calendar.JDateChooser();
		jpService = new javax.swing.JPanel();
		jspService = new javax.swing.JScrollPane();
		jtbService = new javax.swing.JTable();
		jpAccount = new javax.swing.JPanel();
		jlbTotal = new javax.swing.JLabel();
		jtfTotal = new javax.swing.JFormattedTextField();
		jlbAccountState = new javax.swing.JLabel();
		jcbAccountState = new javax.swing.JComboBox<String>();
		jbtSave = new javax.swing.JButton();
		jbtClose = new javax.swing.JButton();
		lbImage = new javax.swing.JLabel();

		setTitle("Hotelero");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/images/melvic.png")));
		setResizable(false);

		jpTitle.setBackground(new java.awt.Color(255, 255, 255));

		jlbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
		jlbTitle.setText("Habitación - Check Out");

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

		jpRoom.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Habitación",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jlbRoom.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbRoom.setText("Habitación:");

		jcbRoom.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jcbRoom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jcbRoomActionPerformed(evt);
			}
		});

		jlbIdentification.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbIdentification.setText("Cedula:");

		jtfIdentification.setEditable(false);
		jtfIdentification.setBackground(new java.awt.Color(255, 255, 255));
		jtfIdentification
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#,##0"))));
		jtfIdentification.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbName.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbName.setText("Nombre:");

		jtfName.setEditable(false);
		jtfName.setBackground(new java.awt.Color(255, 255, 255));
		jtfName.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbFinalDate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbFinalDate.setText("Fecha de salida:");

		jdcFinalDate.setEnabled(false);
		jdcFinalDate.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbInitialDate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbInitialDate.setText("Fecha de llegada:");

		jdcInitialDate.setEnabled(false);
		jdcInitialDate.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		javax.swing.GroupLayout jpRoomLayout = new javax.swing.GroupLayout(
				jpRoom);
		jpRoom.setLayout(jpRoomLayout);
		jpRoomLayout
				.setHorizontalGroup(jpRoomLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpRoomLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpRoomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jpRoomLayout
																		.createSequentialGroup()
																		.addComponent(
																				jlbRoom)
																		.addGap(83,
																				83,
																				83)
																		.addComponent(
																				jlbIdentification))
														.addGroup(
																jpRoomLayout
																		.createSequentialGroup()
																		.addComponent(
																				jcbRoom,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				120,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(27,
																				27,
																				27)
																		.addGroup(
																				jpRoomLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jlbInitialDate)
																						.addComponent(
																								jtfIdentification,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								200,
																								Short.MAX_VALUE)
																						.addComponent(
																								jdcInitialDate,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))))
										.addGap(18, 18, 18)
										.addGroup(
												jpRoomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jlbFinalDate)
														.addComponent(jlbName)
														.addComponent(
																jdcFinalDate,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																201,
																Short.MAX_VALUE)
														.addComponent(jtfName))
										.addContainerGap(29, Short.MAX_VALUE)));
		jpRoomLayout
				.setVerticalGroup(jpRoomLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpRoomLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpRoomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jlbRoom)
														.addComponent(
																jlbIdentification)
														.addComponent(jlbName))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jpRoomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jcbRoom,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jtfIdentification,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jtfName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jpRoomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jlbFinalDate)
														.addComponent(
																jlbInitialDate))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jpRoomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jdcFinalDate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jdcInitialDate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jpService.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Servicios",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jtbService.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jspService.setViewportView(jtbService);

		javax.swing.GroupLayout jpServiceLayout = new javax.swing.GroupLayout(
				jpService);
		jpService.setLayout(jpServiceLayout);
		jpServiceLayout.setHorizontalGroup(jpServiceLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jpServiceLayout
						.createSequentialGroup()
						.addComponent(jspService,
								javax.swing.GroupLayout.PREFERRED_SIZE, 601,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 4, Short.MAX_VALUE)));
		jpServiceLayout.setVerticalGroup(jpServiceLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jpServiceLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jspService,
								javax.swing.GroupLayout.DEFAULT_SIZE, 148,
								Short.MAX_VALUE)));

		jpAccount.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Estado de la cuenta",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jlbTotal.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbTotal.setText("Total:");

		jtfTotal.setEditable(false);
		jtfTotal.setBackground(new java.awt.Color(255, 255, 255));
		jtfTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
				new javax.swing.text.NumberFormatter(
						new java.text.DecimalFormat("#,##0"))));
		jtfTotal.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbAccountState.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbAccountState.setText("Estado:");

		jcbAccountState.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jbtSave.setBackground(new java.awt.Color(16, 135, 221));
		jbtSave.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtSave.setForeground(new java.awt.Color(255, 255, 255));
		jbtSave.setText("Guardar");
		jbtSave.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtSaveActionPerformed(evt);
			}
		});

		jbtClose.setBackground(new java.awt.Color(16, 135, 221));
		jbtClose.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtClose.setForeground(new java.awt.Color(255, 255, 255));
		jbtClose.setText("Cerrar");
		jbtClose.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtClose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtCloseActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpAccountLayout = new javax.swing.GroupLayout(
				jpAccount);
		jpAccount.setLayout(jpAccountLayout);
		jpAccountLayout
				.setHorizontalGroup(jpAccountLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpAccountLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpAccountLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jlbAccountState)
														.addComponent(jlbTotal))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jpAccountLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jtfTotal)
														.addComponent(
																jcbAccountState,
																0, 160,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jpAccountLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jbtSave,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtClose,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		jpAccountLayout
				.setVerticalGroup(jpAccountLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpAccountLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpAccountLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jlbTotal)
														.addComponent(
																jtfTotal,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtSave,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jpAccountLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jlbAccountState)
														.addComponent(
																jcbAccountState,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtClose,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(13, Short.MAX_VALUE)));

		lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/images/soin.png"))); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jpTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(lbImage,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										388,
										javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jpService,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jpRoom,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(0,
																										0,
																										Short.MAX_VALUE))
																				.addComponent(
																						jpAccount,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addContainerGap()))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(jpTitle,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jpRoom,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jpService,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jpAccount,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										33, Short.MAX_VALUE)
								.addComponent(lbImage,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										35,
										javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jcbRoomActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbRoomActionPerformed
		final Invoice invoice = this.getInvoiceSelected();
		this.refreshInvoiceData();
		if (invoice != null) {
			this.jcbAccountState.setEnabled(true);
			final User user = invoice.getUser();
			this.jtfIdentification.setText(String.valueOf(user
					.getIdentification()));
			this.jtfName.setText(user.getName());
			final Date initialDate = invoice.getInitialDate();
			this.jdcInitialDate.setDate(initialDate);
			this.jdcFinalDate.setDate(this.getFinalDate(initialDate, 12));
			this.calculateTotalValue(invoice);
			this.validateBillToCompany(invoice);
			this.jtfTotal.requestFocus();
			this.jcbAccountState.requestFocus();
		} else {
			this.jcbAccountState.setEnabled(false);
		}
	}// GEN-LAST:event_jcbRoomActionPerformed

	private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCloseActionPerformed
		this.setVisible(false);
	}// GEN-LAST:event_jbtCloseActionPerformed

	private void jbtSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtSaveActionPerformed
		if (this.validateDataForSave()) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_SAVE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				final Invoice invoice = this.getInvoiceSelected();
				final InvoiceStatus invoiceStatus = this
						.getInvoiceStatusSelected();
				final Date finalDate = this.jdcFinalDate.getDate();
				final long total = this.getTotalValue();
				invoice.setUpdated(new Date());
				invoice.setRoomStatus(roomStatusEnabled);
				invoice.setInvoiceStatus(invoiceStatus);
				invoice.setFinalDate(finalDate);
				invoice.setValue(total);
				this.invoiceController.save(invoice);
				ViewUtils.showMessage(this, ViewUtils.MSG_SAVED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.jfRoom.refreshRoomData();
				this.refresh();
			}
		}
	}// GEN-LAST:event_jbtSaveActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbtClose;
	private javax.swing.JButton jbtSave;
	private javax.swing.JComboBox<String> jcbAccountState;
	private javax.swing.JComboBox<String> jcbRoom;
	private com.toedter.calendar.JDateChooser jdcFinalDate;
	private com.toedter.calendar.JDateChooser jdcInitialDate;
	private javax.swing.JLabel jlbAccountState;
	private javax.swing.JLabel jlbFinalDate;
	private javax.swing.JLabel jlbIdentification;
	private javax.swing.JLabel jlbInitialDate;
	private javax.swing.JLabel jlbName;
	private javax.swing.JLabel jlbRoom;
	private javax.swing.JLabel jlbTitle;
	private javax.swing.JLabel jlbTotal;
	private javax.swing.JPanel jpAccount;
	private javax.swing.JPanel jpRoom;
	private javax.swing.JPanel jpService;
	private javax.swing.JPanel jpTitle;
	private javax.swing.JScrollPane jspService;
	private javax.swing.JTable jtbService;
	private javax.swing.JFormattedTextField jtfIdentification;
	private javax.swing.JTextField jtfName;
	private javax.swing.JFormattedTextField jtfTotal;
	private javax.swing.JLabel lbImage;
	// End of variables declaration//GEN-END:variables
}
