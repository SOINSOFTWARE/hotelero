package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class RoomStatusDAO extends AbstractDAO<RoomStatus> {

	public RoomStatusDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<RoomStatus> select() {
		final List<RoomStatus> roomStatusList = manager.createQuery(this.getSelectStatementEnabled()).getResultList();
		return (roomStatusList != null) ? new HashSet<>(roomStatusList) : new HashSet<>();
	}

	public RoomStatus select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(RoomStatus.class).load(name);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_ROOM_STATUS);
		return query.toString();
	}
}