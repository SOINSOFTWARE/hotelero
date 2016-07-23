/**
 * 
 */
package co.com.soinsoftware.hotelero.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import co.com.soinsoftware.hotelero.bll.ServiceTypeBLL;
import co.com.soinsoftware.hotelero.entity.Servicetype;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class ServiceController {

	private final ServiceTypeBLL serviceTypeBLL;

	public ServiceController() {
		super();
		this.serviceTypeBLL = ServiceTypeBLL.getInstance();
	}

	public List<Servicetype> selectServiceTypes() {
		List<Servicetype> serviceTypeList = new ArrayList<>();
		final Set<Servicetype> serviceTypeSet = this.serviceTypeBLL.select();
		if (serviceTypeSet != null) {
			serviceTypeList = new ArrayList<>(serviceTypeSet);
			Collections.sort(serviceTypeList);
		}
		return serviceTypeList;
	}
	
	public void saveServiceType(final String name) {
		final Date currentDate = new Date();
		final Servicetype serviceType = new Servicetype(name, currentDate, currentDate, true);
		this.saveServiceType(serviceType);
	}
	
	public void saveServiceType(final Servicetype serviceType) {
		this.serviceTypeBLL.save(serviceType);
	}
}