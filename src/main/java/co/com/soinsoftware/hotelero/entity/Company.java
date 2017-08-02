package co.com.soinsoftware.hotelero.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

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
@Entity(name = "company")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Company extends CommonData implements Comparable<Company> {

	private static final long serialVersionUID = -8618683829658006007L;

	@NaturalId
	private String name;

	private String nit;

	@Transient
	private String newName;

	@Transient
	private String newNit;

	@Transient
	private boolean delete;

	public Company() {
		super();
	}

	public Company(final String name, final String nit, final Date creation, final Date updated,
			final boolean enabled) {
		super(creation, updated, enabled);
		this.name = name;
		this.nit = nit;
	}

	@Override
	public int compareTo(final Company other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}