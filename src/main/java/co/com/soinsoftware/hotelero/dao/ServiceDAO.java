package co.com.soinsoftware.hotelero.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Service;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class ServiceDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Set<Service> select() {
		Set<Service> serviceSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementEnabled());
			serviceSet = (query.list().isEmpty()) ? null
					: new HashSet<Service>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return serviceSet;
	}

	public void save(final Service service) {
		boolean isNew = (service.getId() == null) ? true : false;
		this.save(service, isNew);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_SERVICE);
		return query.toString();
	}
}