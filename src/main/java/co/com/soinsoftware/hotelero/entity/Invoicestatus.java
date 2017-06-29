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
public class Invoicestatus implements Serializable {

	private static final long serialVersionUID = -3420416719346031887L;

	private Integer id;

	private String name;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoice> invoices = new HashSet<>(0);

	public Invoicestatus() {
		super();
	}

	public Invoicestatus(final String name, final Date creation,
			final Date updated, final boolean enabled) {
		this.name = name;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
	}

	public Invoicestatus(final String name, final Date creation,
			final Date updated, final boolean enabled,
			final Set<Invoice> invoices) {
		this.name = name;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.invoices = invoices;
	}
}