package ar.edu.unq.tac.market.domain;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity extends AbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTimestamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationTimestamp;

}
