package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.InvoiceStatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceStatusDAO extends AbstractDAO<InvoiceStatus> {

	public InvoiceStatusDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<InvoiceStatus> select() {
		final List<InvoiceStatus> invoiceStatusList = manager.createQuery(this.getSelectStatementEnabled())
				.getResultList();
		return (invoiceStatusList != null) ? new HashSet<>(invoiceStatusList) : new HashSet<>();
	}

	public InvoiceStatus select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(InvoiceStatus.class).load(name);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_INVOICE_STATUS);
		return query.toString();
	}
}