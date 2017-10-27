package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.com.soinsoftware.hotelero.entity.RoomTypeTariff;

/**
 * @author Carlos Rodriguez
 * @since 20/09/2017
 * @version 1.0.1
 */
public class RoomTypeTariffDAO extends AbstractDAO<RoomTypeTariff> {

	public RoomTypeTariffDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<RoomTypeTariff> select() {
		final List<RoomTypeTariff> roomTypeTariffList = manager.createQuery(
				this.getSelectStatementEnabled()).getResultList();
		return (roomTypeTariffList != null) ? new HashSet<>(roomTypeTariffList)
				: new HashSet<>();
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_ROOM_TYPE_TARIFF);
		return query.toString();
	}
}
