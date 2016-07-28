package co.com.soinsoftware.hotelero.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import co.com.soinsoftware.hotelero.entity.Roomstatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class RoomStatusDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Set<Roomstatus> select() {
		Set<Roomstatus> roomStatusSet = null;
		try {
			final Query query = this.createQuery(this
					.getSelectStatementEnabled());
			roomStatusSet = (query.list().isEmpty()) ? null
					: new HashSet<Roomstatus>(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return roomStatusSet;
	}

	public Roomstatus select(final String name) {
		Roomstatus roomStatus = null;
		try {
			final Query query = this.createQuery(this.getSelectStatementName());
			query.setParameter(COLUMN_NAME, name);
			roomStatus = (query.list().isEmpty()) ? null : (Roomstatus) query
					.list().get(0);
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
		return roomStatus;
	}

	public void save(final Roomstatus roomStatus) {
		boolean isNew = (roomStatus.getId() == null) ? true : false;
		this.save(roomStatus, isNew);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_ROOM_STATUS);
		return query.toString();
	}
}