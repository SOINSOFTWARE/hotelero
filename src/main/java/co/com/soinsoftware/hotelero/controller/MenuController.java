package co.com.soinsoftware.hotelero.controller;

import co.com.soinsoftware.hotelero.view.JFRoomService;
import co.com.soinsoftware.hotelero.view.JFRoomPayment;
import co.com.soinsoftware.hotelero.view.JFRoomHistory;
import co.com.soinsoftware.hotelero.view.JFService;
import co.com.soinsoftware.hotelero.view.JFServiceType;

/**
 * @author Carlos Rodriguez
 * @since 17/07/2016
 * @version 1.0
 */
public class MenuController {

    private final JFRoomService roomServiceFrame;

    private final JFRoomPayment roomPaymentFrame;

    private final JFRoomHistory roomHistoryFrame;

    private final JFServiceType serviceTypeFrame;

    private final JFService serviceFrame;

    public MenuController(final JFRoomService roomServiceFrame,
            final JFRoomPayment roomPaymentFrame,
            final JFRoomHistory viewUserFrame,
            final JFServiceType serviceTypeFrame,
            final JFService serviceFrame) {
        super();
        this.roomServiceFrame = roomServiceFrame;
        this.roomPaymentFrame = roomPaymentFrame;
        this.roomHistoryFrame = viewUserFrame;
        this.serviceTypeFrame = serviceTypeFrame;
        this.serviceFrame = serviceFrame;
    }

    public JFRoomService getRoomServiceFrame() {
        return roomServiceFrame;
    }

    public JFRoomPayment getRoomPaymentFrame() {
        return roomPaymentFrame;
    }

    public JFRoomHistory getRoomHistoryFrame() {
        return roomHistoryFrame;
    }

    public JFServiceType getServiceTypeFrame() {
        return serviceTypeFrame;
    }

    public JFService getServiceFrame() {
        return serviceFrame;
    }
}