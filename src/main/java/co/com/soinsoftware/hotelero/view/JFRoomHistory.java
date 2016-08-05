package co.com.soinsoftware.hotelero.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.table.TableModel;

import co.com.soinsoftware.hotelero.controller.CompanyController;
import co.com.soinsoftware.hotelero.controller.InvoiceController;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoicestatus;
import co.com.soinsoftware.hotelero.util.InvoiceTableModel;

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
public class JFRoomHistory extends JDialog {

	private static final long serialVersionUID = 3967944685330855230L;

	private final CompanyController companyController;

	private final InvoiceController invoiceController;

	private List<Company> companyList;

	private List<Invoicestatus> invoiceStatusList;

	public JFRoomHistory() {
		this.companyController = new CompanyController();
		this.invoiceController = new InvoiceController();
		this.initComponents();
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setLocation((int) (screenSize.getWidth() / 2 - 350),
				(int) (screenSize.getHeight() / 2 - 350));
		this.setModal(true);
		this.setMonthModel();
		this.setInvoiceStatusModel();
		this.setCompanyModel();
	}

	public void refresh() {
		this.setJlsMonthToCurrentMonth();
		this.jcbAccountState.setSelectedIndex(0);
		this.jcbCompany.setSelectedIndex(0);
		this.jcbCompany.setEnabled(false);
		this.refreshTableData();
	}

	private void setJlsMonthToCurrentMonth() {
		final Calendar calendar = Calendar.getInstance();
		final int month = calendar.get(Calendar.MONTH);
		this.jlsMonth.setSelectedIndex(month);
	}

	private void setMonthModel() {
		final String[] months = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
				"Junio", "Julio", "Agosto", "Septiembre", "Octubre",
				"Noviembre", "Diciembre" };
		this.jlsMonth.setListData(months);
	}

	private void setInvoiceStatusModel() {
		final Invoicestatus statusBillToCompany = this.invoiceController
				.selectInvoiceStatusBillToCompany();
		final Invoicestatus statusPaid = this.invoiceController
				.selectInvoiceStatusPaid();
		this.invoiceStatusList = new ArrayList<>();
		this.invoiceStatusList.add(statusPaid);
		this.invoiceStatusList.add(statusBillToCompany);
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Todos");
		for (final Invoicestatus invoiceStatus : this.invoiceStatusList) {
			model.addElement(invoiceStatus.getName());
		}
		this.jcbAccountState.setModel(model);
	}

	private void setCompanyModel() {
		this.companyList = this.companyController.selectCompanies();
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Todas");
		for (final Company company : this.companyList) {
			model.addElement(company.getName());
		}
		this.jcbCompany.setModel(model);
	}

	private Invoicestatus getInvoiceStatusSelected() {
		Invoicestatus invoiceStatus = null;
		if (this.jcbAccountState.getSelectedIndex() > 0) {
			final int index = this.jcbAccountState.getSelectedIndex() - 1;
			invoiceStatus = this.invoiceStatusList.get(index);
		}
		return invoiceStatus;
	}

	private Company getCompanySelected() {
		Company company = null;
		if (this.jcbCompany.getSelectedIndex() > 0) {
			final int index = this.jcbCompany.getSelectedIndex() - 1;
			company = this.companyList.get(index);
		}
		return company;
	}

	private int getYear() {
		final String yearStr = this.jtfYear.getText().replace(".", "")
				.replace(",", "");
		return Integer.parseInt(yearStr);
	}

	private void refreshTableData(final List<Invoice> invoiceList) {
		final TableModel model = new InvoiceTableModel(invoiceList);
		this.jtbRoomHistory.setModel(model);
		this.jtbRoomHistory.setFillsViewportHeight(true);
	}

	private void refreshTableData() {
		final int year = this.getYear();
		final int month = this.jlsMonth.getSelectedIndex() + 1;
		final Invoicestatus invoiceStatus = this.getInvoiceStatusSelected();
		final Company company = this.getCompanySelected();
		final List<Invoice> invoiceList = this.invoiceController.selectByDate(
				year, month, invoiceStatus, company);
		this.refreshTableData(invoiceList);
		this.calculateTotal(invoiceList);
	}

	private void calculateTotal(final List<Invoice> invoiceList) {
		long total = 0;
		for (final Invoice invoice : invoiceList) {
			total += invoice.getValue();
		}
		this.jlbTotal.setText("Total: " + String.valueOf(total));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		lbImage = new javax.swing.JLabel();
		jpTitle = new javax.swing.JPanel();
		jlbTitle = new javax.swing.JLabel();
		jpSearch = new javax.swing.JPanel();
		jlbYear = new javax.swing.JLabel();
		jtfYear = new javax.swing.JFormattedTextField();
		jlbMonth = new javax.swing.JLabel();
		jspMonth = new javax.swing.JScrollPane();
		jlsMonth = new javax.swing.JList<String>();
		jlbInvoiceStatus = new javax.swing.JLabel();
		jlbCompany = new javax.swing.JLabel();
		jbtSearch = new javax.swing.JButton();
		jcbAccountState = new javax.swing.JComboBox<String>();
		jcbCompany = new javax.swing.JComboBox<String>();
		jpRoomHistory = new javax.swing.JPanel();
		jspRoomHistory = new javax.swing.JScrollPane();
		jtbRoomHistory = new javax.swing.JTable();
		jlbTotal = new javax.swing.JLabel();
		jpAction = new javax.swing.JPanel();
		jbtClose = new javax.swing.JButton();

		setTitle("Hotelero");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/images/melvic.png")));
		setResizable(false);

		lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/images/soin.png"))); // NOI18N

		jpTitle.setBackground(new java.awt.Color(255, 255, 255));

		jlbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
		jlbTitle.setText("Historico");

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

		jpSearch.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Busqueda",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jlbYear.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbYear.setText("Año:");

		jtfYear.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
				new javax.swing.text.NumberFormatter(
						new java.text.DecimalFormat("####0"))));
		jtfYear.setText(String.valueOf(Calendar.getInstance()
				.get(Calendar.YEAR)));
		jtfYear.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbMonth.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbMonth.setText("Mes:");

		jspMonth.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlsMonth.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jspMonth.setViewportView(jlsMonth);

		jlbInvoiceStatus.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbInvoiceStatus.setText("Estado:");

		jlbCompany.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbCompany.setText("Empresa:");

		jbtSearch.setBackground(new java.awt.Color(16, 135, 221));
		jbtSearch.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtSearch.setForeground(new java.awt.Color(255, 255, 255));
		jbtSearch.setText("Buscar");
		jbtSearch.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtSearchActionPerformed(evt);
			}
		});

		jcbAccountState.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jcbAccountState.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jcbAccountStateActionPerformed(evt);
			}
		});

		jcbCompany.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jcbCompany.setEnabled(false);

		javax.swing.GroupLayout jpSearchLayout = new javax.swing.GroupLayout(
				jpSearch);
		jpSearch.setLayout(jpSearchLayout);
		jpSearchLayout
				.setHorizontalGroup(jpSearchLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpSearchLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpSearchLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jcbCompany,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jpSearchLayout
																		.createSequentialGroup()
																		.addGroup(
																				jpSearchLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jbtSearch,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								jpSearchLayout
																										.createSequentialGroup()
																										.addGroup(
																												jpSearchLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jlbYear)
																														.addComponent(
																																jtfYear,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																80,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jlbMonth)
																														.addComponent(
																																jspMonth,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																160,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jlbInvoiceStatus)
																														.addComponent(
																																jlbCompany))
																										.addGap(40,
																												40,
																												40)))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE))
														.addComponent(
																jcbAccountState,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		jpSearchLayout
				.setVerticalGroup(jpSearchLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpSearchLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jlbYear)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfYear,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbMonth)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jspMonth,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												102,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbInvoiceStatus)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jcbAccountState,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(14, 14, 14)
										.addComponent(jlbCompany)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jcbCompany,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jbtSearch,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jpRoomHistory.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Listado historico por mes",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jspRoomHistory.setViewportView(jtbRoomHistory);
		if (jtbRoomHistory.getColumnModel().getColumnCount() > 0) {
			jtbRoomHistory.getColumnModel().getColumn(0).setResizable(false);
			jtbRoomHistory.getColumnModel().getColumn(1).setResizable(false);
			jtbRoomHistory.getColumnModel().getColumn(2).setResizable(false);
			jtbRoomHistory.getColumnModel().getColumn(3).setResizable(false);
		}

		jlbTotal.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbTotal.setText("Total:");

		javax.swing.GroupLayout jpRoomHistoryLayout = new javax.swing.GroupLayout(
				jpRoomHistory);
		jpRoomHistory.setLayout(jpRoomHistoryLayout);
		jpRoomHistoryLayout
				.setHorizontalGroup(jpRoomHistoryLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpRoomHistoryLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpRoomHistoryLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jspRoomHistory,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																488,
																Short.MAX_VALUE)
														.addGroup(
																jpRoomHistoryLayout
																		.createSequentialGroup()
																		.addComponent(
																				jlbTotal)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jpRoomHistoryLayout.setVerticalGroup(jpRoomHistoryLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jpRoomHistoryLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jspRoomHistory,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										180,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(jlbTotal)
								.addContainerGap(12, Short.MAX_VALUE)));

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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(lbImage,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										388,
										javax.swing.GroupLayout.PREFERRED_SIZE))
				.addComponent(jpTitle, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jpSearch,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jpRoomHistory,
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
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jpSearch,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jpRoomHistory,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jpAction,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
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

	private void jcbAccountStateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jcbAccountStateActionPerformed
		final Invoicestatus invoiceStatus = this.getInvoiceStatusSelected();
		if (invoiceStatus != null
				&& invoiceStatus.getName().equals("Facturado a empresa")) {
			this.jcbCompany.setEnabled(true);
		} else {
			this.jcbCompany.setEnabled(false);
		}
	}// GEN-LAST:event_jcbAccountStateActionPerformed

	private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCloseActionPerformed
		this.setVisible(false);
	}// GEN-LAST:event_jbtCloseActionPerformed

	private void jbtSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtSearchActionPerformed
		this.refreshTableData();
	}// GEN-LAST:event_jbtSearchActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbtClose;
	private javax.swing.JButton jbtSearch;
	private javax.swing.JComboBox<String> jcbAccountState;
	private javax.swing.JComboBox<String> jcbCompany;
	private javax.swing.JLabel jlbCompany;
	private javax.swing.JLabel jlbInvoiceStatus;
	private javax.swing.JLabel jlbMonth;
	private javax.swing.JLabel jlbTitle;
	private javax.swing.JLabel jlbTotal;
	private javax.swing.JLabel jlbYear;
	private javax.swing.JList<String> jlsMonth;
	private javax.swing.JPanel jpAction;
	private javax.swing.JPanel jpRoomHistory;
	private javax.swing.JPanel jpSearch;
	private javax.swing.JPanel jpTitle;
	private javax.swing.JScrollPane jspMonth;
	private javax.swing.JScrollPane jspRoomHistory;
	private javax.swing.JTable jtbRoomHistory;
	private javax.swing.JFormattedTextField jtfYear;
	private javax.swing.JLabel lbImage;
	// End of variables declaration//GEN-END:variables
}
