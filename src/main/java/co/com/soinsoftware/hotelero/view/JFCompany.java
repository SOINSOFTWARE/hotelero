package co.com.soinsoftware.hotelero.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import co.com.soinsoftware.hotelero.util.CompanyTableModel;

import com.soinsoftware.hotelero.core.controller.CompanyController;
import com.soinsoftware.hotelero.persistence.entity.Company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class JFCompany extends JDialog {

	private static final long serialVersionUID = 372651084892210851L;

	private static final String MSG_NAME_REQUIRED = "Complete el campo nombre de la empresa";

	private static final String MSG_NIT_REQUIRED = "Complete el campo NIT de la empresa";

	private final JFRoom jfRoom;

	private CompanyController companyController;

	public JFCompany(final JFRoom jfRoom) {
		this.companyController = new CompanyController();
		this.jfRoom = jfRoom;
		this.initComponents();
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setLocation((int) (screenSize.getWidth() / 2 - 350),
				(int) (screenSize.getHeight() / 2 - 350));
		this.setModal(true);
		this.setTextFieldLimits();
	}

	public void refresh() {
		this.jtfCompanyName.setText("");
		this.jtfCompanyNit.setText("");
		this.refreshTableData();
	}

	private void setTextFieldLimits() {
		this.jtfCompanyName.setDocument(new JTextFieldLimit(60));
		this.jtfCompanyNit.setDocument(new JTextFieldLimit(45));
	}

	private void refreshTableData() {
		final List<Company> companyList = this.companyController.select();
		final TableModel model = new CompanyTableModel(companyList);
		this.jtbCompanyList.setModel(model);
		this.jtbCompanyList.setFillsViewportHeight(true);
	}

	private boolean validateDataForSave() {
		boolean valid = true;
		final String name = this.jtfCompanyName.getText();
		final String nit = this.jtfCompanyNit.getText();
		if (name.trim().equals("")) {
			valid = false;
			ViewUtils.showMessage(this, MSG_NAME_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		} else if (nit.trim().equals("")) {
			valid = false;
			ViewUtils.showMessage(this, MSG_NIT_REQUIRED,
					ViewUtils.TITLE_REQUIRED_FIELDS, JOptionPane.ERROR_MESSAGE);
		}
		return valid;
	}

	private List<Company> getCompanyListFromTable() {
		final TableModel model = this.jtbCompanyList.getModel();
		return ((CompanyTableModel) model).getCompanyList();
	}

	private boolean hasCompanyToBeUpdated(final List<Company> companyList) {
		boolean hasElements = false;
		for (final Company company : companyList) {
			if ((company.getNewName() != null
					&& !company.getNewName().equals("") && !company
					.getNewName().equals(company.getName()))
					|| (company.getNewNit() != null
							&& !company.getNewNit().equals("") && !company
							.getNewNit().equals(company.getNit()))) {
				hasElements = true;
				break;
			}
		}
		return hasElements;
	}

	private boolean hasCompanyToBeDeleted(final List<Company> companyList) {
		boolean hasElements = false;
		for (final Company company : companyList) {
			if (company.isDelete()) {
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
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jpTitle = new javax.swing.JPanel();
		jlbTitle = new javax.swing.JLabel();
		lbImage = new javax.swing.JLabel();
		jpNewCompany = new javax.swing.JPanel();
		jlbCompanyName = new javax.swing.JLabel();
		jtfCompanyName = new javax.swing.JTextField();
		jlbCompanyNit = new javax.swing.JLabel();
		jtfCompanyNit = new javax.swing.JTextField();
		jbtSave = new javax.swing.JButton();
		jpCompanyList = new javax.swing.JPanel();
		jpActionButtons = new javax.swing.JPanel();
		jbtUpdate = new javax.swing.JButton();
		jbtDelete = new javax.swing.JButton();
		jspCompanyList = new javax.swing.JScrollPane();
		jtbCompanyList = new javax.swing.JTable();
		jpAction = new javax.swing.JPanel();
		jbtClose = new javax.swing.JButton();

		setTitle("Hotelero");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/images/melvic.png")));
		setResizable(false);

		jpTitle.setBackground(new java.awt.Color(255, 255, 255));

		jlbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
		jlbTitle.setText("Empresas");

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

		lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/images/soin.png"))); // NOI18N

		jpNewCompany.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Nueva empresa",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jlbCompanyName.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbCompanyName.setText("Nombre:");

		jtfCompanyName.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

		jlbCompanyNit.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jlbCompanyNit.setText("NIT:");

		jtfCompanyNit.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

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

		javax.swing.GroupLayout jpNewCompanyLayout = new javax.swing.GroupLayout(
				jpNewCompany);
		jpNewCompany.setLayout(jpNewCompanyLayout);
		jpNewCompanyLayout
				.setHorizontalGroup(jpNewCompanyLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpNewCompanyLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpNewCompanyLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jpNewCompanyLayout
																		.createSequentialGroup()
																		.addGroup(
																				jpNewCompanyLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jlbCompanyName)
																						.addComponent(
																								jlbCompanyNit))
																		.addGap(0,
																				141,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jpNewCompanyLayout
																		.createSequentialGroup()
																		.addGap(0,
																				101,
																				Short.MAX_VALUE)
																		.addComponent(
																				jbtSave,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jtfCompanyNit,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																190,
																Short.MAX_VALUE)
														.addComponent(
																jtfCompanyName))
										.addContainerGap()));
		jpNewCompanyLayout
				.setVerticalGroup(jpNewCompanyLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpNewCompanyLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jlbCompanyName)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfCompanyName,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jlbCompanyNit)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jtfCompanyNit,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jbtSave,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jpCompanyList.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Listado de empresas",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N

		jpActionButtons.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		jbtUpdate.setBackground(new java.awt.Color(16, 135, 221));
		jbtUpdate.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtUpdate.setForeground(new java.awt.Color(255, 255, 255));
		jbtUpdate.setText("Actualizar");
		jbtUpdate.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtUpdateActionPerformed(evt);
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

		javax.swing.GroupLayout jpActionButtonsLayout = new javax.swing.GroupLayout(
				jpActionButtons);
		jpActionButtons.setLayout(jpActionButtonsLayout);
		jpActionButtonsLayout
				.setHorizontalGroup(jpActionButtonsLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpActionButtonsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jpActionButtonsLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jbtUpdate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																100,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jbtDelete,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																100,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jpActionButtonsLayout
				.setVerticalGroup(jpActionButtonsLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpActionButtonsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jbtUpdate,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jbtDelete,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(120, Short.MAX_VALUE)));

		jspCompanyList.setViewportView(jtbCompanyList);
		if (jtbCompanyList.getColumnModel().getColumnCount() > 0) {
			jtbCompanyList.getColumnModel().getColumn(0).setResizable(false);
			jtbCompanyList.getColumnModel().getColumn(1).setResizable(false);
			jtbCompanyList.getColumnModel().getColumn(2).setResizable(false);
		}

		javax.swing.GroupLayout jpCompanyListLayout = new javax.swing.GroupLayout(
				jpCompanyList);
		jpCompanyList.setLayout(jpCompanyListLayout);
		jpCompanyListLayout
				.setHorizontalGroup(jpCompanyListLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpCompanyListLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jspCompanyList,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												320,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jpActionButtons,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		jpCompanyListLayout
				.setVerticalGroup(jpCompanyListLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jpCompanyListLayout
										.createSequentialGroup()
										.addGroup(
												jpCompanyListLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																jpActionButtons,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jspCompanyList,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
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
																		jpNewCompany,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jpCompanyList,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
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
								.addGap(11, 11, 11)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jpCompanyList,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jpNewCompany,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
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

	private void jbtSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtSaveActionPerformed
		if (this.validateDataForSave()) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_SAVE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				final String name = this.jtfCompanyName.getText();
				final String nit = this.jtfCompanyNit.getText();
				this.companyController.save(name, nit);
				ViewUtils.showMessage(this, ViewUtils.MSG_SAVED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.refresh();
				this.jfRoom.setCompanyModel();
			}
		}
	}// GEN-LAST:event_jbtSaveActionPerformed

	private void jbtUpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtUpdateActionPerformed
		final List<Company> companyList = this.getCompanyListFromTable();
		if (companyList != null && this.hasCompanyToBeUpdated(companyList)) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_UPDATE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				for (final Company company : companyList) {
					boolean edited = false;
					if (company.getNewName() != null
							&& !company.getNewName().equals("")
							&& !company.getNewName().equals(company.getName())) {
						company.setName(company.getNewName());
						edited = true;
					}
					if (company.getNewNit() != null
							&& !company.getNewNit().equals("")
							&& !company.getNewNit().equals(company.getNit())) {
						company.setNit(company.getNewNit());
						edited = true;
					}
					if (edited) {
						company.setUpdated(new Date());
						this.companyController.save(company);
					}
				}
				ViewUtils.showMessage(this, ViewUtils.MSG_UPDATED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.refresh();
				this.jfRoom.setCompanyModel();
			}
		} else {
			ViewUtils.showMessage(this, ViewUtils.MSG_UNEDITED,
					ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
		}
	}// GEN-LAST:event_jbtUpdateActionPerformed

	private void jbtDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtDeleteActionPerformed
		final List<Company> companyList = this.getCompanyListFromTable();
		if (companyList != null && this.hasCompanyToBeDeleted(companyList)) {
			final int confirmation = ViewUtils.showConfirmDialog(this,
					ViewUtils.MSG_DELETE_QUESTION, ViewUtils.TITLE_SAVED);
			if (confirmation == JOptionPane.OK_OPTION) {
				for (final Company company : companyList) {
					if (company.isDelete()) {
						company.setEnabled(false);
						company.setUpdated(new Date());
						this.companyController.save(company);
					}
				}
				ViewUtils.showMessage(this, ViewUtils.MSG_DELETED,
						ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
				this.refresh();
				this.jfRoom.setCompanyModel();
			}
		} else {
			ViewUtils.showMessage(this, ViewUtils.MSG_UNSELECTED,
					ViewUtils.TITLE_SAVED, JOptionPane.INFORMATION_MESSAGE);
		}
	}// GEN-LAST:event_jbtDeleteActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbtClose;
	private javax.swing.JButton jbtDelete;
	private javax.swing.JButton jbtSave;
	private javax.swing.JButton jbtUpdate;
	private javax.swing.JLabel jlbCompanyName;
	private javax.swing.JLabel jlbCompanyNit;
	private javax.swing.JLabel jlbTitle;
	private javax.swing.JPanel jpAction;
	private javax.swing.JPanel jpActionButtons;
	private javax.swing.JPanel jpCompanyList;
	private javax.swing.JPanel jpNewCompany;
	private javax.swing.JPanel jpTitle;
	private javax.swing.JScrollPane jspCompanyList;
	private javax.swing.JTable jtbCompanyList;
	private javax.swing.JTextField jtfCompanyName;
	private javax.swing.JTextField jtfCompanyNit;
	private javax.swing.JLabel lbImage;
	// End of variables declaration//GEN-END:variables
}
