package co.com.soinsoftware.hotelero.entity;

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
 * @since 02/08/2017
 * @since 1.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "floor")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Floor extends CommonData {

	private static final long serialVersionUID = 3173778659449996444L;

	@NaturalId
	private String code;

	@NaturalId
	private String name;
}