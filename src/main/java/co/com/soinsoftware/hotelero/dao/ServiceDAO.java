package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;

import co.com.soinsoftware.hotelero.entity.Service;
import co.com.soinsoftware.hotelero.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class ServiceDAO extends AbstractDAO<Service> {

	private static final String COLUMN_SERVICE_TYPE = "serviceType";
	private static final String PARAM_SERVICE_TYPE = "serviceType";

	public ServiceDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<Service> select() {
		final List<Service> serviceList = manager.createQuery(this.getSelectStatementEnabled()).getResultList();
		return (serviceList != null) ? new HashSet<>(serviceList) : new HashSet<>();
	}

	@SuppressWarnings("unchecked")
	public Set<Service> select(final ServiceType serviceType) {
		Set<Service> serviceSet = null;
		try {
			final Query query = manager.createQuery(this.getSelectStatementServiceType());
			query.setParameter(PARAM_SERVICE_TYPE, serviceType);
			serviceSet = new HashSet<>(query.getResultList());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return serviceSet;
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_SERVICE);
		return query.toString();
	}

	private String getSelectStatementServiceType() {
		final StringBuilder query = new StringBuilder(this.getSelectStatementEnabled());
		query.append(SQL_AND);
		query.append(COLUMN_SERVICE_TYPE);
		query.append(SQL_EQUALS_WITH_PARAM);
		query.append(PARAM_SERVICE_TYPE);
		return query.toString();
	}
}