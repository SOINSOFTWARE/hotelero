package co.com.soinsoftware.hotelero.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Invoicestatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceStatusDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Set<Invoicestatus> select() {
		Set<Invoicestatus> invoiceStatusSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementEnabled());
			invoiceStatusSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoicestatus>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceStatusSet;
	}

	public Invoicestatus select(final String name) {
		Invoicestatus invoiceStatus = null;
		try {
			final Query query = this.createQuery(this.getSelectStatementName());
			query.setParameter(COLUMN_NAME, name);
			invoiceStatus = (query.list().isEmpty()) ? null : (Invoicestatus) query
					.list().get(0);
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceStatus;
	}

	public void save(final Invoicestatus invoiceStatus) {
		boolean isNew = (invoiceStatus.getId() == null) ? true : false;
		this.save(invoiceStatus, isNew);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_INVOICE_STATUS);
		return query.toString();
	}
}