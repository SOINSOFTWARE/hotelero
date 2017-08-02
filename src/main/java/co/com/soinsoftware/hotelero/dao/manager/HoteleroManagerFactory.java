package co.com.soinsoftware.hotelero.dao.manager;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;

/**
 * This class provides the {@link EntityManagerFactory} to connect to database.
 * 
 * @author Carlos Rodriguez
 * @since 1.0.0
 *
 */
public class HoteleroManagerFactory extends AbstractManagerFactory {

	private static HoteleroManagerFactory instance;

	private static final String PACKAGE_INFO = "co.com.soinsoftware.hotelero.entity";

	private static final String PERSISTENCE_UNIT_NAME = "Hotelero";

	private static final String PROPERTY_FILE = "/connection.properties";

	private HoteleroManagerFactory() throws IOException {
		super(PACKAGE_INFO, PERSISTENCE_UNIT_NAME, PROPERTY_FILE);
	}

	public static HoteleroManagerFactory getInstance() throws IOException {
		if (instance == null) {
			instance = new HoteleroManagerFactory();
		}
		return instance;
	}
}
