package co.com.soinsoftware.hotelero.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Servicetype;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class ServiceTypeDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Set<Servicetype> select() {
		Set<Servicetype> serviceTypeSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementEnabled());
			serviceTypeSet = (query.list().isEmpty()) ? null
					: new HashSet<Servicetype>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return serviceTypeSet;
	}

	public void save(final Servicetype serviceType) {
		boolean isNew = (serviceType.getId() == null) ? true : false;
		this.save(serviceType, isNew);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_SERVICE_TYPE);
		return query.toString();
	}
}