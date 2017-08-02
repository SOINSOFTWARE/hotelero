package co.com.soinsoftware.hotelero.bll;

import java.io.IOException;
import java.util.Set;

import co.com.soinsoftware.hotelero.dao.InvoiceItemDAO;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoiceitem;

/**
 * @author Carlos Rodriguez
 * @since 28/07/2016
 * @version 1.0
 */
public class InvoiceItemBLL {

	private static InvoiceItemBLL instance;

	private final InvoiceItemDAO dao;

	public static InvoiceItemBLL getInstance() throws IOException {
		if (instance == null) {
			instance = new InvoiceItemBLL();
		}
		return instance;
	}

	public Set<Invoiceitem> select() {
		return this.dao.select();
	}
	
	public Set<Invoiceitem> selectByInvoice(final Invoice invoice) {
		return this.dao.selectByInvoice(invoice);
	}

	public void save(final Invoiceitem invoiceItem) {
		this.dao.save(invoiceItem);
	}

	private InvoiceItemBLL() throws IOException {
		super();
		this.dao = new InvoiceItemDAO();
	}
}