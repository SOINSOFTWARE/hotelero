package co.com.soinsoftware.hotelero.bll;

import java.util.Set;

import co.com.soinsoftware.hotelero.dao.InvoiceStatusDAO;
import co.com.soinsoftware.hotelero.entity.Invoicestatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceStatusBLL {

	private static final String INVOICE_STATUS_NO_PAID = "Sin pago";

	private static final String INVOICE_STATUS_PAID = "Pagado";

	private static final String INVOICE_STATUS_BILL_TO_COMPANY = "Facturado a empresa";

	private static final String INVOICE_STATUS_DELETED = "Cancelado";

	private static InvoiceStatusBLL instance;

	private final InvoiceStatusDAO dao;

	public static InvoiceStatusBLL getInstance() {
		if (instance == null) {
			instance = new InvoiceStatusBLL();
		}
		return instance;
	}

	public Set<Invoicestatus> select() {
		return this.dao.select();
	}

	public Invoicestatus select(final String name) {
		return this.dao.select(name);
	}

	public Invoicestatus selectInvoiceStatusNoPaid() {
		return this.select(INVOICE_STATUS_NO_PAID);
	}

	public Invoicestatus selectInvoiceStatusPaid() {
		return this.select(INVOICE_STATUS_PAID);
	}

	public Invoicestatus selectInvoiceStatusBillToCompany() {
		return this.select(INVOICE_STATUS_BILL_TO_COMPANY);
	}

	public Invoicestatus selectInvoiceStatusDeleted() {
		return this.select(INVOICE_STATUS_DELETED);
	}

	public void save(final Invoicestatus invoiceStatus) {
		this.dao.save(invoiceStatus);
	}

	private InvoiceStatusBLL() {
		super();
		this.dao = new InvoiceStatusDAO();
	}
}