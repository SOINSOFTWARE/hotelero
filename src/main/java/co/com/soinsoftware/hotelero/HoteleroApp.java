package co.com.soinsoftware.hotelero;

import co.com.soinsoftware.hotelero.controller.MenuController;
import co.com.soinsoftware.hotelero.view.JFRoomService;
import co.com.soinsoftware.hotelero.view.JFRoomPayment;
import co.com.soinsoftware.hotelero.view.JFRoomHistory;
import co.com.soinsoftware.hotelero.view.JFLogo;
import co.com.soinsoftware.hotelero.view.JFService;
import co.com.soinsoftware.hotelero.view.JFRoom;
import co.com.soinsoftware.hotelero.view.JFServiceType;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;

/**
 * @author Carlos Rodriguez
 * @since 12/07/2016
 * @version 1.0
 */
public class HoteleroApp {

    private static final String LOOK_AND_FEEL = "com.jtattoo.plaf.luna.LunaLookAndFeel";

    public static void main(String[] args) {
        final JFLogo logo = new JFLogo();
        logo.setVisible(true);
        EventQueue.invokeLater(() -> {
            try {
                //SessionController.getInstance();
                System.out.println("Initializing jasper report context");
                DefaultJasperReportsContext.getInstance();
                System.out.println("Finalizing initialization of jasper report context");
                UIManager.setLookAndFeel(LOOK_AND_FEEL);
                logo.setVisible(false);
                buildAppView();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        });
    }

    private static void buildAppView() {
        final JFRoom roomFrame = new JFRoom();
        final JFRoomService roomServiceFrame = new JFRoomService();
        final JFRoomPayment roomPaymentFrame = new JFRoomPayment();
        final JFRoomHistory roomHistoryFrame = new JFRoomHistory();
        final JFServiceType serviceTypeFrame = new JFServiceType();
        final JFService serviceFrame = new JFService();
        final MenuController menuController = new MenuController(
                roomServiceFrame, roomPaymentFrame, roomHistoryFrame,
                serviceTypeFrame, serviceFrame);
        roomFrame.addController(menuController);
        roomFrame.setVisible(true);
    }
}
