package co.com.soinsoftware.hotelero.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import co.com.soinsoftware.hotelero.entity.Floor;

/**
 * @author Carlos Rodriguez
 * @since 19/09/2017
 * @version 1.0.1
 */
public class FloorDAO extends AbstractDAO<Floor> {

	public FloorDAO() throws IOException {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Floor> select() {
		final List<Floor> floorList = manager.createQuery(
				this.getSelectStatementEnabled()).getResultList();
		return (floorList != null) ? floorList : new ArrayList<>();
	}

	public Floor select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Floor.class).load(code);
	}

	@Override
	protected String getSelectStatement() {
		final StringBuilder query = new StringBuilder();
		query.append(SQL_FROM);
		query.append(TABLE_FLOOR);
		return query.toString();
	}

}
