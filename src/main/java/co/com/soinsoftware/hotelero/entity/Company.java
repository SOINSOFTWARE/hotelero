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
public class Company implements Serializable, Comparable<Company> {

	private static final long serialVersionUID = 4296349483883760312L;

	private Integer id;

	private String name;

	private String nit;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoice> invoices = new HashSet<>(0);

	private Set<User> users = new HashSet<>(0);

	private volatile String newName;

	private volatile String newNit;

	private volatile boolean delete;

	public Company() {
		super();
	}

	public Company(final String name, final String nit, final Date creation,
			final Date updated, final boolean enabled) {
		super();
		this.name = name;
		this.nit = nit;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
	}

	public Company(final String name, final String nit, final Date creation,
			final Date updated, final boolean enabled,
			final Set<Invoice> invoices, final Set<User> users) {
		this.name = name;
		this.nit = nit;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.invoices = invoices;
		this.users = users;
	}

	@Override
	public int compareTo(final Company other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}