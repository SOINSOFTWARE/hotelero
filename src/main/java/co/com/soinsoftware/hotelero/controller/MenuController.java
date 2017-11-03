package co.com.soinsoftware.hotelero.controller;

import co.com.soinsoftware.hotelero.view.JFCompany;
import co.com.soinsoftware.hotelero.view.JFFloor;
import co.com.soinsoftware.hotelero.view.JFRoomEdition;
import co.com.soinsoftware.hotelero.view.JFRoomHistory;
import co.com.soinsoftware.hotelero.view.JFRoomPayment;
import co.com.soinsoftware.hotelero.view.JFRoomService;
import co.com.soinsoftware.hotelero.view.JFRoomType;
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

	private final JFCompany companyFrame;

	private final JFRoomEdition roomPriceFrame;

	private final JFFloor floorFrame;
	
	private final JFRoomType roomTypeFrame;

	public MenuController(final JFRoomService roomServiceFrame,
			final JFRoomPayment roomPaymentFrame,
			final JFRoomHistory viewUserFrame,
			final JFServiceType serviceTypeFrame, final JFService serviceFrame,
			final JFCompany companyFrame, final JFRoomEdition roomPriceFrame,
			final JFFloor floorFrame, final JFRoomType roomTypeFrame) {
		super();
		this.roomServiceFrame = roomServiceFrame;
		this.roomPaymentFrame = roomPaymentFrame;
		this.roomHistoryFrame = viewUserFrame;
		this.serviceTypeFrame = serviceTypeFrame;
		this.serviceFrame = serviceFrame;
		this.companyFrame = companyFrame;
		this.roomPriceFrame = roomPriceFrame;
		this.floorFrame = floorFrame;
		this.roomTypeFrame = roomTypeFrame;
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

	public JFCompany getCompanyFrame() {
		return companyFrame;
	}

	public JFRoomEdition getRoomPriceFrame() {
		return roomPriceFrame;
	}

	public JFFloor getFloorFrame() {
		return floorFrame;
	}
	
	public JFRoomType getRoomTypeFrame() {
		return roomTypeFrame;
	}
}