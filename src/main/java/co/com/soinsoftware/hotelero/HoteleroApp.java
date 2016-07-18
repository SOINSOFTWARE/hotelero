package co.com.soinsoftware.hotelero;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import co.com.soinsoftware.hotelero.dao.SessionController;
import co.com.soinsoftware.hotelero.view.JFLogin;
import co.com.soinsoftware.hotelero.view.JFLogo;

/**
 * @author Carlos Rodriguez
 * @since 12/07/2016
 * @version 1.0
 */
public class HoteleroApp {

	private static final String LOOK_AND_FEEL = "com.jtattoo.plaf.luna.LunaLookAndFeel";

	public static void main(String[] args) {
		final JFLogo logo = new JFLogo();
		final JFLogin login = new JFLogin();
		logo.setVisible(true);
		EventQueue
				.invokeLater(() -> {
					try {
						SessionController.getInstance();
						System.out
								.println("Initializing jasper report context");
						DefaultJasperReportsContext.getInstance();
						System.out
								.println("Finalizing initialization of jasper report context");
						UIManager.setLookAndFeel(LOOK_AND_FEEL);
						logo.setVisible(false);
						login.setVisible(true);
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException ex) {
						ex.printStackTrace();
					}
				});
	}
}