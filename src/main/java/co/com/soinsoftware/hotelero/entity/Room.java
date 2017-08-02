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
@Entity(name = "room")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Room extends CommonData implements Comparable<Room> {

	private static final long serialVersionUID = 5677610775147934489L;

	@NaturalId
	private String name;

	private long value;

	@Transient
	private long newValue;

	public Room() {
		super();
	}

	public Room(final String name, final long value, final Date creation, final Date updated, final boolean enabled) {
		super(creation, updated, enabled);
		this.name = name;
		this.value = value;
	}

	@Override
	public int compareTo(final Room other) {
		final String firstName = (this.name == null) ? "0" : this.name;
		final String secondName = (other.name == null) ? "0" : other.name;
		return Integer.valueOf(firstName).compareTo(Integer.valueOf(secondName));
	}
}