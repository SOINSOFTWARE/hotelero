package co.com.soinsoftware.hotelero.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.Invoicestatus;
import co.com.soinsoftware.hotelero.entity.Roomstatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceDAO extends AbstractDAO {

	private static final String COLUMN_COMPANY = "company";

	private static final String COLUMN_FINAL_DATE = "finaldate";

	private static final String COLUMN_INITIAL_DATE = "initialdate";

	private static final String COLUMN_INVOICE_STATUS = "invoicestatus";

	private static final String COLUMN_ROOM_STATUS = "roomstatus";

	private static final String PARAM_MONTH = "month";

	private static final String PARAM_YEAR = "year";

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

	@SuppressWarnings("unchecked")
	public Set<Invoice> select(final int year, final int month,
			final Roomstatus roomStatusEnabled) {
		Set<Invoice> invoiceSet = new HashSet<>();
		try {
			final Query query = this.createQuery(this
					.getSelectStatementByDate());
			query.setParameter(PARAM_YEAR, year);
			query.setParameter(PARAM_MONTH, month);
			query.setParameter(COLUMN_ROOM_STATUS, roomStatusEnabled);
			invoiceSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoice>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> select(final int year, final int month,
			final Roomstatus roomStatusEnabled,
			final Invoicestatus invoiceStatus) {
		Set<Invoice> invoiceSet = new HashSet<>();
		try {
			final Query query = this.createQuery(this
					.getSelectStatementByDateInvoiceStatus());
			query.setParameter(PARAM_YEAR, year);
			query.setParameter(PARAM_MONTH, month);
			query.setParameter(COLUMN_ROOM_STATUS, roomStatusEnabled);
			query.setParameter(COLUMN_INVOICE_STATUS, invoiceStatus);
			invoiceSet = (query.list().isEmpty()) ? null
					: new HashSet<Invoice>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> select(final int year, final int month,
			final Roomstatus roomStatusEnabled,
			final Invoicestatus invoiceStatus, final Company company) {
		Set<Invoice> invoiceSet = new HashSet<>();
		try {
			final Query query = this.createQuery(this
					.getSelectStatementByDateInvoiceStatusCompany());
			query.setParameter(PARAM_YEAR, year);
			query.setParameter(PARAM_MONTH, month);
			query.setParameter(COLUMN_ROOM_STATUS, roomStatusEnabled);
			query.setParameter(COLUMN_INVOICE_STATUS, invoiceStatus);
			query.setParameter(COLUMN_COMPANY, company);
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

	private String getSelectStatementByDate() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementByStatus());
		query.append(SQL_AND);
		query.append(SQL_YEAR_FUNC);
		query.append("(");
		query.append(COLUMN_INITIAL_DATE);
		query.append(")");
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_YEAR);
		query.append(SQL_AND);
		query.append(SQL_MONTH_FUNC);
		query.append("(");
		query.append(COLUMN_INITIAL_DATE);
		query.append(")");
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_MONTH);
		return query.toString();
	}

	private String getSelectStatementByDateInvoiceStatus() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementByDate());
		query.append(SQL_AND);
		query.append(COLUMN_INVOICE_STATUS);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(COLUMN_INVOICE_STATUS);
		return query.toString();
	}

	private String getSelectStatementByDateInvoiceStatusCompany() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementByDateInvoiceStatus());
		query.append(SQL_AND);
		query.append(COLUMN_COMPANY);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(COLUMN_COMPANY);
		return query.toString();
	}
}