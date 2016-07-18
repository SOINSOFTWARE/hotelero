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
public class Room implements Serializable {

	private static final long serialVersionUID = 5677610775147934489L;

	private Integer id;

	private String name;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoice> invoices = new HashSet<>(0);

	public Room() {
		super();
	}

	public Room(final String name, final Date creation, final Date updated,
			final boolean enabled) {
		this.name = name;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
	}

	public Room(final String name, final Date creation, final Date updated,
			final boolean enabled, final Set<Invoice> invoices) {
		this.name = name;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.invoices = invoices;
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
}