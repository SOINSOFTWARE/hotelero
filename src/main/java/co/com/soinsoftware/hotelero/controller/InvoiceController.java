package co.com.soinsoftware.hotelero.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import co.com.soinsoftware.hotelero.bll.InvoiceBLL;
import co.com.soinsoftware.hotelero.bll.InvoiceStatusBLL;
import co.com.soinsoftware.hotelero.bll.RoomBLL;
import co.com.soinsoftware.hotelero.bll.RoomStatusBLL;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoicestatus;
import co.com.soinsoftware.hotelero.entity.Room;
import co.com.soinsoftware.hotelero.entity.Roomstatus;
import co.com.soinsoftware.hotelero.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceController {

	private final InvoiceBLL invoiceBLL;

	private final InvoiceStatusBLL invoiceStatusBLL;

	private final RoomBLL roomBLL;

	private final RoomStatusBLL roomStatusBLL;

	public InvoiceController() {
		super();
		this.invoiceBLL = InvoiceBLL.getInstance();
		this.invoiceStatusBLL = InvoiceStatusBLL.getInstance();
		this.roomBLL = RoomBLL.getInstance();
		this.roomStatusBLL = RoomStatusBLL.getInstance();
	}

	public List<Invoice> selectBooked() {
		List<Invoice> invoiceList = new ArrayList<>();
		final Roomstatus roomStatus = this.roomStatusBLL
				.selectRoomStatusBooked();
		final Date initialDate = this.getInitialDateForBooked();
		final Set<Invoice> invoiceSet = this.invoiceBLL.selectBooked(
				roomStatus, initialDate);
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
		final Roomstatus roomStatus = this.roomStatusBLL
				.selectRoomStatusEnabled();
		return this.invoiceBLL.selectNotEnabled(roomStatus, initialDate,
				finalDate);
	}

	public Roomstatus selectRoomStatusEnabled() {
		return this.roomStatusBLL.selectRoomStatusEnabled();
	}

	public Roomstatus selectRoomStatusDisabled() {
		return this.roomStatusBLL.selectRoomStatusDisabled();
	}

	public Roomstatus selectRoomStatusBooked() {
		return this.roomStatusBLL.selectRoomStatusBooked();
	}

	public Invoicestatus selectInvoiceStatusNoPaid() {
		return this.invoiceStatusBLL.selectInvoiceStatusNoPaid();
	}

	public Invoicestatus selectInvoiceStatusPaid() {
		return this.invoiceStatusBLL.selectInvoiceStatusPaid();
	}

	public Invoicestatus selectInvoiceStatusBillToCompany() {
		return this.invoiceStatusBLL.selectInvoiceStatusBillToCompany();
	}

	public Invoicestatus selectInvoiceStatusDeleted() {
		return this.invoiceStatusBLL.selectInvoiceStatusDeleted();
	}

	public Invoice saveInvoiceBooking(final User user, final String roomName,
			final Date initialDate, final Date finalDate,
			final String siteFrom, final String siteTo, final Company company) {
		final Roomstatus roomStatus = this.roomStatusBLL
				.selectRoomStatusBooked();
		return this.saveInvoice(user, roomName, roomStatus, initialDate,
				finalDate, siteFrom, siteTo, company);
	}

	public Invoice saveInvoiceCheckIn(final User user, final String roomName,
			final Date initialDate, final Date finalDate,
			final String siteFrom, final String siteTo, final Company company) {
		final Roomstatus roomStatus = this.roomStatusBLL
				.selectRoomStatusDisabled();
		return this.saveInvoice(user, roomName, roomStatus, initialDate,
				finalDate, siteFrom, siteTo, company);
	}

	public void saveInvoice(final Invoice invoice) {
		this.invoiceBLL.save(invoice);
	}

	@SuppressWarnings("deprecation")
	private Invoice saveInvoice(final User user, final String roomName,
			final Roomstatus roomStatus, final Date initialDate,
			final Date finalDate, final String siteFrom, final String siteTo,
			final Company company) {
		initialDate.setHours(13);
		initialDate.setMinutes(0);
		initialDate.setSeconds(0);
		finalDate.setHours(12);
		finalDate.setMinutes(0);
		finalDate.setSeconds(0);
		final Room room = this.roomBLL.select(roomName);
		final Invoicestatus invoiceStatus = this.invoiceStatusBLL
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