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
public class Invoice implements Serializable, Comparable<Invoice> {

	private static final long serialVersionUID = 3003481043888000893L;

	private Integer id;

	private Company company;

	private Invoicestatus invoicestatus;

	private Room room;

	private Roomstatus roomstatus;

	private User user;

	private Date initialdate;

	private Date finaldate;

	private long value;

	private String sitefrom;

	private String siteto;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoiceitem> invoiceitems = new HashSet<>(0);

	private volatile boolean delete;

	public Invoice() {
		super();
		this.delete = false;
	}

	public Invoice(final Invoicestatus invoicestatus, final Room room,
			final Roomstatus roomstatus, final User user,
			final Date initialdate, final long value, final Date creation,
			final Date updated, final boolean enabled) {
		this.invoicestatus = invoicestatus;
		this.room = room;
		this.roomstatus = roomstatus;
		this.user = user;
		this.initialdate = initialdate;
		this.value = value;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.delete = false;
	}

	public Invoice(final Company company, final Invoicestatus invoicestatus,
			final Room room, final Roomstatus roomstatus, final User user,
			final Date initialdate, final Date finaldate, final long value,
			final String sitefrom, final String siteto, final Date creation,
			final Date updated, final boolean enabled,
			final Set<Invoiceitem> invoiceitems) {
		this.company = company;
		this.invoicestatus = invoicestatus;
		this.room = room;
		this.roomstatus = roomstatus;
		this.user = user;
		this.initialdate = initialdate;
		this.finaldate = finaldate;
		this.value = value;
		this.sitefrom = sitefrom;
		this.siteto = siteto;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.invoiceitems = invoiceitems;
		this.delete = false;
	}

	@Override
	public int compareTo(final Invoice other) {
		final Date firstDate = (this.initialdate != null) ? this.initialdate
				: new Date();
		final Date secondDate = (other.initialdate != null) ? other.initialdate
				: new Date();
		return firstDate.compareTo(secondDate);
	}
}