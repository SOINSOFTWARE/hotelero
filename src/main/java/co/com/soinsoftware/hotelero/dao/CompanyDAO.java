package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class CompanyDAO extends AbstractDAO<Company> {
	
	public CompanyDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<Company> select() {
		final List<Company> companyList = manager.createQuery(this.getSelectStatementNoFirst()).getResultList();
		return (companyList != null) ? new HashSet<>(companyList) : new HashSet<>();
	}

	public Company select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Company.class).load(name);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_COMPANY);
		return query.toString();
	}

	protected String getSelectStatementNoFirst() {
		final StringBuilder query = new StringBuilder(
				this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_ID);
		query.append(" <> 1 ");
		return query.toString();
	}
}