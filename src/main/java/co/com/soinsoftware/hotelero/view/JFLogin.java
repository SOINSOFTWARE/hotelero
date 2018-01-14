/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.hotelero.view;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import co.com.soinsoftware.hotelero.controller.MenuController;
import co.com.soinsoftware.hotelero.util.MidnightTimerTask;

import com.soinsoftware.hotelero.core.controller.UserController;
import com.soinsoftware.hotelero.persistence.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0.1
 */
public class JFLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Hotelero";

	private static final String EMPTY_LOGIN = "Por favor complete la casilla login";

	private static final String EMPTY_PASSWORD = "Por favor complete la casilla contraseña";

	private static final String WRONG_LOGIN = "¡Usuario o clave invalida!, intente nuevamente";

	private UserController controller;

	/**
	 * Creates new form JFLogin
	 */
	public JFLogin() {
		super();
		this.initComponents();
		this.controller = new UserController();
		this.setTitle(TITLE);
		this.setMaximized();
		this.setTextFieldLimits();
	}

	private void setMaximized() {
		final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setMaximizedBounds(env.getMaximumWindowBounds());
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	private void setTextFieldLimits() {
		this.jtfUser.setDocument(new JTextFieldLimit(10));
		this.jpfPassword.setDocument(new JTextFieldLimit(10));
	}

	private void validateLogin() {
		final String login = this.jtfUser.getText().trim();
		final char[] password = this.jpfPassword.getPassword();
		final int infoMessage = JOptionPane.INFORMATION_MESSAGE;
		if (login.equals("")) {
			ViewUtils.showMessage(this, EMPTY_LOGIN, TITLE, infoMessage);
		} else if (password.length == 0) {
			ViewUtils.showMessage(this, EMPTY_PASSWORD, TITLE, infoMessage);
		} else {
			final User user = controller.select(login, password);
			if (user != null) {
				this.setVisible(false);
				this.createAppFrames();
			} else {
				ViewUtils.showMessage(this, WRONG_LOGIN, TITLE, infoMessage);
			}
		}
	}

	private void createAppFrames() {
		final JFRoom roomFrame = new JFRoom();
		final JFRoomService roomServiceFrame = new JFRoomService();
		final JFRoomPayment roomPaymentFrame = new JFRoomPayment(roomFrame);
		final JFRoomHistory roomHistoryFrame = new JFRoomHistory();
		final JFServiceType serviceTypeFrame = new JFServiceType();
		final JFCompany companyFrame = new JFCompany(roomFrame);
		final JFService serviceFrame = new JFService();
		final JFRoomEdition roomPriceFrame = new JFRoomEdition();
		final JFFloor floorFrame = new JFFloor();
		final JFRoomType roomTypeFrame = new JFRoomType();
		final MenuController menuController = new MenuController(roomServiceFrame, roomPaymentFrame, roomHistoryFrame,
				serviceTypeFrame, serviceFrame, companyFrame, roomPriceFrame, floorFrame, roomTypeFrame);
		roomFrame.addController(menuController);
		roomFrame.setVisible(true);
		this.createMidnightTask(roomFrame);
	}

	private void createMidnightTask(final JFRoom jfRoom) {
		final MidnightTimerTask task = new MidnightTimerTask(jfRoom);
		final Timer timer = new Timer();
		timer.schedule(task, MidnightTimerTask.getMidnightDate(), MidnightTimerTask.ONCE_PER_DAY);
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

		pnTitle = new javax.swing.JPanel();
		lbTitle = new javax.swing.JLabel();
		pnUser = new javax.swing.JPanel();
		lbName = new javax.swing.JLabel();
		jtfUser = new javax.swing.JTextField();
		jbtCancel = new javax.swing.JButton();
		jbtLogin = new javax.swing.JButton();
		lbPassword = new javax.swing.JLabel();
		jpfPassword = new javax.swing.JPasswordField();
		lbImage = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/h-square.png")));
		setMinimumSize(new java.awt.Dimension(420, 320));
		setName("jfLogin"); // NOI18N

		pnTitle.setBackground(new java.awt.Color(255, 255, 255));
		pnTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		pnTitle.setToolTipText("");
		pnTitle.setName("pnTitle"); // NOI18N

		lbTitle.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
		lbTitle.setText("Login");

		javax.swing.GroupLayout pnTitleLayout = new javax.swing.GroupLayout(pnTitle);
		pnTitle.setLayout(pnTitleLayout);
		pnTitleLayout.setHorizontalGroup(pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnTitleLayout.createSequentialGroup().addContainerGap().addComponent(lbTitle)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnTitleLayout.setVerticalGroup(pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnTitleLayout.createSequentialGroup().addContainerGap().addComponent(lbTitle)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pnUser.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 12))); // NOI18N
		pnUser.setName("pnUser"); // NOI18N
		pnUser.setPreferredSize(new java.awt.Dimension(300, 200));

		lbName.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		lbName.setText("Login:");

		jtfUser.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
		jtfUser.setPreferredSize(new java.awt.Dimension(200, 20));

		jbtCancel.setBackground(new java.awt.Color(16, 135, 221));
		jbtCancel.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtCancel.setForeground(new java.awt.Color(255, 255, 255));
		jbtCancel.setText("Cancelar");
		jbtCancel.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtCancelActionPerformed(evt);
			}
		});

		jbtLogin.setBackground(new java.awt.Color(16, 135, 221));
		jbtLogin.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		jbtLogin.setForeground(new java.awt.Color(255, 255, 255));
		jbtLogin.setText("Entrar");
		jbtLogin.setPreferredSize(new java.awt.Dimension(89, 23));
		jbtLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtLoginActionPerformed(evt);
			}
		});

		lbPassword.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
		lbPassword.setText("Contraseña:");

		javax.swing.GroupLayout pnUserLayout = new javax.swing.GroupLayout(pnUser);
		pnUser.setLayout(pnUserLayout);
		pnUserLayout.setHorizontalGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnUserLayout.createSequentialGroup().addContainerGap().addGroup(pnUserLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnUserLayout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jbtCancel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jbtLogin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(pnUserLayout.createSequentialGroup()
								.addGroup(pnUserLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(lbName)
										.addComponent(jtfUser, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbPassword).addComponent(jpfPassword))
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		pnUserLayout.setVerticalGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnUserLayout.createSequentialGroup().addContainerGap().addComponent(lbName)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jtfUser, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbPassword)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
						.addGroup(pnUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jbtLogin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jbtCancel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/soin.png"))); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(pnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 232,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(0, 20, Short.MAX_VALUE).addComponent(lbImage,
								javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(pnTitle, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(pnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
						.addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jbtCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtCancelActionPerformed
		System.exit(EXIT_ON_CLOSE);
	}// GEN-LAST:event_jbtCancelActionPerformed

	private void jbtLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtLoginActionPerformed
		this.validateLogin();
	}// GEN-LAST:event_jbtLoginActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbtCancel;
	private javax.swing.JButton jbtLogin;
	private javax.swing.JPasswordField jpfPassword;
	private javax.swing.JTextField jtfUser;
	private javax.swing.JLabel lbImage;
	private javax.swing.JLabel lbName;
	private javax.swing.JLabel lbPassword;
	private javax.swing.JLabel lbTitle;
	private javax.swing.JPanel pnTitle;
	private javax.swing.JPanel pnUser;
	// End of variables declaration//GEN-END:variables
}
