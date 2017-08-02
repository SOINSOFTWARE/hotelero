package co.com.soinsoftware.hotelero.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "invoiceitem")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Invoiceitem extends CommonData implements Comparable<Invoiceitem> {

	private static final long serialVersionUID = -489119310949259201L;

	@ManyToOne
	@JoinColumn(name = "idinvoice")
	private Invoice invoice;

	@ManyToOne
	@JoinColumn(name = "idservice")
	private Service service;

	private int quantity;

	private long unitvalue;

	private long value;

	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceitemdate;

	@Transient
	private boolean delete;

	public Invoiceitem() {
		super();
	}

	public Invoiceitem(final Invoice invoice, final Service service, final int quantity, final long unitvalue,
			final long value, final Date invoiceitemdate, final Date creation, final Date updated,
			final boolean enabled) {
		super(creation, updated, enabled);
		this.invoice = invoice;
		this.service = service;
		this.quantity = quantity;
		this.unitvalue = unitvalue;
		this.value = value;
		this.invoiceitemdate = invoiceitemdate;
		this.delete = false;
	}

	@Override
	public int compareTo(final Invoiceitem other) {
		final Date firstDate = (this.invoiceitemdate != null) ? this.invoiceitemdate : new Date();
		final Date secondDate = (other.invoiceitemdate != null) ? other.invoiceitemdate : new Date();
		return firstDate.compareTo(secondDate);
	}
}