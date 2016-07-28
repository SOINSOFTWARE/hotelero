package co.com.soinsoftware.hotelero.bll;

import java.util.Set;

import co.com.soinsoftware.hotelero.dao.RoomDAO;
import co.com.soinsoftware.hotelero.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class RoomBLL {

	private static RoomBLL instance;

	private final RoomDAO dao;

	public static RoomBLL getInstance() {
		if (instance == null) {
			instance = new RoomBLL();
		}
		return instance;
	}

	public Set<Room> select() {
		return this.dao.select();
	}

	public Room select(final String name) {
		return this.dao.select(name);
	}

	public void save(final Room room) {
		this.dao.save(room);
	}

	private RoomBLL() {
		super();
		this.dao = new RoomDAO();
	}
}