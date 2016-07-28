/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.hotelero.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.time.DateUtils;

import co.com.soinsoftware.hotelero.controller.InvoiceController;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoicestatus;
import co.com.soinsoftware.hotelero.entity.Roomstatus;
import co.com.soinsoftware.hotelero.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 28/07/2016
 * @version 1.0
 */
public class JFRoomDetail extends JDialog {

	private static final long serialVersionUID = 8056285646612411090L;

	private final JFRoom parent;

	private final InvoiceController invoiceController;

	private final Roomstatus roomStatusBooked;

	private Invoice invoice;

	public JFRoomDetail(final JFRoom parent,
			final InvoiceController invoiceController) {
		this.initComponents();
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setLocation((int) (screenSize.getWidth() / 2 - 350),
				(int) (screenSize.getHeight() / 2 - 380));
		this.setModal(true);
		this.parent = parent;
		this.invoiceController = invoiceController;
		this.roomStatusBooked = this.invoiceController.selectRoomStatusBooked();
	}

	public void refresh(final Invoice invoice, final List<Company> companyList) {
		this.invoice = invoice;
		this.setCompanyModel(companyList);
		this.fillUserData(invoice.getUser());
		this.fillInvoiceData(invoice);
		this.showButtonsByRoomStatus(invoice.getRoomstatus());
		final String roomName = "Habitación " + invoice.getRoom().getName();
		this.setTitle(roomName);
		this.jlbTitle.setText(roomName);
	}

	private void fillUserData(final User user) {
		this.jtfIdentification
				.setText(String.valueOf(user.getIdentification()));
		this.jtfName.setText(user.getName());
		this.jtfPhone.setText(String.valueOf(user.getPhone()));
		if (user.getCareer() != null) {
			this.jtfCareer.setText(user.getCareer());
		}
		if (user.getCompany() != null) {
			this.jcbCompany.setSelectedItem(user.getCompany().getName());
		}
	}

	private void fillInvoiceData(final Invoice invoice) {
		this.jdcInitialDate.setDate(invoice.getInitialdate());
		this.jdcFinalDate.setDate(invoice.getFinaldate());
		if (invoice.getSitefrom() != null) {
			this.jtfSiteFrom.setText(invoice.getSitefrom());
		}
		if (invoice.getSiteto() != null) {
			this.jtfSiteTo.setText(invoice.getSiteto());
		}
	}

	private void showButtonsByRoomStatus(final Roomstatus roomStatus) {
		if (roomStatus.equals(this.roomStatusBooked)) {
			final Date currentDate = new Date();
			if (DateUtils.isSameDay(currentDate, this.jdcInitialDate.getDate())) {
				this.jbtCheckIn.setVisible(true);
			} else {
				this.jbtCheckIn.setVisible(false);
			}
			this.jbtDelete.setVisible(true);
		} else {
			this.jbtCheckIn.setVisible(false);
			this.jbtDelete.setVisible(false);
		}
	}

	private void setCompanyModel(final List<Company> companyList) {
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Ninguna");
		for (final Company company : companyList) {
			model.addElement(company.getName());
		}
		this.jcbCompany.setModel(model);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jpTitle = new javax.swing.JPanel();
		jlbTitle = new javax.swing.JLabel();
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
		lbImage = new javax.swing.JLabel();
		jpAction = new javax.swing.JPanel();
		jbtCheckIn = new javax.swing.JButton();
		jbtDelete = new javax.swing.JButton();
		jbtClose = new javax.swing.JButton();

		setTitle("Hotelero");

		jpTitle.setBackground(new java.awt.Color(255, 255, 255));

		jlbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
		jlbTitle.setText("Habitación");

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

		jpClient.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Cliente",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jlbIdentification.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbIdentification.setText("Cedula(*):");

		jtfIdentification.setEditable(false);
		jtfIdentification.setBackground(new java.awt.Color(255, 255, 255));
		jtfIdentification
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#,##0"))));
		jtfIdentification.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbName.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbName.setText("Nombre(*):");

		jtfName.setEditable(false);
		jtfName.setBackground(new java.awt.Color(255, 255, 255));
		jtfName.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbPhone.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbPhone.setText("Teléfono(*):");

		jtfPhone.setEditable(false);
		jtfPhone.setBackground(new java.awt.Color(255, 255, 255));
		jtfPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
				new javax.swing.text.NumberFormatter(
						new java.text.DecimalFormat("#0"))));
		jtfPhone.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbCareer.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbCareer.setText("Profesión:");

		jtfCareer.setEditable(false);
		jtfCareer.setBackground(new java.awt.Color(255, 255, 255));
		jtfCareer.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbSiteFrom.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbSiteFrom.setText("Procedencia:");

		jtfSiteFrom.setEditable(false);
		jtfSiteFrom.setBackground(new java.awt.Color(255, 255, 255));
		jtfSiteFrom.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbSiteTo.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbSiteTo.setText("Destino:");

		jtfSiteTo.setEditable(false);
		jtfSiteTo.setBackground(new java.awt.Color(255, 255, 255));
		jtfSiteTo.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbCompany.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbCompany.setText("Empresa:");

		jlbInitialDate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbInitialDate.setText("Fecha de llegada(*):");

		jdcInitialDate.setBackground(new java.awt.Color(255, 255, 255));
		jdcInitialDate.setEnabled(false);
		jdcInitialDate.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jcbCompany.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jcbCompany.setEnabled(false);

		jlbFinalDate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbFinalDate.setText("Fecha de salida(*):");

		jdcFinalDate.setBackground(new java.awt.Color(255, 255, 255));
		jdcFinalDate.setEnabled(false);
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
																				javax.swing.GroupLayout.Alignment.TRAILING)
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
																				200,
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
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
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
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/images/soin.png"))); // NOI18N

		jpAction.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 0, 11))); // NOI18N

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

		jbtDelete.setBackground(new java.awt.Color(16, 135, 221));
		jbtDelete.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtDelete.setForeground(new java.awt.Color(255, 255, 255));
		jbtDelete.setText("Eliminar");
		jbtDelete.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtDeleteActionPerformed(evt);
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
												jbtDelete,
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
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jbtClose,
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
																jbtCheckIn,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtDelete,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtClose,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

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
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jpClient,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jpAction,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jpTitle,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jpClient,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

	private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCloseActionPerformed
		this.setVisible(false);
	}// GEN-LAST:event_jbtCloseActionPerformed

	private void jbtCheckInActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCheckInActionPerformed
		if (this.jbtCheckIn.isVisible()) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_SAVE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				final Roomstatus roomStatusCheckIn = this.invoiceController
						.selectRoomStatusDisabled();
				this.invoice.setUpdated(new Date());
				this.invoice.setRoomstatus(roomStatusCheckIn);
				this.invoiceController.saveInvoice(invoice);
				ViewUtils.showMessage(this, ViewUtils.MSG_SAVED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.parent.refreshRoomData();
				this.setVisible(false);
			}
		}
	}// GEN-LAST:event_jbtCheckInActionPerformed

	private void jbtDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtDeleteActionPerformed
		if (this.jbtDelete.isVisible()) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_DELETE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				final Roomstatus roomStatusEnabled = this.invoiceController
						.selectRoomStatusEnabled();
				final Invoicestatus invoiceStatusDeleted = this.invoiceController
						.selectInvoiceStatusDeleted();
				this.invoice.setEnabled(false);
				this.invoice.setUpdated(new Date());
				this.invoice.setRoomstatus(roomStatusEnabled);
				this.invoice.setInvoicestatus(invoiceStatusDeleted);
				this.invoiceController.saveInvoice(invoice);
				ViewUtils.showMessage(this, ViewUtils.MSG_DELETED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.parent.refreshRoomData();
				this.setVisible(false);
			}
		}
	}// GEN-LAST:event_jbtDeleteActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbtCheckIn;
	private javax.swing.JButton jbtClose;
	private javax.swing.JButton jbtDelete;
	private javax.swing.JComboBox<String> jcbCompany;
	private com.toedter.calendar.JDateChooser jdcFinalDate;
	private com.toedter.calendar.JDateChooser jdcInitialDate;
	private javax.swing.JLabel jlbCareer;
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
	private javax.swing.JPanel jpTitle;
	private javax.swing.JTextField jtfCareer;
	private javax.swing.JFormattedTextField jtfIdentification;
	private javax.swing.JTextField jtfName;
	private javax.swing.JFormattedTextField jtfPhone;
	private javax.swing.JTextField jtfSiteFrom;
	private javax.swing.JTextField jtfSiteTo;
	private javax.swing.JLabel lbImage;
	// End of variables declaration//GEN-END:variables
}