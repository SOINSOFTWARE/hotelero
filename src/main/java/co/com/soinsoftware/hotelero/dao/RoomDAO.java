package co.com.soinsoftware.hotelero.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class RoomDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Set<Room> select() {
		Set<Room> roomSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementEnabled());
			roomSet = (query.list().isEmpty()) ? null : new HashSet<Room>(
					query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return roomSet;
	}

	public Room select(final String name) {
		Room room = null;
		try {
			final Query query = this.createQuery(this.getSelectStatementName());
			query.setParameter(COLUMN_NAME, name);
			room = (query.list().isEmpty()) ? null : (Room) query.list().get(0);
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return room;
	}

	public void save(final Room room) {
		boolean isNew = (room.getId() == null) ? true : false;
		this.save(room, isNew);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_ROOM);
		return query.toString();
	}
}