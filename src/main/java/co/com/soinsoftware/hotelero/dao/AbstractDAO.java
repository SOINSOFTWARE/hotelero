package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import lombok.extern.log4j.Log4j;
import co.com.soinsoftware.hotelero.dao.manager.HoteleroManagerFactory;

/**
 * @author Carlos Rodriguez
 * @since 26/05/2016
 * @version 1.0
 */
@Log4j
public abstract class AbstractDAO<T> {

	protected static final String COLUMN_ENABLED = "enabled";
	protected static final String COLUMN_ID = "id";
	protected static final String COLUMN_NAME = "name";

	protected static final String TABLE_COMPANY = "company";
	protected static final String TABLE_FLOOR = "floor";
	protected static final String TABLE_INVOICE = "invoice";
	protected static final String TABLE_INVOICE_ITEM = "invoiceitem";
	protected static final String TABLE_INVOICE_STATUS = "invoicestatus";
	protected static final String TABLE_ROOM = "room";
	protected static final String TABLE_ROOM_STATUS = "roomstatus";
	protected static final String TABLE_ROOM_TYPE = "roomtype";
	protected static final String TABLE_ROOM_TYPE_TARIFF = "roomtypextariff";
	protected static final String TABLE_SERVICE = "service";
	protected static final String TABLE_SERVICE_TYPE = "servicetype";
	protected static final String TABLE_TARIFF = "tariff";
	protected static final String TABLE_USER = "user";

	protected static final String SQL_AND = " and ";
	protected static final String SQL_BETWEEN = " between ";
	protected static final String SQL_DISTINCT_WITH_PARAM = " <> :";
	protected static final String SQL_EQUALS_WITH_PARAM = " = :";
	protected static final String SQL_FROM = " from ";
	protected static final String SQL_GREATER_THAN_WITH_PARAM = " > :";
	protected static final String SQL_MONTH_FUNC = " month ";
	protected static final String SQL_OR = " or ";
	protected static final String SQL_PARAMETER = ":";
	protected static final String SQL_SELECT = " select ";
	protected static final String SQL_WHERE = " where ";
	protected static final String SQL_YEAR_FUNC = " year ";

	protected EntityManager manager;

	/**
	 * Default constructor that must be used for all DAO implementations.
	 * 
	 * @throws IOException
	 */
	public AbstractDAO() throws IOException {
		super();
		createEntityManager();
	}

	public void save(T record) {
		final EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(record);
			transaction.commit();
		} catch (IllegalStateException | RollbackException ex) {
			log.error("Transaction could not be saved, applying rollback");
			rollbackTransaction(transaction);
		}
		manager.refresh(record);
	}

	/**
	 * When using transaction to store data, use this method to roll back
	 * transaction if anything goes wrong before finish it
	 * 
	 * @param transaction
	 *            {@link EntityTransaction} used.
	 */
	private void rollbackTransaction(final EntityTransaction transaction) {
		if (transaction != null) {
			if (!transaction.isActive()) {
				transaction.begin();
			}
			transaction.rollback();
		}
	}

	/**
	 * Close an application-managed entity manager.
	 */
	public void close() {
		manager.close();
	}

	protected abstract String getSelectStatement();

	protected String getSelectStatementEnabled() {
		final StringBuilder query = new StringBuilder(this.getSelectStatement());
		query.append(SQL_WHERE);
		query.append(COLUMN_ENABLED);
		query.append(" = 1 ");
		return query.toString();
	}

	protected String getSelectStatementName() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_NAME);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(COLUMN_NAME);
		return query.toString();
	}

	protected void createEntityManager() throws IOException {
		final HoteleroManagerFactory emf = HoteleroManagerFactory.getInstance();
		this.manager = emf.createEntityManager();
	}
}