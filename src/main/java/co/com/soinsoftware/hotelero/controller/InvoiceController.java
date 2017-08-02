package co.com.soinsoftware.hotelero.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import co.com.soinsoftware.hotelero.bll.InvoiceBLL;
import co.com.soinsoftware.hotelero.bll.InvoiceItemBLL;
import co.com.soinsoftware.hotelero.bll.InvoiceStatusBLL;
import co.com.soinsoftware.hotelero.bll.RoomBLL;
import co.com.soinsoftware.hotelero.bll.RoomStatusBLL;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoiceitem;
import co.com.soinsoftware.hotelero.entity.InvoiceStatus;
import co.com.soinsoftware.hotelero.entity.Room;
import co.com.soinsoftware.hotelero.entity.RoomStatus;
import co.com.soinsoftware.hotelero.entity.Service;
import co.com.soinsoftware.hotelero.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceController {

	private final InvoiceBLL invoiceBLL;

	private final InvoiceItemBLL invoiceItemBLL;

	private final InvoiceStatusBLL invoiceStatusBLL;

	private final RoomBLL roomBLL;

	private final RoomStatusBLL roomStatusBLL;

	public InvoiceController() throws IOException {
		super();
		this.invoiceBLL = InvoiceBLL.getInstance();
		this.invoiceItemBLL = InvoiceItemBLL.getInstance();
		this.invoiceStatusBLL = InvoiceStatusBLL.getInstance();
		this.roomBLL = RoomBLL.getInstance();
		this.roomStatusBLL = RoomStatusBLL.getInstance();
	}

	public List<Invoice> selectBooked() {
		List<Invoice> invoiceList = new ArrayList<>();
		final RoomStatus roomStatus = this.roomStatusBLL
				.selectRoomStatusBooked();
		final Date initialDate = this.getInitialDateForBooked();
		final Set<Invoice> invoiceSet = this.invoiceBLL.select(roomStatus,
				initialDate);
		if (invoiceSet != null) {
			invoiceList = new ArrayList<>(invoiceSet);
			Collections.sort(invoiceList);
		}
		return invoiceList;
	}

	@SuppressWarnings("deprecation")
	public Set<Invoice> selectNotEnabled(final Date initialDate,
			final Date finalDate) {
		initialDate.setHours(12);
		initialDate.setMinutes(0);
		initialDate.setSeconds(0);
		finalDate.setHours(12);
		finalDate.setMinutes(0);
		finalDate.setSeconds(0);
		final RoomStatus roomStatus = this.roomStatusBLL
				.selectRoomStatusEnabled();
		return this.invoiceBLL.select(roomStatus, initialDate, finalDate);
	}

	public List<Invoice> selectNotEnabled() {
		List<Invoice> invoiceList = new ArrayList<>();
		final RoomStatus roomStatus = this.roomStatusBLL
				.selectRoomStatusDisabled();
		final Set<Invoice> invoiceSet = this.invoiceBLL.select(roomStatus);
		if (invoiceSet != null) {
			invoiceList = new ArrayList<>(invoiceSet);
			Collections.sort(invoiceList, new Comparator<Invoice>() {

				@Override
				public int compare(final Invoice firstInvoice,
						final Invoice secondInvoice) {
					final Room firstRoom = firstInvoice.getRoom();
					final Room secondRoom = secondInvoice.getRoom();
					return firstRoom.getName().compareTo(secondRoom.getName());
				}
			});
		}
		return invoiceList;
	}

	public List<Invoice> selectByDate(final int year, final int month,
			final InvoiceStatus invoiceStatus, final Company company) {
		List<Invoice> invoiceList = new ArrayList<>();
		final RoomStatus roomStatusEnabled = this.selectRoomStatusEnabled();
		final Set<Invoice> invoiceSet = this.invoiceBLL.select(year, month,
				roomStatusEnabled, invoiceStatus, company);
		if (invoiceSet != null) {
			invoiceList = new ArrayList<>(invoiceSet);
			if (invoiceList.size() > 0) {
				Collections.sort(invoiceList);
			}
		}
		return invoiceList;
	}

	public List<Invoiceitem> selectInvoiceItem(final Invoice invoice) {
		List<Invoiceitem> invoiceItemList = new ArrayList<>();
		final Set<Invoiceitem> invoiceItemSet = this.invoiceItemBLL
				.selectByInvoice(invoice);
		if (invoiceItemSet != null) {
			invoiceItemList = new ArrayList<>(invoiceItemSet);
			Collections.sort(invoiceItemList);
		}
		return invoiceItemList;
	}

	public RoomStatus selectRoomStatusEnabled() {
		return this.roomStatusBLL.selectRoomStatusEnabled();
	}

	public RoomStatus selectRoomStatusDisabled() {
		return this.roomStatusBLL.selectRoomStatusDisabled();
	}

	public RoomStatus selectRoomStatusBooked() {
		return this.roomStatusBLL.selectRoomStatusBooked();
	}

	public InvoiceStatus selectInvoiceStatusNoPaid() {
		return this.invoiceStatusBLL.selectInvoiceStatusNoPaid();
	}

	public InvoiceStatus selectInvoiceStatusPaid() {
		return this.invoiceStatusBLL.selectInvoiceStatusPaid();
	}

	public InvoiceStatus selectInvoiceStatusBillToCompany() {
		return this.invoiceStatusBLL.selectInvoiceStatusBillToCompany();
	}

	public InvoiceStatus selectInvoiceStatusDeleted() {
		return this.invoiceStatusBLL.selectInvoiceStatusDeleted();
	}

	public List<Room> selectRooms() {
		List<Room> roomList = new ArrayList<>();
		final Set<Room> roomSet = this.roomBLL.select();
		if (roomSet != null) {
			roomList = new ArrayList<>(roomSet);
			if (roomList.size() > 0) {
				Collections.sort(roomList);
			}
		}
		return roomList;
	}

	public Invoice saveInvoiceBooking(final User user, final String roomName,
			final Date initialDate, final Date finalDate,
			final String siteFrom, final String siteTo, final Company company) {
		final RoomStatus roomStatus = this.roomStatusBLL
				.selectRoomStatusBooked();
		return this.saveInvoice(user, roomName, roomStatus, initialDate,
				finalDate, siteFrom, siteTo, company);
	}

	public Invoice saveInvoiceCheckIn(final User user, final String roomName,
			final Date initialDate, final Date finalDate,
			final String siteFrom, final String siteTo, final Company company) {
		final RoomStatus roomStatus = this.roomStatusBLL
				.selectRoomStatusDisabled();
		return this.saveInvoice(user, roomName, roomStatus, initialDate,
				finalDate, siteFrom, siteTo, company);
	}

	public void saveInvoice(final Invoice invoice) {
		this.invoiceBLL.save(invoice);
	}

	public Invoiceitem saveInvoiceItem(final Invoice invoice,
			final Service service, final int quantity, final long unitvalue,
			final long value, final Date invoiceItemDate) {
		final Date currentDate = new Date();
		final Invoiceitem invoiceItem = new Invoiceitem(invoice, service,
				quantity, unitvalue, value, invoiceItemDate, currentDate,
				currentDate, true);
		this.saveInvoiceItem(invoiceItem);
		return invoiceItem;
	}

	public void saveInvoiceItem(final Invoiceitem invoiceItem) {
		this.invoiceItemBLL.save(invoiceItem);
	}

	public void saveRoom(final Room room) {
		this.roomBLL.save(room);
	}

	@SuppressWarnings("deprecation")
	private Invoice saveInvoice(final User user, final String roomName,
			final RoomStatus roomStatus, final Date initialDate,
			final Date finalDate, final String siteFrom, final String siteTo,
			final Company company) {
		initialDate.setHours(13);
		initialDate.setMinutes(0);
		initialDate.setSeconds(0);
		finalDate.setHours(12);
		finalDate.setMinutes(0);
		finalDate.setSeconds(0);
		final Room room = this.roomBLL.select(roomName);
		final InvoiceStatus invoiceStatus = this.invoiceStatusBLL
				.selectInvoiceStatusNoPaid();
		final Date currentDate = new Date();
		final Invoice invoice = new Invoice(company, invoiceStatus, room,
				roomStatus, user, initialDate, finalDate, 0, siteFrom, siteTo,
				currentDate, currentDate, true, null);
		this.saveInvoice(invoice);
		return invoice;
	}

	private Date getInitialDateForBooked() {
		final LocalTime midnight = LocalTime.MIDNIGHT;
		final LocalDate today = LocalDate.now();
		final LocalDateTime tomorrowMidnight = today.plusDays(1).atTime(
				midnight);
		final ZonedDateTime zdt = tomorrowMidnight.atZone(ZoneId
				.systemDefault());
		return Date.from(zdt.toInstant());
	}
}