package co.com.soinsoftware.hotelero.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "invoice")
@Indexed
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Invoice extends CommonData implements Comparable<Invoice> {

	private static final long serialVersionUID = 3003481043888000893L;

	@ManyToOne
	@JoinColumn(name = "idcompany")
	private Company company;

	@ManyToOne
	@JoinColumn(name = "idinvoicestatus")
	private InvoiceStatus invoiceStatus;

	@ManyToOne
	@JoinColumn(name = "idroom")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "idroomstatus")
	private RoomStatus roomStatus;

	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "initialdate")
	private Date initialDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "finaldate")
	private Date finalDate;

	private long value;

	@Column(name = "sitefrom")
	private String siteFrom;

	@Column(name = "siteto")
	private String siteTo;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@IndexedEmbedded(prefix = "invoiceitem.idinvoice")
	private Set<Invoiceitem> invoiceItems;

	@Transient
	private boolean delete;

	public Invoice() {
		super();
		invoiceItems = new HashSet<>(0);
	}

	public Invoice(final InvoiceStatus invoiceStatus, final Room room, final RoomStatus roomStatus, final User user,
			final Date initialDate, final long value, final Date creation, final Date updated, final boolean enabled) {
		super(creation, updated, enabled);
		this.invoiceStatus = invoiceStatus;
		this.room = room;
		this.roomStatus = roomStatus;
		this.user = user;
		this.initialDate = initialDate;
		this.value = value;
		this.invoiceItems = new HashSet<>(0);
		this.delete = false;
	}

	public Invoice(final Company company, final InvoiceStatus invoiceStatus, final Room room,
			final RoomStatus roomStatus, final User user, final Date initialDate, final Date finalDate,
			final long value, final String siteFrom, final String siteTo, final Date creation, final Date updated,
			final boolean enabled, final Set<Invoiceitem> invoiceItems) {
		super(creation, updated, enabled);
		this.company = company;
		this.invoiceStatus = invoiceStatus;
		this.room = room;
		this.roomStatus = roomStatus;
		this.user = user;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.value = value;
		this.siteFrom = siteFrom;
		this.siteTo = siteTo;
		this.invoiceItems = invoiceItems;
		this.delete = false;
	}

	@Override
	public int compareTo(final Invoice other) {
		final Date firstDate = (this.initialDate != null) ? this.initialDate : new Date();
		final Date secondDate = (other.initialDate != null) ? other.initialDate : new Date();
		return firstDate.compareTo(secondDate);
	}
}