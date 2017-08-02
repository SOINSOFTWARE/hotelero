package co.com.soinsoftware.hotelero.bll;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import co.com.soinsoftware.hotelero.dao.InvoiceDAO;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.InvoiceStatus;
import co.com.soinsoftware.hotelero.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceBLL {

	private static InvoiceBLL instance;

	private final InvoiceDAO dao;

	public static InvoiceBLL getInstance() throws IOException {
		if (instance == null) {
			instance = new InvoiceBLL();
		}
		return instance;
	}

	public Set<Invoice> select() {
		return this.dao.select();
	}

	public Set<Invoice> select(final RoomStatus roomStatusBooked,
			final Date initialDate) {
		return this.dao.selectBooked(roomStatusBooked, initialDate);
	}

	public Set<Invoice> select(final RoomStatus roomStatus,
			final Date initialDate, final Date finalDate) {
		return this.dao.selectNotEnabled(roomStatus, initialDate, finalDate);
	}

	public Set<Invoice> select(final RoomStatus roomStatus) {
		return this.dao.selectByStatus(roomStatus);
	}

	public Set<Invoice> select(final int year, final int month,
			final RoomStatus roomStatusEnabled,
			final InvoiceStatus invoiceStatus, final Company company) {
		Set<Invoice> invoiceSet = null;
		if (invoiceStatus == null) {
			invoiceSet = this.dao.select(year, month, roomStatusEnabled);
		} else {
			if (invoiceStatus.getName().equals("Facturado a empresa")
					&& company != null) {
				invoiceSet = this.dao.select(year, month, roomStatusEnabled,
						invoiceStatus, company);
			} else {
				invoiceSet = this.dao.select(year, month, roomStatusEnabled,
						invoiceStatus);
			}
		}
		return invoiceSet;
	}

	public void save(final Invoice invoice) {
		this.dao.save(invoice);
	}

	private InvoiceBLL() throws IOException {
		super();
		this.dao = new InvoiceDAO();
	}
}