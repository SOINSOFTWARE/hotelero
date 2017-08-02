package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class UserDAO extends AbstractDAO<User> {

	private static final String COLUMN_LOGIN = "login";
	private static final String COLUMN_PASS = "password";
	private static final String PARAM_LOGIN = "login";
	private static final String PARAM_PASS = "password";

	public UserDAO() throws IOException {
		super();
	}

	public User select(final String login, final String password) {
		User user = null;
		try {
			final Query query = manager.createQuery(this.getSelectStatementLogin());
			query.setParameter(PARAM_LOGIN, login);
			query.setParameter(PARAM_PASS, password);
			user = (User) query.getSingleResult();
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return user;
	}

	public User select(final long identification) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(User.class).load(identification);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_USER);
		return query.toString();
	}

	private String getSelectStatementLogin() {
		final StringBuilder query = new StringBuilder(this.getSelectStatement());
		query.append(SQL_WHERE);
		query.append(COLUMN_LOGIN);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_LOGIN);
		query.append(SQL_AND);
		query.append(COLUMN_PASS);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_PASS);
		return query.toString();
	}
}