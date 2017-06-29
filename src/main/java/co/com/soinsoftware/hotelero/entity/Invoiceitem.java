package co.com.soinsoftware.hotelero.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
@Data
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

	@Override
	public int compareTo(final Invoiceitem other) {
		final Date firstDate = (this.invoiceitemdate != null) ? this.invoiceitemdate
				: new Date();
		final Date secondDate = (other.invoiceitemdate != null) ? other.invoiceitemdate
				: new Date();
		return firstDate.compareTo(secondDate);
	}
}