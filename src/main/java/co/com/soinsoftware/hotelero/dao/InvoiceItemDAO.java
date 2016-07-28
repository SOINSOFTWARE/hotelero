package co.com.soinsoftware.hotelero.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoiceitem;

/**
 * @author Carlos Rodriguez
 * @since 28/07/2016
 * @version 1.0
 */
public class InvoiceItemDAO extends AbstractDAO {

	private static final String COLUMN_INVOICE = "invoice";

	@SuppressWarnings("unchecked")
	public Set<Invoiceitem> select() {
		Set<Invoiceitem> invoiceItemSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementEnabled());
			invoiceItemSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoiceitem>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceItemSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoiceitem> selectByInvoice(final Invoice invoice) {
		Set<Invoiceitem> invoiceSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementByInvoice());
			query.setParameter(COLUMN_INVOICE, invoice);
			invoiceSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoiceitem>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	public void save(final Invoiceitem invoiceItem) {
		boolean isNew = (invoiceItem.getId() == null) ? true : false;
		this.save(invoiceItem, isNew);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_INVOICE_ITEM);
		return query.toString();
	}

	private String getSelectStatementByInvoice() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_INVOICE);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(COLUMN_INVOICE);
		return query.toString();
	}
}