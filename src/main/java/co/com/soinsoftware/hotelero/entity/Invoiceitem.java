package co.com.soinsoftware.hotelero.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class Invoiceitem implements Serializable, Comparable<Invoiceitem> {

	private static final long serialVersionUID = -489119310949259201L;

	private Integer id;

	private Invoice invoice;

	private Service service;

	private int quantity;

	private long unitvalue;

	private long value;

	private Date invoiceitemdate;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private volatile boolean delete;

	public Invoiceitem() {
		super();
		this.delete = false;
	}

	public Invoiceitem(final Invoice invoice, final Service service,
			final int quantity, final long unitvalue, final long value,
			final Date invoiceitemdate, final Date creation,
			final Date updated, final boolean enabled) {
		this.invoice = invoice;
		this.service = service;
		this.quantity = quantity;
		this.unitvalue = unitvalue;
		this.value = value;
		this.invoiceitemdate = invoiceitemdate;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.delete = false;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(final Service service) {
		this.service = service;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getUnitvalue() {
		return unitvalue;
	}

	public void setUnitvalue(long unitvalue) {
		this.unitvalue = unitvalue;
	}

	public long getValue() {
		return this.value;
	}

	public void setValue(final long value) {
		this.value = value;
	}

	public Date getInvoiceitemdate() {
		return this.invoiceitemdate;
	}

	public void setInvoiceitemdate(final Date invoiceitemdate) {
		this.invoiceitemdate = invoiceitemdate;
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
		result = prime * result + (delete ? 1231 : 1237);
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((invoiceitemdate == null) ? 0 : invoiceitemdate.hashCode());
		result = prime * result + quantity;
		result = prime * result + (int) (unitvalue ^ (unitvalue >>> 32));
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
		Invoiceitem other = (Invoiceitem) obj;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (delete != other.delete)
			return false;
		if (enabled != other.enabled)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invoiceitemdate == null) {
			if (other.invoiceitemdate != null)
				return false;
		} else if (!invoiceitemdate.equals(other.invoiceitemdate))
			return false;
		if (quantity != other.quantity)
			return false;
		if (unitvalue != other.unitvalue)
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
	public int compareTo(final Invoiceitem other) {
		final Date firstDate = (this.invoiceitemdate != null) ? this.invoiceitemdate
				: new Date();
		final Date secondDate = (other.invoiceitemdate != null) ? other.invoiceitemdate
				: new Date();
		return firstDate.compareTo(secondDate);
	}
}