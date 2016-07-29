package co.com.soinsoftware.hotelero.bll;

import java.util.Date;
import java.util.Set;

import co.com.soinsoftware.hotelero.dao.InvoiceDAO;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoicestatus;
import co.com.soinsoftware.hotelero.entity.Roomstatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceBLL {

	private static InvoiceBLL instance;

	private final InvoiceDAO dao;

	public static InvoiceBLL getInstance() {
		if (instance == null) {
			instance = new InvoiceBLL();
		}
		return instance;
	}

	public Set<Invoice> select() {
		return this.dao.select();
	}

	public Set<Invoice> select(final Roomstatus roomStatusBooked,
			final Date initialDate) {
		return this.dao.selectBooked(roomStatusBooked, initialDate);
	}

	public Set<Invoice> select(final Roomstatus roomStatus,
			final Date initialDate, final Date finalDate) {
		return this.dao.selectNotEnabled(roomStatus, initialDate, finalDate);
	}

	public Set<Invoice> select(final Roomstatus roomStatus) {
		return this.dao.selectByStatus(roomStatus);
	}

	public Set<Invoice> select(final int year, final int month,
			final Roomstatus roomStatusEnabled,
			final Invoicestatus invoiceStatus, final Company company) {
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

	private InvoiceBLL() {
		super();
		this.dao = new InvoiceDAO();
	}
}