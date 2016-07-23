/**
 * 
 */
package co.com.soinsoftware.hotelero.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import co.com.soinsoftware.hotelero.bll.ServiceBLL;
import co.com.soinsoftware.hotelero.bll.ServiceTypeBLL;
import co.com.soinsoftware.hotelero.entity.Service;
import co.com.soinsoftware.hotelero.entity.Servicetype;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class ServiceController {

	private final ServiceTypeBLL serviceTypeBLL;
	
	private final ServiceBLL serviceBLL;

	public ServiceController() {
		super();
		this.serviceTypeBLL = ServiceTypeBLL.getInstance();
		this.serviceBLL = ServiceBLL.getInstance();
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
		final Servicetype serviceType = new Servicetype(name, currentDate,
				currentDate, true);
		this.saveServiceType(serviceType);
	}

	public void saveServiceType(final Servicetype serviceType) {
		this.serviceTypeBLL.save(serviceType);
	}
	
	public List<Service> selectServices() {
		List<Service> serviceList = new ArrayList<>();
		final Set<Service> serviceSet = this.serviceBLL.select();
		if (serviceSet != null) {
			serviceList = new ArrayList<>(serviceSet);
			Collections.sort(serviceList);
		}
		return serviceList;
	}

	public void saveService(final Servicetype serviceType, final String name,
			final long value) {
		final Date currentDate = new Date();
		final Service service = new Service(serviceType, name, value,
				currentDate, currentDate, true);
		this.saveService(service);
	}

	public void saveService(final Service service) {
		this.serviceBLL.save(service);
	}
}