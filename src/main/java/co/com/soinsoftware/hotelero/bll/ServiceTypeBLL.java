package co.com.soinsoftware.hotelero.bll;

import java.io.IOException;
import java.util.Set;

import co.com.soinsoftware.hotelero.dao.ServiceTypeDAO;
import co.com.soinsoftware.hotelero.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class ServiceTypeBLL {

	private static ServiceTypeBLL instance;

	private final ServiceTypeDAO dao;

	public static ServiceTypeBLL getInstance() throws IOException {
		if (instance == null) {
			instance = new ServiceTypeBLL();
		}
		return instance;
	}

	public Set<ServiceType> select() {
		return this.dao.select();
	}
	
	public void save(final ServiceType serviceType) {
		this.dao.save(serviceType);
	}

	private ServiceTypeBLL() throws IOException {
		super();
		this.dao = new ServiceTypeDAO();
	}
}