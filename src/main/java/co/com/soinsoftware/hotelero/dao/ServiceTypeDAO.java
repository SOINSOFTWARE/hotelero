package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.com.soinsoftware.hotelero.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class ServiceTypeDAO extends AbstractDAO<ServiceType> {

	public ServiceTypeDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<ServiceType> select() {
		final List<ServiceType> serviceTypeList = manager.createQuery(this.getSelectStatementEnabled()).getResultList();
		return (serviceTypeList != null) ? new HashSet<>(serviceTypeList) : new HashSet<>();
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_SERVICE_TYPE);
		return query.toString();
	}
}