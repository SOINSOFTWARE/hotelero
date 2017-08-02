package co.com.soinsoftware.hotelero.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class CommonData implements Serializable {

	private static final long serialVersionUID = -5379808644431949606L;

	@Id
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	private boolean enabled;

	public CommonData() {
		super();
	}

	public CommonData(final Date creation, final Date updated, final boolean enabled) {
		super();
		this.creation = creation;
		this.updated = updated;
		this.enabled = enabled;
	}

	public CommonData(final Integer id, final Date creation, final Date updated, final boolean enabled) {
		this(creation, updated, enabled);
		this.id = id;
	}
}
