package co.com.soinsoftware.hotelero.bll;

import java.util.Date;
import java.util.Set;

import co.com.soinsoftware.hotelero.dao.InvoiceDAO;
import co.com.soinsoftware.hotelero.entity.Invoice;
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

	public Set<Invoice> selectBooked(final Roomstatus roomStatus,
			final Date initialDate) {
		return this.dao.selectBooked(roomStatus, initialDate);
	}

	public Set<Invoice> selectNotEnabled(final Roomstatus roomStatus,
			final Date initialDate, final Date finalDate) {
		return this.dao.selectNotEnabled(roomStatus, initialDate, finalDate);
	}

	public void save(final Invoice invoice) {
		this.dao.save(invoice);
	}

	private InvoiceBLL() {
		super();
		this.dao = new InvoiceDAO();
	}
}