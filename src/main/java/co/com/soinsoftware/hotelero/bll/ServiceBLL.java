package co.com.soinsoftware.hotelero.bll;

import java.util.Set;

import co.com.soinsoftware.hotelero.dao.ServiceDAO;
import co.com.soinsoftware.hotelero.entity.Service;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class ServiceBLL {

	private static ServiceBLL instance;

	private final ServiceDAO dao;

	public static ServiceBLL getInstance() {
		if (instance == null) {
			instance = new ServiceBLL();
		}
		return instance;
	}

	public Set<Service> select() {
		return this.dao.select();
	}

	public void save(final Service serviceType) {
		this.dao.save(serviceType);
	}

	private ServiceBLL() {
		super();
		this.dao = new ServiceDAO();
	}
}