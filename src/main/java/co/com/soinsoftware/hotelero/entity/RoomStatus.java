package co.com.soinsoftware.hotelero.entity;

import java.util.Date;

import javax.persistence.Entity;

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
@Entity(name = "roomstatus")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class RoomStatus extends CommonData {

	private static final long serialVersionUID = -5695917647318550744L;

	@NaturalId
	private String name;

	public RoomStatus() {
		super();
	}

	public RoomStatus(final String name, final Date creation,
			final Date updated, final boolean enabled) {
		super(creation, updated, enabled);
		this.name = name;
	}
}