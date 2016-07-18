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
public class Service implements Serializable {

	private static final long serialVersionUID = -5260330528451334307L;

	private Integer id;

	private Servicetype servicetype;

	private String name;

	private long value;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoiceitem> invoiceitems = new HashSet<>(0);

	public Service() {
		super();
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
}