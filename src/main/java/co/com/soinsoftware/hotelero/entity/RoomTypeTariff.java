package co.com.soinsoftware.hotelero.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
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
@Entity(name = "roomtypextariff")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class RoomTypeTariff extends CommonData {
	
	private static final long serialVersionUID = -3575020581907337110L;
	
	@Column(name = "fromdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Column(name = "todate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	
	@Column(name = "defaulttariff")
	private boolean defaultTariff;
	
	@ManyToOne
	@JoinColumn(name = "idroomtype")
	private RoomType roomType;
	
	@ManyToOne
	@JoinColumn(name = "idtariff")
	private RoomType tariff;
}