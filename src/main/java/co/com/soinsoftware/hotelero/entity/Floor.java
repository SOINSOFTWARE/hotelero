package co.com.soinsoftware.hotelero.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author Carlos Rodriguez
 * @since 02/08/2017
 * @since 1.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "floor")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Floor extends CommonData implements Comparable<Floor> {

	private static final long serialVersionUID = 3173778659449996444L;

	@NaturalId
	private String code;

	private String name;

	@Transient
	private String newCode;

	@Transient
	private String newName;

	@Transient
	private boolean delete;

	public Floor() {
		super();
	}

	public Floor(final String code, final String name) {
		super();
		this.code = code;
		this.name = name;
		this.setEnabled(true);
	}

	@Override
	public int compareTo(final Floor other) {
		return code.compareToIgnoreCase(other.code);
	}
}