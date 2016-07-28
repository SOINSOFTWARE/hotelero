package co.com.soinsoftware.hotelero.bll;

import java.util.Set;

import co.com.soinsoftware.hotelero.dao.RoomStatusDAO;
import co.com.soinsoftware.hotelero.entity.Roomstatus;

/**
 * @author Carlos Rodriguez
 * @since 27/07/2016
 * @version 1.0
 */
public class RoomStatusBLL {

	private static final String ROOM_STATUS_ENABLED = "Disponible";

	private static final String ROOM_STATUS_DISABLED = "Ocupado";

	private static final String ROOM_STATUS_BOOKED = "Reservado";

	private static RoomStatusBLL instance;

	private final RoomStatusDAO dao;

	public static RoomStatusBLL getInstance() {
		if (instance == null) {
			instance = new RoomStatusBLL();
		}
		return instance;
	}

	public Set<Roomstatus> select() {
		return this.dao.select();
	}

	public Roomstatus select(final String name) {
		return this.dao.select(name);
	}

	public Roomstatus selectRoomStatusEnabled() {
		return this.select(ROOM_STATUS_ENABLED);
	}

	public Roomstatus selectRoomStatusDisabled() {
		return this.select(ROOM_STATUS_DISABLED);
	}

	public Roomstatus selectRoomStatusBooked() {
		return this.select(ROOM_STATUS_BOOKED);
	}

	public void save(final Roomstatus roomStatus) {
		this.dao.save(roomStatus);
	}

	private RoomStatusBLL() {
		super();
		this.dao = new RoomStatusDAO();
	}
}