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
public class Room implements Serializable, Comparable<Room> {

	private static final long serialVersionUID = 5677610775147934489L;

	private Integer id;

	private String name;

	private long value;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoice> invoices = new HashSet<>(0);

	private volatile long newValue;

	public Room() {
		super();
	}

	public Room(final String name, final long value, final Date creation,
			final Date updated, final boolean enabled) {
		this.name = name;
		this.value = value;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
	}

	public Room(final String name, final long value, final Date creation,
			final Date updated, final boolean enabled,
			final Set<Invoice> invoices) {
		this.name = name;
		this.value = value;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.invoices = invoices;
	}

	@Override
	public int compareTo(final Room other) {
		final String firstName = (this.name == null) ? "0" : this.name;
		final String secondName = (other.name == null) ? "0" : other.name;
		return Integer.valueOf(firstName)
				.compareTo(Integer.valueOf(secondName));
	}
}