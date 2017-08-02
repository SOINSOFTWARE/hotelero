package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;

import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoiceitem;

/**
 * @author Carlos Rodriguez
 * @since 28/07/2016
 * @version 1.0
 */
public class InvoiceItemDAO extends AbstractDAO<Invoiceitem> {

	private static final String COLUMN_INVOICE = "invoice";
	private static final String PARAM_INVOICE = "invoice";

	public InvoiceItemDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<Invoiceitem> select() {
		final List<Invoiceitem> invoiceItemList = manager.createQuery(this.getSelectStatementEnabled()).getResultList();
		return (invoiceItemList != null) ? new HashSet<>(invoiceItemList) : new HashSet<>();
	}

	@SuppressWarnings("unchecked")
	public Set<Invoiceitem> selectByInvoice(final Invoice invoice) {
		Set<Invoiceitem> invoiceSet = null;
		try {
			final Query query = manager.createQuery(this.getSelectStatementByInvoice());
			query.setParameter(PARAM_INVOICE, invoice);
			invoiceSet = new HashSet<Invoiceitem>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_INVOICE_ITEM);
		return query.toString();
	}

	private String getSelectStatementByInvoice() {
		final StringBuilder query = new StringBuilder(this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_INVOICE);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_INVOICE);
		return query.toString();
	}
}