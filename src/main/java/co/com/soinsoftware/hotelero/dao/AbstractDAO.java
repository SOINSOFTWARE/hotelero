package co.com.soinsoftware.hotelero.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Carlos Rodriguez
 * @since 26/05/2016
 * @version 1.0
 */
public abstract class AbstractDAO {
	
	protected static final String COLUMN_ENABLED = "enabled";
	protected static final String COLUMN_ID = "id";

	protected static final String TABLE_COMPANY = "Company";
	protected static final String TABLE_SERVICE = "Service";
	protected static final String TABLE_SERVICE_TYPE = "Servicetype";
	protected static final String TABLE_USER = "User";

	protected static final String SQL_AND = " and ";
	protected static final String SQL_EQUALS_WITH_PARAM = " = :";
	protected static final String SQL_FROM = " from ";
	protected static final String SQL_MONTH_FUNC = " month ";
	protected static final String SQL_SELECT = " select ";
	protected static final String SQL_WHERE = " where ";
	protected static final String SQL_YEAR_FUNC = " year ";

	public Query createQuery(final String queryStatement) {
		final SessionController controller = SessionController.getInstance();
		final Session session = controller.openSession();
		return session.createQuery(queryStatement);
	}

	public void save(final Serializable object, final boolean isNew) {
		final SessionController controller = SessionController.getInstance();
		final Session session = controller.openSession();
		if (isNew) {
			session.save(object);
		}
		session.getTransaction().commit();
	}

	protected abstract String getSelectStatement();
	
	protected String getSelectStatementEnabled() {
		final StringBuilder query = new StringBuilder(this.getSelectStatement());
		query.append(SQL_WHERE);
		query.append(COLUMN_ENABLED);
		query.append(" = 1 ");
		return query.toString();
	}
}