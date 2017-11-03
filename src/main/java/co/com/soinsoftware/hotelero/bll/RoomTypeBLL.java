package co.com.soinsoftware.hotelero.bll;

import java.io.IOException;
import java.util.List;

import co.com.soinsoftware.hotelero.dao.RoomTypeDAO;
import co.com.soinsoftware.hotelero.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 02/11/2017
 * @version 1.0.1
 */
public class RoomTypeBLL {

	private static RoomTypeBLL instance;

	private final RoomTypeDAO dao;

	public static RoomTypeBLL getInstance() throws IOException {
		if (instance == null) {
			instance = new RoomTypeBLL();
		}
		return instance;
	}

	public List<RoomType> select() {
		return this.dao.select();
	}

	public void save(final RoomType roomType) {
		this.dao.save(roomType);
	}

	private RoomTypeBLL() throws IOException {
		super();
		this.dao = new RoomTypeDAO();
	}
}
