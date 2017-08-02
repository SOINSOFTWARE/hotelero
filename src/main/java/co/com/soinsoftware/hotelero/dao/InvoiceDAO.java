package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;

import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.Invoice;
import co.com.soinsoftware.hotelero.entity.InvoiceStatus;
import co.com.soinsoftware.hotelero.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class InvoiceDAO extends AbstractDAO<Invoice> {

	private static final String COLUMN_COMPANY = "company";

	private static final String COLUMN_FINAL_DATE = "finaldate";

	private static final String COLUMN_INITIAL_DATE = "initialdate";

	private static final String COLUMN_INVOICE_STATUS = "invoicestatus";

	private static final String COLUMN_ROOM_STATUS = "roomStatus";

	private static final String PARAM_COMPANY = "company";

	private static final String PARAM_FINAL_DATE = "finalDate";

	private static final String PARAM_INITIAL_DATE = "initialDate";

	private static final String PARAM_INVOICE_STATUS = "invoiceStatus";

	private static final String PARAM_ROOM_STATUS = "roomStatus";

	private static final String PARAM_MONTH = "month";

	private static final String PARAM_YEAR = "year";

	public InvoiceDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> select() {
		final List<Invoice> invoiceList = manager.createQuery(this.getSelectStatementEnabled()).getResultList();
		return (invoiceList != null) ? new HashSet<>(invoiceList) : new HashSet<>();
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> selectBooked(final RoomStatus roomStatusBooked, final Date initialDate) {
		Set<Invoice> invoiceSet = null;
		try {
			final Query query = manager.createQuery(this.getSelectStatementBooked());
			query.setParameter(PARAM_INITIAL_DATE, initialDate);
			query.setParameter(PARAM_ROOM_STATUS, roomStatusBooked);
			invoiceSet = new HashSet<>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> selectNotEnabled(final RoomStatus roomStatusEnabled, final Date initialDate,
			final Date finalDate) {
		Set<Invoice> invoiceSet = null;
		try {
			final Query query = manager.createQuery(this.getSelectStatementNotAvailable());
			query.setParameter(PARAM_INITIAL_DATE, initialDate);
			query.setParameter(PARAM_FINAL_DATE, finalDate);
			query.setParameter(PARAM_ROOM_STATUS, roomStatusEnabled);
			invoiceSet = new HashSet<>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> selectByStatus(final RoomStatus roomStatus) {
		Set<Invoice> invoiceSet = null;
		try {
			final Query query = manager.createQuery(this.getSelectStatementByStatus());
			query.setParameter(PARAM_ROOM_STATUS, roomStatus);
			invoiceSet = new HashSet<>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> select(final int year, final int month, final RoomStatus roomStatusEnabled) {
		Set<Invoice> invoiceSet = new HashSet<>();
		try {
			final Query query = manager.createQuery(this.getSelectStatementByDate());
			query.setParameter(PARAM_YEAR, year);
			query.setParameter(PARAM_MONTH, month);
			query.setParameter(PARAM_ROOM_STATUS, roomStatusEnabled);
			invoiceSet = new HashSet<>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> select(final int year, final int month, final RoomStatus roomStatusEnabled,
			final InvoiceStatus invoiceStatus) {
		Set<Invoice> invoiceSet = new HashSet<>();
		try {
			final Query query = manager.createQuery(this.getSelectStatementByDateInvoiceStatus());
			query.setParameter(PARAM_YEAR, year);
			query.setParameter(PARAM_MONTH, month);
			query.setParameter(PARAM_ROOM_STATUS, roomStatusEnabled);
			query.setParameter(PARAM_INVOICE_STATUS, invoiceStatus);
			invoiceSet = new HashSet<>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@SuppressWarnings("unchecked")
	public Set<Invoice> select(final int year, final int month, final RoomStatus roomStatusEnabled,
			final InvoiceStatus invoiceStatus, final Company company) {
		Set<Invoice> invoiceSet = new HashSet<>();
		try {
			final Query query = manager.createQuery(this.getSelectStatementByDateInvoiceStatusCompany());
			query.setParameter(PARAM_YEAR, year);
			query.setParameter(PARAM_MONTH, month);
			query.setParameter(PARAM_ROOM_STATUS, roomStatusEnabled);
			query.setParameter(PARAM_INVOICE_STATUS, invoiceStatus);
			query.setParameter(PARAM_COMPANY, company);
			invoiceSet = new HashSet<>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return invoiceSet;
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_INVOICE);
		return query.toString();
	}

	private String getSelectStatementBooked() {
		final StringBuilder query = new StringBuilder(this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_GREATER_THAN_WITH_PARAM);
		query.append(PARAM_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(COLUMN_ROOM_STATUS);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_ROOM_STATUS);
		return query.toString();
	}

	private String getSelectStatementNotAvailable() {
		final StringBuilder query = new StringBuilder(this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append("((");
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_BETWEEN);
		query.append(SQL_PARAMETER);
		query.append(PARAM_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(SQL_PARAMETER);
		query.append(PARAM_FINAL_DATE);
		query.append(")");
		query.append(SQL_OR);
		query.append("(");
		query.append(COLUMN_FINAL_DATE);
		query.append(SQL_BETWEEN);
		query.append(SQL_PARAMETER);
		query.append(PARAM_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(SQL_PARAMETER);
		query.append(PARAM_FINAL_DATE);
		query.append(")");
		query.append(SQL_OR);
		query.append("(");
		query.append(SQL_PARAMETER);
		query.append(PARAM_INITIAL_DATE);
		query.append(SQL_BETWEEN);
		query.append(COLUMN_INITIAL_DATE);
		query.append(SQL_AND);
		query.append(COLUMN_FINAL_DATE);
		query.append("))");
		query.append(SQL_AND);
		query.append(COLUMN_ROOM_STATUS);
		query.append(SQL_DISTINCT_WITH_PARAM);
		query.append(PARAM_ROOM_STATUS);
		return query.toString();
	}

	private String getSelectStatementByStatus() {
		final StringBuilder query = new StringBuilder(this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_ROOM_STATUS);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_ROOM_STATUS);
		return query.toString();
	}

	private String getSelectStatementByDate() {
		final StringBuilder query = new StringBuilder(this.getSelectStatementByStatus());
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
		final StringBuilder query = new StringBuilder(this.getSelectStatementByDate());
		query.append(SQL_AND);
		query.append(COLUMN_INVOICE_STATUS);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_INVOICE_STATUS);
		return query.toString();
	}

	private String getSelectStatementByDateInvoiceStatusCompany() {
		final StringBuilder query = new StringBuilder(this.getSelectStatementByDateInvoiceStatus());
		query.append(SQL_AND);
		query.append(COLUMN_COMPANY);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_COMPANY);
		return query.toString();
	}
}