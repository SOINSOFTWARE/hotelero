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

	public long getIdentification() {
		return this.identification;
	}

	public void setIdentification(final long identification) {
		this.identification = identification;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public long getPhone() {
		return this.phone;
	}

	public void setPhone(final long phone) {
		this.phone = phone;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(final String career) {
		this.career = career;
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

	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(final Set<Invoice> invoices) {
		this.invoices = invoices;
	}
}