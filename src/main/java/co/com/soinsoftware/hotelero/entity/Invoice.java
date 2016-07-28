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

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(final Company company) {
		this.company = company;
	}

	public Invoicestatus getInvoicestatus() {
		return this.invoicestatus;
	}

	public void setInvoicestatus(final Invoicestatus invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(final Room room) {
		this.room = room;
	}

	public Roomstatus getRoomstatus() {
		return this.roomstatus;
	}

	public void setRoomstatus(final Roomstatus roomstatus) {
		this.roomstatus = roomstatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Date getInitialdate() {
		return this.initialdate;
	}

	public void setInitialdate(final Date initialdate) {
		this.initialdate = initialdate;
	}

	public Date getFinaldate() {
		return this.finaldate;
	}

	public void setFinaldate(final Date finaldate) {
		this.finaldate = finaldate;
	}

	public long getValue() {
		return this.value;
	}

	public void setValue(final long value) {
		this.value = value;
	}

	public String getSitefrom() {
		return this.sitefrom;
	}

	public void setSitefrom(final String sitefrom) {
		this.sitefrom = sitefrom;
	}

	public String getSiteto() {
		return this.siteto;
	}

	public void setSiteto(final String siteto) {
		this.siteto = siteto;
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
		result = prime * result
				+ ((finaldate == null) ? 0 : finaldate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((initialdate == null) ? 0 : initialdate.hashCode());
		result = prime * result
				+ ((sitefrom == null) ? 0 : sitefrom.hashCode());
		result = prime * result + ((siteto == null) ? 0 : siteto.hashCode());
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
		Invoice other = (Invoice) obj;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (enabled != other.enabled)
			return false;
		if (finaldate == null) {
			if (other.finaldate != null)
				return false;
		} else if (!finaldate.equals(other.finaldate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (initialdate == null) {
			if (other.initialdate != null)
				return false;
		} else if (!initialdate.equals(other.initialdate))
			return false;
		if (sitefrom == null) {
			if (other.sitefrom != null)
				return false;
		} else if (!sitefrom.equals(other.sitefrom))
			return false;
		if (siteto == null) {
			if (other.siteto != null)
				return false;
		} else if (!siteto.equals(other.siteto))
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
	public int compareTo(final Invoice other) {
		return this.initialdate.compareTo(other.initialdate);
	}
}