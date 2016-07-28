package co.com.soinsoftware.hotelero.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Roomstatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceDAO extends AbstractDAO {

	private static final String COLUMN_FINAL_DATE = "finaldate";

	private static final String COLUMN_INITIAL_DATE = "initialdate";

	private static final String COLUMN_ROOM_STATUS = "roomstatus";

	@SuppressWarnings("unchecked")
	public Set<Invoice> select() {
		Set<Invoice> invoiceSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementEnabled());
			invoiceSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoice>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> selectBooked(final Roomstatus roomStatusBooked,
			final Date initialDate) {
		Set<Invoice> invoiceSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementBooked());
			query.setParameter(COLUMN_INITIAL_DATE, initialDate);
			query.setParameter(COLUMN_ROOM_STATUS, roomStatusBooked);
			invoiceSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoice>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> selectNotEnabled(final Roomstatus roomStatusEnabled,
			final Date initialDate, final Date finalDate) {
		Set<Invoice> invoiceSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementNotEnabled());
			query.setParameter(COLUMN_INITIAL_DATE, initialDate);
			query.setParameter(COLUMN_FINAL_DATE, finalDate);
			query.setParameter(COLUMN_ROOM_STATUS, roomStatusEnabled);
			invoiceSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoice>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> selectByStatus(final Roomstatus roomStatus) {
		Set<Invoice> invoiceSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementByStatus());
			query.setParameter(COLUMN_ROOM_STATUS, roomStatus);
			invoiceSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoice>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	public void save(final Invoice invoice) {
		boolean isNew = (invoice.getId() == null) ? true : false;
		this.save(invoice, isNew);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_INVOICE);
		return query.toString();
	}

	private String getSelectStatementBooked() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_GREATER_THAN_WITH_PARAM);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(COLUMN_ROOM_STATUS);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(COLUMN_ROOM_STATUS);
		return query.toString();
	}

	private String getSelectStatementNotEnabled() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append("((");
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_BETWEEN);
		query.append(SQL_PARAMETER);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(SQL_PARAMETER);
		query.append(COLUMN_FINAL_DATE);
		query.append(")");
		query.append(SQL_OR);
		query.append("(");
		query.append(COLUMN_FINAL_DATE);
		query.append(SQL_BETWEEN);
		query.append(SQL_PARAMETER);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(SQL_PARAMETER);
		query.append(COLUMN_FINAL_DATE);
		query.append(")");
		query.append(SQL_OR);
		query.append("(");
		query.append(SQL_PARAMETER);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_BETWEEN);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(COLUMN_FINAL_DATE);
		query.append("))");
		query.append(SQL_AND);
		query.append(COLUMN_ROOM_STATUS);
		query.append(SQL_DISTINCT_WITH_PARAM);
		query.append(COLUMN_ROOM_STATUS);
		return query.toString();
	}

	private String getSelectStatementByStatus() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_ROOM_STATUS);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(COLUMN_ROOM_STATUS);
		return query.toString();
	}
}