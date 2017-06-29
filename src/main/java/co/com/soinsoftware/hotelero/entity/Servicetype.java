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
public class Servicetype implements Serializable, Comparable<Servicetype> {

	private static final long serialVersionUID = 7265381353242674545L;

	private Integer id;

	private String name;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Service> services = new HashSet<>(0);

	private volatile String newName;

	private volatile boolean delete;

	public Servicetype() {
		super();
		this.delete = false;
	}

	public Servicetype(final String name, final Date creation,
			final Date updated, final boolean enabled) {
		this.name = name;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.delete = false;
	}

	public Servicetype(final String name, final Date creation,
			final Date updated, final boolean enabled,
			final Set<Service> services) {
		this.name = name;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.services = services;
		this.delete = false;
	}

	@Override
	public int compareTo(final Servicetype other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}