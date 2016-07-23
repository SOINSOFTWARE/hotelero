package co.com.soinsoftware.hotelero.bll;

import java.util.Set;

import co.com.soinsoftware.hotelero.dao.ServiceTypeDAO;
import co.com.soinsoftware.hotelero.entity.Servicetype;

/**
 * @author Carlos Rodriguez
 * @since 19/07/2016
 * @version 1.0
 */
public class ServiceTypeBLL {

	private static ServiceTypeBLL instance;

	private final ServiceTypeDAO dao;

	public static ServiceTypeBLL getInstance() {
		if (instance == null) {
			instance = new ServiceTypeBLL();
		}
		return instance;
	}

	public Set<Servicetype> select() {
		return this.dao.select();
	}
	
	public void save(final Servicetype serviceType) {
		this.dao.save(serviceType);
	}

	private ServiceTypeBLL() {
		super();
		this.dao = new ServiceTypeDAO();
	}
}