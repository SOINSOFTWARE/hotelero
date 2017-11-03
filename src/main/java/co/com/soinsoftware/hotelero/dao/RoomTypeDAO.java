package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 19/09/2017
 * @version 1.0.1
 */
public class RoomTypeDAO extends AbstractDAO<RoomType> {

	public RoomTypeDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<RoomType> select() {
		final List<RoomType> roomTypeList = manager.createQuery(
				this.getSelectStatementEnabled()).getResultList();
		return (roomTypeList != null) ? roomTypeList : new ArrayList<>();
	}

	public RoomType select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(RoomType.class).load(code);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_ROOM_TYPE);
		return query.toString();
	}
}
