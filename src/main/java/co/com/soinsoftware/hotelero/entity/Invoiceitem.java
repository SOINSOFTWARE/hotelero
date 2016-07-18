package co.com.soinsoftware.hotelero.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class Invoiceitem implements Serializable {

	private static final long serialVersionUID = -489119310949259201L;

	private Integer id;

	private Invoice invoice;

	private Service service;

	private long value;

	private Date invoiceitemdate;

	private Date creation;

	private Date updated;

	private boolean enabled;

	public Invoiceitem() {
		super();
	}

	public Invoiceitem(final Invoice invoice, final Service service,
			final long value, final Date invoiceitemdate, final Date creation,
			final Date updated, final boolean enabled) {
		this.invoice = invoice;
		this.service = service;
		this.value = value;
		this.invoiceitemdate = invoiceitemdate;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
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
}