package co.com.soinsoftware.hotelero.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import co.com.soinsoftware.hotelero.bll.FloorBLL;
import co.com.soinsoftware.hotelero.entity.Floor;

/**
 * @author Carlos Rodriguez
 * @since 23/09/2017
 * @version 1.0.1
 */
public class FloorController {

	private final FloorBLL floorBLL;

	public FloorController() throws IOException {
		super();
		floorBLL = FloorBLL.getInstance();
	}

	public List<Floor> selectFloors() {
		List<Floor> floorList = floorBLL.select();
		if (floorList != null && floorList.size() > 0) {
			Collections.sort(floorList);
		}
		return floorList;
	}

	public void saveFloor(final String code, final String name) {
		final Floor floor = new Floor(code, name);
		saveFloor(floor);
	}

	public void saveFloor(final Floor floor) {
		floorBLL.save(floor);
	}
}
