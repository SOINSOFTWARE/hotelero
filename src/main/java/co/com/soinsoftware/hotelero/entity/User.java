package co.com.soinsoftware.hotelero.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
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
@Entity(name = "user")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class User extends CommonData {

	private static final long serialVersionUID = 8513570707677287722L;

	@NaturalId
	private long identification;

	private String name;

	private String login;

	private String password;

	private long phone;

	private String career;

	@ManyToOne
	@JoinColumn(name = "idcompany")
	private Company company;

	public User() {
		super();
	}

	public User(final long identification, final String name, final long phone, final Date creation, final Date updated,
			final boolean enabled) {
		super(creation, updated, enabled);
		this.identification = identification;
		this.name = name;
		this.phone = phone;
	}

	public User(final Company company, final long identification, final String name, final long phone,
			final String career, final Date creation, final Date updated, final boolean enabled) {
		this(identification, name, phone, creation, updated, enabled);
		this.company = company;
		this.career = career;
	}

	public User(final Company company, final long identification, final String name, final String login,
			final String password, final long phone, final String career, final Date creation, final Date updated,
			final boolean enabled) {
		this(company, identification, name, phone, career, creation, updated, enabled);
		this.login = login;
		this.password = password;
	}
}