package co.com.soinsoftware.hotelero.bll;

import java.io.IOException;
import java.util.Set;

import co.com.soinsoftware.hotelero.dao.RoomStatusDAO;
import co.com.soinsoftware.hotelero.entity.RoomStatus;

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

	public static RoomStatusBLL getInstance() throws IOException {
		if (instance == null) {
			instance = new RoomStatusBLL();
		}
		return instance;
	}

	public Set<RoomStatus> select() {
		return this.dao.select();
	}

	public RoomStatus select(final String name) {
		return this.dao.select(name);
	}

	public RoomStatus selectRoomStatusEnabled() {
		return this.select(ROOM_STATUS_ENABLED);
	}

	public RoomStatus selectRoomStatusDisabled() {
		return this.select(ROOM_STATUS_DISABLED);
	}

	public RoomStatus selectRoomStatusBooked() {
		return this.select(ROOM_STATUS_BOOKED);
	}

	public void save(final RoomStatus roomStatus) {
		this.dao.save(roomStatus);
	}

	private RoomStatusBLL() throws IOException {
		super();
		this.dao = new RoomStatusDAO();
	}
}