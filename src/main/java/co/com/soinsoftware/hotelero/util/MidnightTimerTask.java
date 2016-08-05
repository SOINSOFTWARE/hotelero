package co.com.soinsoftware.hotelero.util;

import java.util.Date;
import java.util.TimerTask;

import co.com.soinsoftware.hotelero.view.JFRoom;

/**
 * @author Carlos Rodriguez
 * @since 04/08/2016
 * @version 1.0
 */
public class MidnightTimerTask extends TimerTask {

	public final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;

	private final static int MIDNIGHT = 0;

	private final static int ZERO_MINUTES = 0;

	private final JFRoom jfRoom;

	public MidnightTimerTask(final JFRoom jfRoom) {
		super();
		this.jfRoom = jfRoom;
	}

	@Override
	public void run() {
		jfRoom.refresh();
	}

	@SuppressWarnings("deprecation")
	public static Date getMidnightDate() {
		final Date midnightDate = new Date();
		midnightDate.setHours(MIDNIGHT);
		midnightDate.setMinutes(ZERO_MINUTES);
		return midnightDate;
	}
}