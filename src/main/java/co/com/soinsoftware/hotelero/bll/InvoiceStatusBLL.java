package co.com.soinsoftware.hotelero.bll;

import java.io.IOException;
import java.util.Set;

import co.com.soinsoftware.hotelero.dao.InvoiceStatusDAO;
import co.com.soinsoftware.hotelero.entity.InvoiceStatus;

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

	public static InvoiceStatusBLL getInstance() throws IOException {
		if (instance == null) {
			instance = new InvoiceStatusBLL();
		}
		return instance;
	}

	public Set<InvoiceStatus> select() {
		return this.dao.select();
	}

	public InvoiceStatus select(final String name) {
		return this.dao.select(name);
	}

	public InvoiceStatus selectInvoiceStatusNoPaid() {
		return this.select(INVOICE_STATUS_NO_PAID);
	}

	public InvoiceStatus selectInvoiceStatusPaid() {
		return this.select(INVOICE_STATUS_PAID);
	}

	public InvoiceStatus selectInvoiceStatusBillToCompany() {
		return this.select(INVOICE_STATUS_BILL_TO_COMPANY);
	}

	public InvoiceStatus selectInvoiceStatusDeleted() {
		return this.select(INVOICE_STATUS_DELETED);
	}

	public void save(final InvoiceStatus invoiceStatus) {
		this.dao.save(invoiceStatus);
	}

	private InvoiceStatusBLL() throws IOException {
		super();
		this.dao = new InvoiceStatusDAO();
	}
}