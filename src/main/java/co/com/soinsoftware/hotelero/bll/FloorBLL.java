package co.com.soinsoftware.hotelero.bll;

import java.io.IOException;
import java.util.List;

import co.com.soinsoftware.hotelero.dao.FloorDAO;
import co.com.soinsoftware.hotelero.entity.Floor;

/**
 * @author Carlos Rodriguez
 * @since 23/09/2017
 * @version 1.0.1
 */
public class FloorBLL {

	private static FloorBLL instance;

	private final FloorDAO dao;

	public static FloorBLL getInstance() throws IOException {
		if (instance == null) {
			instance = new FloorBLL();
		}
		return instance;
	}

	public List<Floor> select() {
		return this.dao.select();
	}

	public void save(final Floor floor) {
		this.dao.save(floor);
	}

	private FloorBLL() throws IOException {
		super();
		this.dao = new FloorDAO();
	}
}
