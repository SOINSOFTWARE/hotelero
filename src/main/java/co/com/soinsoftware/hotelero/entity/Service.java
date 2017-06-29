package co.com.soinsoftware.hotelero.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
@Data
public class Service implements Serializable, Comparable<Service> {

	private static final long serialVersionUID = -5260330528451334307L;

	private Integer id;

	private Servicetype servicetype;

	private String name;

	private long value;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoiceitem> invoiceitems = new HashSet<>(0);

	private volatile String newName;

	private volatile long newValue;

	private volatile boolean delete;

	public Service() {
		super();
		this.delete = false;
	}

	public Service(final Servicetype servicetype, final String name,
			final long value, final Date creation, final Date updated,
			final boolean enabled) {
		this.servicetype = servicetype;
		this.name = name;
		this.value = value;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.delete = false;
	}

	public Service(final Servicetype servicetype, final String name,
			final long value, final Date creation, final Date updated,
			final boolean enabled, final Set<Invoiceitem> invoiceitems) {
		this.servicetype = servicetype;
		this.name = name;
		this.value = value;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.invoiceitems = invoiceitems;
		this.delete = false;
	}

	@Override
	public int compareTo(final Service other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}