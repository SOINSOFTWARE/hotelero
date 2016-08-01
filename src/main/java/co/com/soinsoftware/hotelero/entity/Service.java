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

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(final Servicetype servicetype) {
		this.servicetype = servicetype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public long getValue() {
		return this.value;
	}

	public void setValue(final long value) {
		this.value = value;
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

	public Set<Invoiceitem> getInvoiceitems() {
		return this.invoiceitems;
	}

	public void setInvoiceitems(final Set<Invoiceitem> invoiceitems) {
		this.invoiceitems = invoiceitems;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(final String newName) {
		this.newName = newName;
	}

	public long getNewValue() {
		return newValue;
	}

	public void setNewValue(long newValue) {
		this.newValue = newValue;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(final boolean delete) {
		this.delete = delete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creation == null) ? 0 : creation.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((invoiceitems == null) ? 0 : invoiceitems.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (enabled != other.enabled)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invoiceitems == null) {
			if (other.invoiceitems != null)
				return false;
		} else if (!invoiceitems.equals(other.invoiceitems))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public int compareTo(final Service other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}