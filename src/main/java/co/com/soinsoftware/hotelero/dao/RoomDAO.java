package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class RoomDAO extends AbstractDAO<Room> {

	public RoomDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public Set<Room> select() {
		final List<Room> roomList = manager.createQuery(this.getSelectStatementEnabled()).getResultList();
		return (roomList != null) ? new HashSet<>(roomList) : new HashSet<>();
	}

	public Room select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Room.class).load(name);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_ROOM);
		return query.toString();
	}
}