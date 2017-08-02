package co.com.soinsoftware.hotelero.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import org.apache.commons.lang3.time.DateUtils;

import co.com.soinsoftware.hotelero.controller.InvoiceController;
import co.com.soinsoftware.hotelero.controller.ServiceController;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoiceitem;
import co.com.soinsoftware.hotelero.entity.Room;
import co.com.soinsoftware.hotelero.entity.Service;
import co.com.soinsoftware.hotelero.entity.ServiceType;
import co.com.soinsoftware.hotelero.entity.User;
import co.com.soinsoftware.hotelero.util.InvoiceItemTableModel;

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
public class JFRoomService extends JDialog {

	private static final long serialVersionUID = -1408671591230972102L;

	private static final String MSG_DATE_REQUIRED = "Complete el campo fecha del servicio";

	private static final String MSG_ROOM_REQUIRED = "Seleccione una habitación";

	private static final String MSG_QUANTITY_EQUALS_TO_ZERO = "El campo cantidad debe ser mayor a 0";

	private static final String MSG_SERVICE_REQUIRED = "Seleccione un servicio";

	private static final String MSG_SERVICE_CATEGORY_REQUIRED = "Seleccione un tipo de servicio";

	private static final String MSG_VALUE_EQUALS_TO_ZERO_REQUIRED = "El campo precio debe ser mayor a 0";

	private InvoiceController invoiceController;

	private ServiceController serviceController;

	private List<Invoice> invoiceList;

	private List<ServiceType> serviceTypeList;

	private List<Service> serviceList;

	public JFRoomService() {
		try {
			this.invoiceController = new InvoiceController();
			this.serviceController = new ServiceController();
		} catch (final IOException e) {
			e.printStackTrace();
			ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_DATABASE_CONNECTION_ERROR, ViewUtils.TITLE_DATABASE_ERROR);
			System.exit(0);
		}
		this.initComponents();
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setLocation((int) (screenSize.getWidth() / 2 - 350),
				(int) (screenSize.getHeight() / 2 - 350));
		this.setModal(true);
	}

	public void refresh() {
		this.setRoomModel();
		this.jtfIdentification.setText("");
		this.jtfName.setText("");
		this.setEnabledNewServiceFields(false);
		this.refreshService();
	}

	private void refreshService() {
		this.jdcInitialDate.setDate(null);
		this.setServiceTypeModel();
		this.setServiceModel(null);
		this.jtfServiceQuantity.setText("");
		this.jtfServiceValue.setText("");
		this.refreshTableData();
	}

	private void refreshTableData() {
		final Invoice invoice = this.getInvoiceSelected();
		final List<Invoiceitem> invoiceItemList = (invoice != null) ? this.invoiceController
				.selectInvoiceItem(invoice) : new ArrayList<>();
		final TableModel model = new InvoiceItemTableModel(invoiceItemList);
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

	private void setServiceTypeModel() {
		this.serviceTypeList = this.serviceController.selectServiceTypes();
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Seleccione uno...");
		for (final ServiceType serviceType : this.serviceTypeList) {
			model.addElement(serviceType.getName());
		}
		this.jcbServiceCategory.setModel(model);
	}

	private void setServiceModel(final ServiceType serviceType) {
		this.serviceList = this.serviceController.selectServices(serviceType);
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Seleccione uno...");
		for (final Service service : this.serviceList) {
			if (service.isEnabled()) {
				model.addElement(service.getName());
			}
		}
		this.jcbService.setModel(model);
	}

	private Invoice getInvoiceSelected() {
		Invoice invoice = null;
		if (this.jcbRoom.getSelectedIndex() > 0) {
			final int index = this.jcbRoom.getSelectedIndex() - 1;
			invoice = this.invoiceList.get(index);
		}
		return invoice;
	}

	private ServiceType getServiceTypeSelected() {
		ServiceType serviceType = null;
		if (this.jcbServiceCategory.getSelectedIndex() > 0) {
			final int index = this.jcbServiceCategory.getSelectedIndex() - 1;
			serviceType = this.serviceTypeList.get(index);
		}
		return serviceType;
	}

	private Service getServiceSelected() {
		Service service = null;
		if (this.jcbService.getSelectedIndex() > 0) {
			final int index = this.jcbService.getSelectedIndex() - 1;
			service = this.serviceList.get(index);
		}
		return service;
	}

	private void setEnabledNewServiceFields(final boolean enabled) {
		this.jdcInitialDate.setEnabled(enabled);
		this.jcbServiceCategory.setEnabled(enabled);
		this.jcbService.setEnabled(enabled);
		this.jtfServiceQuantity.setEnabled(enabled);
		this.jtfServiceValue.setEnabled(enabled);
	}

	private long getServiceValue() {
		String valStr = this.jtfServiceValue.getText();
		if (valStr.equals("")) {
			valStr = "0";
		}
		return Long.parseLong(valStr.replace(".", "").replace(",", ""));
	}

	private int getServiceQuantity() {
		String quantityStr = this.jtfServiceQuantity.getText();
		if (quantityStr.equals("")) {
			quantityStr = "0";
		}
		return Integer.parseInt(quantityStr.replace(".", "").replace(",", ""));
	}

	private boolean validateDataForSave() {
		boolean valid = true;
		final Invoice invoice = this.getInvoiceSelected();
		final Date invoiceItemDate = this.jdcInitialDate.getDate();
		final ServiceType serviceType = this.getServiceTypeSelected();
		final Service service = this.getServiceSelected();
		final int quantity = this.getServiceQuantity();
		final long value = this.getServiceValue();
		if (invoice == null) {
			valid = false;
			ViewUtils.showMessage(this, MSG_ROOM_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (invoiceItemDate == null) {
			valid = false;
			ViewUtils.showMessage(this, MSG_DATE_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (serviceType == null) {
			valid = false;
			ViewUtils.showMessage(this, MSG_SERVICE_CATEGORY_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (service == null) {
			valid = false;
			ViewUtils.showMessage(this, MSG_SERVICE_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (quantity == 0) {
			valid = false;
			ViewUtils.showMessage(this, MSG_QUANTITY_EQUALS_TO_ZERO,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (value == 0) {
			valid = false;
			ViewUtils.showMessage(this, MSG_VALUE_EQUALS_TO_ZERO_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		}
		return valid;
	}

	private List<Invoiceitem> getInvoiceItemListFromTable() {
		final TableModel model = this.jtbService.getModel();
		return ((InvoiceItemTableModel) model).getInvoiceItemList();
	}

	private boolean hasServiceToBeDeleted(
			final List<Invoiceitem> invoiceItemList) {
		boolean hasElements = false;
		for (final Invoiceitem invoiceItem : invoiceItemList) {
			if (invoiceItem.isDelete()) {
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
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jpTitle = new javax.swing.JPanel();
		jlbTitle = new javax.swing.JLabel();
		jpService = new javax.swing.JPanel();
		jlbRoom = new javax.swing.JLabel();
		jcbRoom = new javax.swing.JComboBox<String>();
		jlbIdentification = new javax.swing.JLabel();
		jtfIdentification = new javax.swing.JFormattedTextField();
		jlbName = new javax.swing.JLabel();
		jtfName = new javax.swing.JTextField();
		jspService = new javax.swing.JScrollPane();
		jtbService = new javax.swing.JTable();
		jpCreateService = new javax.swing.JPanel();
		jbtAddService = new javax.swing.JButton();
		jlbServicelDate = new javax.swing.JLabel();
		jdcInitialDate = new com.toedter.calendar.JDateChooser();
		jlbServiceCategory = new javax.swing.JLabel();
		jcbServiceCategory = new javax.swing.JComboBox<String>();
		jlbServiceValue = new javax.swing.JLabel();
		jtfServiceValue = new javax.swing.JFormattedTextField();
		jcbService = new javax.swing.JComboBox<String>();
		jlbService = new javax.swing.JLabel();
		jlbServiceQuantity = new javax.swing.JLabel();
		jtfServiceQuantity = new javax.swing.JFormattedTextField();
		jbtDeleteService = new javax.swing.JButton();
		jpAction = new javax.swing.JPanel();
		jbtClose = new javax.swing.JButton();
		lbImage = new javax.swing.JLabel();

		setTitle("Hotelero");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/images/melvic.png")));
		setName("jfRoomService"); // NOI18N
		setResizable(false);

		jpTitle.setBackground(new java.awt.Color(255, 255, 255));

		jlbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
		jlbTitle.setText("Servicio a la habitación");

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

		jpService.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Servicio",
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

		jtbService.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jspService.setViewportView(jtbService);
		if (jtbService.getColumnModel().getColumnCount() > 0) {
			jtbService.getColumnModel().getColumn(0).setResizable(false);
			jtbService.getColumnModel().getColumn(1).setResizable(false);
			jtbService.getColumnModel().getColumn(2).setResizable(false);
			jtbService.getColumnModel().getColumn(3).setResizable(false);
		}

		jpCreateService.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		jbtAddService.setBackground(new java.awt.Color(16, 135, 221));
		jbtAddService.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtAddService.setForeground(new java.awt.Color(255, 255, 255));
		jbtAddService.setText("Agregar");
		jbtAddService.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtAddService.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtAddServiceActionPerformed(evt);
			}
		});

		jlbServicelDate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbServicelDate.setText("Fecha:");

		jdcInitialDate.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbServiceCategory.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbServiceCategory.setText("Tipo de Consumo:");

		jcbServiceCategory.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jcbServiceCategory
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jcbServiceCategoryActionPerformed(evt);
					}
				});

		jlbServiceValue.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbServiceValue.setText("Precio:");

		jtfServiceValue.setEditable(false);
		jtfServiceValue.setBackground(new java.awt.Color(255, 255, 255));
		jtfServiceValue
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#,##0"))));
		jtfServiceValue.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jcbService.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jcbService.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jcbServiceActionPerformed(evt);
			}
		});

		jlbService.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbService.setText("Servicio:");

		jlbServiceQuantity.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbServiceQuantity.setText("Cantidad:");

		jtfServiceQuantity
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#,##0"))));
		jtfServiceQuantity.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		javax.swing.GroupLayout jpCreateServiceLayout = new javax.swing.GroupLayout(
				jpCreateService);
		jpCreateService.setLayout(jpCreateServiceLayout);
		jpCreateServiceLayout
				.setHorizontalGroup(jpCreateServiceLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpCreateServiceLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpCreateServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jdcInitialDate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																130,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jlbServicelDate))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jpCreateServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jcbServiceCategory,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																130,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jlbServiceCategory)
														.addComponent(
																jlbService)
														.addComponent(
																jcbService,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																130,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jpCreateServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jpCreateServiceLayout
																		.createSequentialGroup()
																		.addGroup(
																				jpCreateServiceLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jlbServiceValue)
																						.addComponent(
																								jtfServiceValue,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								120,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				94,
																				Short.MAX_VALUE)
																		.addComponent(
																				jbtAddService,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jpCreateServiceLayout
																		.createSequentialGroup()
																		.addGroup(
																				jpCreateServiceLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jlbServiceQuantity)
																						.addComponent(
																								jtfServiceQuantity,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								120,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jpCreateServiceLayout
				.setVerticalGroup(jpCreateServiceLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpCreateServiceLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpCreateServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jlbServicelDate)
														.addComponent(
																jlbServiceCategory)
														.addComponent(
																jlbServiceQuantity))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jpCreateServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jdcInitialDate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jpCreateServiceLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jcbServiceCategory,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jtfServiceQuantity,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(
												jpCreateServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jpCreateServiceLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				24,
																				Short.MAX_VALUE)
																		.addGroup(
																				jpCreateServiceLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jbtAddService,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jcbService,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jtfServiceValue,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addContainerGap())
														.addGroup(
																jpCreateServiceLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jpCreateServiceLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jlbServiceValue)
																						.addComponent(
																								jlbService))
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))));

		jbtDeleteService.setBackground(new java.awt.Color(16, 135, 221));
		jbtDeleteService.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtDeleteService.setForeground(new java.awt.Color(255, 255, 255));
		jbtDeleteService.setText("Eliminar");
		jbtDeleteService.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtDeleteService.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtDeleteServiceActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpServiceLayout = new javax.swing.GroupLayout(
				jpService);
		jpService.setLayout(jpServiceLayout);
		jpServiceLayout
				.setHorizontalGroup(jpServiceLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpServiceLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jpServiceLayout
																		.createSequentialGroup()
																		.addGroup(
																				jpServiceLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jpServiceLayout
																										.createSequentialGroup()
																										.addComponent(
																												jcbRoom,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												120,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(27,
																												27,
																												27)
																										.addComponent(
																												jtfIdentification,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												200,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jpServiceLayout
																										.createSequentialGroup()
																										.addComponent(
																												jlbRoom)
																										.addGap(83,
																												83,
																												83)
																										.addComponent(
																												jlbIdentification)))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jpServiceLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jlbName)
																						.addComponent(
																								jtfName,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								220,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																jpCreateService,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jspService))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jpServiceLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jbtDeleteService,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(20, 20, 20)));
		jpServiceLayout
				.setVerticalGroup(jpServiceLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpServiceLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpServiceLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jlbRoom)
														.addComponent(
																jlbIdentification)
														.addComponent(jlbName))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jpServiceLayout
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
										.addGap(18, 18, 18)
										.addComponent(
												jpCreateService,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jspService,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												114,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jbtDeleteService,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jpAction.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 0, 11))); // NOI18N

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

		javax.swing.GroupLayout jpActionLayout = new javax.swing.GroupLayout(
				jpAction);
		jpAction.setLayout(jpActionLayout);
		jpActionLayout.setHorizontalGroup(jpActionLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jpActionLayout
						.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jbtClose,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(22, 22, 22)));
		jpActionLayout.setVerticalGroup(jpActionLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jpActionLayout
						.createSequentialGroup()
						.addGap(23, 23, 23)
						.addComponent(jbtClose,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

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
												.addComponent(
														jpService,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jpAction,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));
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
								.addComponent(jpService,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jpAction,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lbImage,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										35,
										javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jcbRoomActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbRoomActionPerformed
		final Invoice invoice = this.getInvoiceSelected();
		this.refreshService();
		if (invoice != null) {
			this.setEnabledNewServiceFields(true);
			final User user = invoice.getUser();
			this.jtfIdentification.setText(String.valueOf(user
					.getIdentification()));
			this.jtfName.setText(user.getName());
			this.jdcInitialDate.setMinSelectableDate(invoice.getInitialDate());
			final Date finalDate = invoice.getFinalDate();
			final Date currentDate = new Date();
			if (!DateUtils.isSameDay(currentDate, finalDate)
					&& currentDate.after(finalDate)) {
				this.jdcInitialDate.setMaxSelectableDate(currentDate);
			} else {
				this.jdcInitialDate
						.setMaxSelectableDate(invoice.getFinalDate());
			}
		} else {
			this.setEnabledNewServiceFields(false);
			this.jtfIdentification.setText("");
			this.jtfName.setText("");
		}
	}// GEN-LAST:event_jcbRoomActionPerformed

	private void jcbServiceCategoryActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbServiceCategoryActionPerformed
		final ServiceType serviceType = this.getServiceTypeSelected();
		this.setServiceModel(serviceType);
		this.jtfServiceValue.setText("");
		this.jtfServiceQuantity.setText("");
	}// GEN-LAST:event_jcbServiceCategoryActionPerformed

	private void jcbServiceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbServiceActionPerformed
		final Service service = this.getServiceSelected();
		if (service != null) {
			this.jtfServiceValue.setText(String.valueOf(service.getValue()));
			if (service.getValue() == 0) {
				this.jtfServiceValue.setEditable(true);
			} else {
				this.jtfServiceValue.setEditable(false);
			}
			this.jtfServiceValue.requestFocus();
			this.jtfServiceQuantity.requestFocus();
		}
	}// GEN-LAST:event_jcbServiceActionPerformed

	private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCloseActionPerformed
		this.setVisible(false);
	}// GEN-LAST:event_jbtCloseActionPerformed

	private void jbtAddServiceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtAddServiceActionPerformed
		if (this.validateDataForSave()) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_SAVE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				final Invoice invoice = this.getInvoiceSelected();
				final Date invoiceItemDate = this.jdcInitialDate.getDate();
				final Service service = this.getServiceSelected();
				final int quantity = this.getServiceQuantity();
				final long unitValue = this.getServiceValue();
				final long value = quantity * unitValue;
				this.invoiceController.saveInvoiceItem(invoice, service,
						quantity, unitValue, value, invoiceItemDate);
				final long invoiceValue = value + invoice.getValue();
				invoice.setValue(invoiceValue);
				invoice.setUpdated(new Date());
				this.invoiceController.saveInvoice(invoice);
				ViewUtils.showMessage(this, ViewUtils.MSG_SAVED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.refreshService();
			}
		}
	}// GEN-LAST:event_jbtAddServiceActionPerformed

	private void jbtDeleteServiceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtDeleteServiceActionPerformed
		final Invoice invoice = this.getInvoiceSelected();
		if (invoice == null) {
			ViewUtils.showMessage(this, MSG_ROOM_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else {
			final List<Invoiceitem> invoiceItemList = this
					.getInvoiceItemListFromTable();
			if (invoiceItemList != null
					&& this.hasServiceToBeDeleted(invoiceItemList)) {
				final int confirmation = ViewUtils.showConfirmDialog(this,
						ViewUtils.MSG_DELETE_QUESTION, ViewUtils.TITLE_SAVED);
				if (confirmation == JOptionPane.OK_OPTION) {
					long value = 0;
					for (final Invoiceitem invoiceItem : invoiceItemList) {
						if (invoiceItem.isDelete()) {
							invoiceItem.setEnabled(false);
							invoiceItem.setUpdated(new Date());
							this.invoiceController.saveInvoiceItem(invoiceItem);
							value += invoiceItem.getValue();
						}
					}
					final long invoiceValue = invoice.getValue() - value;
					invoice.setValue(invoiceValue);
					invoice.setUpdated(new Date());
					this.invoiceController.saveInvoice(invoice);
					ViewUtils.showMessage(this, ViewUtils.MSG_DELETED,
							ViewUtils.TITLE_SAVED,
							JOptionPane.INFORMATION_MESSAGE);
					this.refreshService();
				}
			} else {
				ViewUtils.showMessage(this, ViewUtils.MSG_UNSELECTED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}// GEN-LAST:event_jbtDeleteServiceActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbtAddService;
	private javax.swing.JButton jbtClose;
	private javax.swing.JButton jbtDeleteService;
	private javax.swing.JComboBox<String> jcbRoom;
	private javax.swing.JComboBox<String> jcbService;
	private javax.swing.JComboBox<String> jcbServiceCategory;
	private com.toedter.calendar.JDateChooser jdcInitialDate;
	private javax.swing.JLabel jlbIdentification;
	private javax.swing.JLabel jlbName;
	private javax.swing.JLabel jlbRoom;
	private javax.swing.JLabel jlbService;
	private javax.swing.JLabel jlbServiceCategory;
	private javax.swing.JLabel jlbServiceQuantity;
	private javax.swing.JLabel jlbServiceValue;
	private javax.swing.JLabel jlbServicelDate;
	private javax.swing.JLabel jlbTitle;
	private javax.swing.JPanel jpAction;
	private javax.swing.JPanel jpCreateService;
	private javax.swing.JPanel jpService;
	private javax.swing.JPanel jpTitle;
	private javax.swing.JScrollPane jspService;
	private javax.swing.JTable jtbService;
	private javax.swing.JFormattedTextField jtfIdentification;
	private javax.swing.JTextField jtfName;
	private javax.swing.JFormattedTextField jtfServiceQuantity;
	private javax.swing.JFormattedTextField jtfServiceValue;
	private javax.swing.JLabel lbImage;
	// End of variables declaration//GEN-END:variables
}
