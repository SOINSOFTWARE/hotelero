package co.com.soinsoftware.hotelero.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
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
@Entity(name = "service")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Service extends CommonData implements Comparable<Service> {

	private static final long serialVersionUID = -5260330528451334307L;

	private String name;

	private long value;
	
	@ManyToOne
	@JoinColumn(name = "idservicetype")
	private ServiceType serviceType;

	@Transient
	private String newName;

	@Transient
	private long newValue;

	@Transient
	private boolean delete;

	public Service() {
		super();
	}

	public Service(final ServiceType serviceType, final String name, final long value, final Date creation,
			final Date updated, final boolean enabled) {
		super(creation, updated, enabled);
		this.serviceType = serviceType;
		this.name = name;
		this.value = value;
		this.delete = false;
	}

	@Override
	public int compareTo(final Service other) {
		final String firstName = (this.name != null) ? this.name : "";
		final String secondName = (other.name != null) ? other.name : "";
		return firstName.compareToIgnoreCase(secondName);
	}
}