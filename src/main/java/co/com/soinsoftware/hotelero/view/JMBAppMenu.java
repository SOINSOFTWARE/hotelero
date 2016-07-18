package co.com.soinsoftware.hotelero.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import co.com.soinsoftware.hotelero.controller.MenuController;

/**
 * @author Carlos Rodriguez
 * @since 17/07/2016
 * @version 1.0
 */
public class JMBAppMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 2306440560901177958L;

	private static final String MENU_ROOMS = "Habitaciones";

	private static final String MENU_ROOM_SERVICES = "Servicios";

	private static final String MENU_ROOM_CHECKOUT = "Check-out";

	private static final String MENU_ROOM_HISTORY = "Historico";

	private static final String MENU_CONFIGURATION = "Configuración";

	private static final String MENU_CONFIGURATION_SERVICES_TYPE = "Tipo de consumo";

	private static final String MENU_CONFIGURATION_SERVICES = "Servicio";

	private final MenuController controller;

	public JMBAppMenu(final MenuController controller) {
		super();
		this.addMenuRoom();
		this.addMenuConfiguration();
		this.controller = controller;
	}

	public void refresh() {
		this.controller.getRoomServiceFrame().refresh();
		this.controller.getRoomPaymentFrame().refresh();
		this.controller.getRoomHistoryFrame().refresh();
		this.controller.getServiceFrame().refresh();
	}

	@Override
	public void actionPerformed(final ActionEvent evt) {
		this.refresh();
		final String actionCommand = evt.getActionCommand();
		switch (actionCommand) {
		case MENU_ROOM_SERVICES:
			this.showRoomServiceFrame();
			break;
		case MENU_ROOM_CHECKOUT:
			this.showRoomPaymentFrame();
			break;
		case MENU_ROOM_HISTORY:
			this.showRoomHistoryFrame();
			break;
		case MENU_CONFIGURATION_SERVICES_TYPE:
			this.showServiceTypeFrame();
			break;
		case MENU_CONFIGURATION_SERVICES:
			this.showServiceFrame();
			break;
		}
	}

	private void addMenuRoom() {
		final JMenu menu = new JMenu(MENU_ROOMS);
		menu.setMnemonic(KeyEvent.VK_H);
		final JMenuItem miRoomService = ViewUtils.createJMenuItem(
				MENU_ROOM_SERVICES, KeyEvent.VK_S,
				KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		final JMenuItem miRoomPayment = ViewUtils.createJMenuItem(
				MENU_ROOM_CHECKOUT, KeyEvent.VK_C,
				KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		final JMenuItem miRoomHistory = ViewUtils.createJMenuItem(
				MENU_ROOM_HISTORY, KeyEvent.VK_H,
				KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		miRoomService.addActionListener(this);
		miRoomPayment.addActionListener(this);
		miRoomHistory.addActionListener(this);
		menu.add(miRoomService);
		menu.add(miRoomPayment);
		menu.add(miRoomHistory);
		this.add(menu);
	}

	private void addMenuConfiguration() {
		final JMenu menu = new JMenu(MENU_CONFIGURATION);
		menu.setMnemonic(KeyEvent.VK_C);
		final JMenuItem miServiceType = ViewUtils.createJMenuItem(
				MENU_CONFIGURATION_SERVICES_TYPE, KeyEvent.VK_C,
				KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		final JMenuItem miService = ViewUtils.createJMenuItem(
				MENU_CONFIGURATION_SERVICES, KeyEvent.VK_S,
				KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		miService.addActionListener(this);
		miServiceType.addActionListener(this);
		menu.add(miServiceType);
		menu.add(miService);
		this.add(menu);
	}

	private void showRoomServiceFrame() {
		if (!this.controller.getRoomServiceFrame().isVisible()) {
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getRoomServiceFrame().setVisible(true);
		}
	}

	private void showRoomPaymentFrame() {
		if (!this.controller.getRoomPaymentFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(true);
		}
	}

	private void showRoomHistoryFrame() {
		if (!this.controller.getRoomHistoryFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(true);
		}
	}

	private void showServiceFrame() {
		if (!this.controller.getServiceFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(true);
		}
	}

	private void showServiceTypeFrame() {
		if (!this.controller.getServiceTypeFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(true);
		}
	}
}