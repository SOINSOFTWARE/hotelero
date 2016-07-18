package co.com.soinsoftware.hotelero.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class Company implements Serializable {

	private static final long serialVersionUID = 4296349483883760312L;

	private Integer id;

	private String name;

	private String nit;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoice> invoices = new HashSet<>(0);

	private Set<User> users = new HashSet<>(0);

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

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(final String nit) {
		this.nit = nit;
	}

	public Date getCreation() {
		return this.creation;
	}

	public void setCreation(final Date creation) {
		this.creation = creation;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(final Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(final Set<User> users) {
		this.users = users;
	}
}