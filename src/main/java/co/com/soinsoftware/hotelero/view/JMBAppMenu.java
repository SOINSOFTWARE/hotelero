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
 * @version 1.0.1
 */
public class JMBAppMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 2306440560901177958L;

	private static final String MENU_ROOMS = "Habitaciones";

	private static final String MENU_ROOM_SERVICES = "Servicios";

	private static final String MENU_ROOM_CHECKOUT = "Check-out";

	private static final String MENU_ROOM_HISTORY = "Historico";

	private static final String MENU_CONFIGURATION = "Configuración";

	private static final String MENU_CONFIGURATION_COMPANY = "Empresa";

	private static final String MENU_CONFIGURATION_SERVICE_TYPE = "Tipo de consumo";

	private static final String MENU_CONFIGURATION_SERVICE = "Servicio";

	private static final String MENU_INITIAL_CONFIGURATION = "Configuración inicial";

	private static final String MENU_INITIAL_CONFIGURATION_FLOOR = "Pisos";

	private static final String MENU_INITIAL_CONFIGURATION_ROOM_TYPE = "Tipo de habitaciones";

	private static final String MENU_INITIAL_CONFIGURATION_ROOM_PRICE = "Precios habitaciones";

	private final MenuController controller;

	public JMBAppMenu(final MenuController controller) {
		super();
		addMenuRoom();
		addMenuConfiguration();
		addMenuInitialConfiguration();
		this.controller = controller;
	}

	@Override
	public void actionPerformed(final ActionEvent evt) {
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
		case MENU_CONFIGURATION_SERVICE_TYPE:
			this.showServiceTypeFrame();
			break;
		case MENU_CONFIGURATION_SERVICE:
			this.showServiceFrame();
			break;
		case MENU_CONFIGURATION_COMPANY:
			this.showCompanyFrame();
			break;
		case MENU_INITIAL_CONFIGURATION_ROOM_PRICE:
			this.showRoomPriceFrame();
			break;
		case MENU_INITIAL_CONFIGURATION_FLOOR:
			this.showFloorFrame();
			break;
		case MENU_INITIAL_CONFIGURATION_ROOM_TYPE:
			showRoomTypeFrame();
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
				MENU_CONFIGURATION_SERVICE_TYPE, KeyEvent.VK_C,
				KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		final JMenuItem miService = ViewUtils.createJMenuItem(
				MENU_CONFIGURATION_SERVICE, KeyEvent.VK_S,
				KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		final JMenuItem miCompany = ViewUtils.createJMenuItem(
				MENU_CONFIGURATION_COMPANY, KeyEvent.VK_E,
				KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.ALT_MASK));
		miService.addActionListener(this);
		miServiceType.addActionListener(this);
		miCompany.addActionListener(this);
		menu.add(miServiceType);
		menu.add(miService);
		menu.add(miCompany);
		this.add(menu);
	}

	private void addMenuInitialConfiguration() {
		final JMenu menu = new JMenu(MENU_INITIAL_CONFIGURATION);
		menu.setMnemonic(KeyEvent.VK_I);
		final JMenuItem miFloor = ViewUtils.createJMenuItem(
				MENU_INITIAL_CONFIGURATION_FLOOR, KeyEvent.VK_F,
				KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.ALT_MASK));
		final JMenuItem miRoomType = ViewUtils.createJMenuItem(
				MENU_INITIAL_CONFIGURATION_ROOM_TYPE, KeyEvent.VK_T,
				KeyStroke.getKeyStroke(KeyEvent.VK_8, ActionEvent.ALT_MASK));
		final JMenuItem miRoomPrice = ViewUtils.createJMenuItem(
				MENU_INITIAL_CONFIGURATION_ROOM_PRICE, KeyEvent.VK_P,
				KeyStroke.getKeyStroke(KeyEvent.VK_9, ActionEvent.ALT_MASK));
		miFloor.addActionListener(this);
		miRoomType.addActionListener(this);
		miRoomPrice.addActionListener(this);
		menu.add(miFloor);
		menu.add(miRoomType);
		menu.add(miRoomPrice);
		this.add(menu);
	}

	private void showRoomServiceFrame() {
		if (!this.controller.getRoomServiceFrame().isVisible()) {
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getRoomServiceFrame().refresh();
			this.controller.getRoomServiceFrame().setVisible(true);
		}
	}

	private void showRoomPaymentFrame() {
		if (!this.controller.getRoomPaymentFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().refresh();
			this.controller.getRoomPaymentFrame().setVisible(true);
		}
	}

	private void showRoomHistoryFrame() {
		if (!this.controller.getRoomHistoryFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().refresh();
			this.controller.getRoomHistoryFrame().setVisible(true);
		}
	}

	private void showServiceFrame() {
		if (!this.controller.getServiceFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getServiceFrame().refresh();
			this.controller.getServiceFrame().setVisible(true);
		}
	}

	private void showServiceTypeFrame() {
		if (!this.controller.getServiceTypeFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getServiceTypeFrame().refresh();
			this.controller.getServiceTypeFrame().setVisible(true);
		}
	}

	private void showCompanyFrame() {
		if (!this.controller.getCompanyFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().refresh();
			this.controller.getCompanyFrame().setVisible(true);
		}
	}

	private void showRoomPriceFrame() {
		if (!this.controller.getRoomPriceFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getRoomPriceFrame().refresh();
			this.controller.getRoomPriceFrame().setVisible(true);
		}
	}

	private void showFloorFrame() {
		if (!this.controller.getFloorFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getRoomTypeFrame().setVisible(false);
			this.controller.getFloorFrame().refresh();
			this.controller.getFloorFrame().setVisible(true);
		}
	}

	private void showRoomTypeFrame() {
		if (!this.controller.getRoomTypeFrame().isVisible()) {
			this.controller.getRoomServiceFrame().setVisible(false);
			this.controller.getRoomPaymentFrame().setVisible(false);
			this.controller.getRoomHistoryFrame().setVisible(false);
			this.controller.getServiceFrame().setVisible(false);
			this.controller.getServiceTypeFrame().setVisible(false);
			this.controller.getCompanyFrame().setVisible(false);
			this.controller.getRoomPriceFrame().setVisible(false);
			this.controller.getFloorFrame().setVisible(false);
			this.controller.getRoomTypeFrame().refresh();
			this.controller.getRoomTypeFrame().setVisible(true);
		}
	}
}