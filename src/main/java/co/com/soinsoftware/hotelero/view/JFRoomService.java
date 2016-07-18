package co.com.soinsoftware.hotelero.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;

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

    /**
     * Creates new form JFRoomService
     */
    public JFRoomService() {
        this.initComponents();
        final Dimension screenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();
        this.setLocation((int) (screenSize.getWidth() / 2 - 350),
                (int) (screenSize.getHeight() / 2 - 350));
        this.setModal(true);
    }

    public void refresh() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        jbtDeleteService = new javax.swing.JButton();
        jpAction = new javax.swing.JPanel();
        jbtClose = new javax.swing.JButton();
        lbImage = new javax.swing.JLabel();

        setTitle("Hotelero");
        setName("jfRoomService"); // NOI18N
        setResizable(false);

        jpTitle.setBackground(new java.awt.Color(255, 255, 255));

        jlbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jlbTitle.setText("Servicio a la habitación");

        javax.swing.GroupLayout jpTitleLayout = new javax.swing.GroupLayout(jpTitle);
        jpTitle.setLayout(jpTitleLayout);
        jpTitleLayout.setHorizontalGroup(
            jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTitleLayout.setVerticalGroup(
            jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitleLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jlbTitle)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jpService.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Servicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        jlbRoom.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jlbRoom.setText("Habitación:");

        jcbRoom.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jcbRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "101", "102", "103", "..", "...", "...", "....", "...", " " }));

        jlbIdentification.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jlbIdentification.setText("Cedula:");

        jtfIdentification.setEditable(false);
        jtfIdentification.setBackground(new java.awt.Color(255, 255, 255));
        jtfIdentification.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jtfIdentification.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

        jlbName.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jlbName.setText("Nombre:");

        jtfName.setEditable(false);
        jtfName.setBackground(new java.awt.Color(255, 255, 255));
        jtfName.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

        jtbService.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jtbService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Valor", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspService.setViewportView(jtbService);
        if (jtbService.getColumnModel().getColumnCount() > 0) {
            jtbService.getColumnModel().getColumn(0).setResizable(false);
            jtbService.getColumnModel().getColumn(1).setResizable(false);
            jtbService.getColumnModel().getColumn(2).setResizable(false);
            jtbService.getColumnModel().getColumn(3).setResizable(false);
        }

        jpCreateService.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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
        jcbServiceCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Bar", "Alimentos", "Otros" }));

        jlbServiceValue.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jlbServiceValue.setText("Precio:");

        jtfServiceValue.setEditable(false);
        jtfServiceValue.setBackground(new java.awt.Color(255, 255, 255));
        jtfServiceValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jtfServiceValue.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N

        jcbService.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jcbService.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Seleccionar---", "Bar", "Alimentos", "Otros" }));

        jlbService.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jlbService.setText("Servicio:");

        javax.swing.GroupLayout jpCreateServiceLayout = new javax.swing.GroupLayout(jpCreateService);
        jpCreateService.setLayout(jpCreateServiceLayout);
        jpCreateServiceLayout.setHorizontalGroup(
            jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCreateServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCreateServiceLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtAddService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpCreateServiceLayout.createSequentialGroup()
                        .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcInitialDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbServicelDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbServiceCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbServiceCategory))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbService)
                            .addComponent(jcbService, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfServiceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbServiceValue))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpCreateServiceLayout.setVerticalGroup(
            jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCreateServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbServicelDate)
                    .addComponent(jlbServiceCategory)
                    .addComponent(jlbServiceValue)
                    .addComponent(jlbService))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcInitialDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpCreateServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbServiceCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfServiceValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jbtAddService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout jpServiceLayout = new javax.swing.GroupLayout(jpService);
        jpService.setLayout(jpServiceLayout);
        jpServiceLayout.setHorizontalGroup(
            jpServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpServiceLayout.createSequentialGroup()
                        .addGroup(jpServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpServiceLayout.createSequentialGroup()
                                .addComponent(jcbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jtfIdentification, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpServiceLayout.createSequentialGroup()
                                .addComponent(jlbRoom)
                                .addGap(83, 83, 83)
                                .addComponent(jlbIdentification)))
                        .addGap(18, 18, 18)
                        .addGroup(jpServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbName)
                            .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jpCreateService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspService))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpServiceLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtDeleteService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jpServiceLayout.setVerticalGroup(
            jpServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbRoom)
                    .addComponent(jlbIdentification)
                    .addComponent(jlbName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfIdentification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jpCreateService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jspService, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtDeleteService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpAction.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 11))); // NOI18N

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

        javax.swing.GroupLayout jpActionLayout = new javax.swing.GroupLayout(jpAction);
        jpAction.setLayout(jpActionLayout);
        jpActionLayout.setHorizontalGroup(
            jpActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpActionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jpActionLayout.setVerticalGroup(
            jpActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpActionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jbtClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/soin.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtCloseActionPerformed

    private void jbtAddServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtAddServiceActionPerformed

    private void jbtDeleteServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDeleteServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtDeleteServiceActionPerformed

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
    private javax.swing.JFormattedTextField jtfServiceValue;
    private javax.swing.JLabel lbImage;
    // End of variables declaration//GEN-END:variables
}