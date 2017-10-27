package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.Tariff;

/**
 * @author Carlos Rodriguez
 * @since 19/09/2017
 * @version 1.0.1
 */
public class TariffDAO extends AbstractDAO<Tariff> {

	public TariffDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<Tariff> select() {
		final List<Tariff> tariffList = manager.createQuery(
				this.getSelectStatementEnabled()).getResultList();
		return (tariffList != null) ? new HashSet<>(tariffList)
				: new HashSet<>();
	}

	public Tariff select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Tariff.class).load(code);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_TARIFF);
		return query.toString();
	}
}
