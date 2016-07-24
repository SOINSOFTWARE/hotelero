package co.com.soinsoftware.hotelero.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class CompanyDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Set<Company> select() {
		Set<Company> companySet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementNoFirst());
			companySet = (query.list().isEmpty()) ? null
					: new HashSet<Company>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return companySet;
	}

	public void save(final Company company) {
		boolean isNew = (company.getId() == null) ? true : false;
		this.save(company, isNew);
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