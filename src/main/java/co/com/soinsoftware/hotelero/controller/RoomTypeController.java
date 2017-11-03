package co.com.soinsoftware.hotelero.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import co.com.soinsoftware.hotelero.bll.RoomTypeBLL;
import co.com.soinsoftware.hotelero.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 02/11/2017
 * @version 1.0.1
 */
public class RoomTypeController {

	private final RoomTypeBLL roomTypeBLL;

	public RoomTypeController() throws IOException {
		super();
		roomTypeBLL = RoomTypeBLL.getInstance();
	}

	public List<RoomType> selectRoomTypes() {
		List<RoomType> roomTypeList = roomTypeBLL.select();
		if (roomTypeList != null && roomTypeList.size() > 0) {
			Collections.sort(roomTypeList);
		}
		return roomTypeList;
	}

	public void saveRoomType(final String code, final String name) {
		final RoomType roomType = new RoomType(code, name);
		saveRoomType(roomType);
	}

	public void saveRoomType(final RoomType roomType) {
		roomTypeBLL.save(roomType);
	}
}
