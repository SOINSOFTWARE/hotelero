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
public class Invoice implements Serializable {

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

	public Invoice() {
		super();
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
}