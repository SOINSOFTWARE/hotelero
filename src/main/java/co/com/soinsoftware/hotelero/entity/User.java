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
public class User implements Serializable {

	private static final long serialVersionUID = 8513570707677287722L;

	private Integer id;

	private Company company;

	private long identification;

	private String name;

	private String login;

	private String password;

	private long phone;

	private String career;

	private Date creation;

	private Date updated;

	private boolean enabled;

	private Set<Invoice> invoices = new HashSet<>(0);

	public User() {
		super();
	}

	public User(final long identification, final String name, final long phone,
			final Date creation, final Date updated, final boolean enabled) {
		this.identification = identification;
		this.name = name;
		this.phone = phone;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
	}

	public User(final Company company, final long identification,
			final String name, final String login, final String password,
			final long phone, final String career, final Date creation,
			final Date updated, final boolean enabled,
			final Set<Invoice> invoices) {
		this.company = company;
		this.identification = identification;
		this.name = name;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.career = career;
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
		this.invoices = invoices;
	}
}