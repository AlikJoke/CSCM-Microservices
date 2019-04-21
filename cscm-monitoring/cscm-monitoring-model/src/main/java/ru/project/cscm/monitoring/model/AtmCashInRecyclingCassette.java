package ru.project.cscm.monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "t_cscm_atm_cashin_r_cass")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY)
public class AtmCashInRecyclingCassette {

    @Id
    @GeneratedValue
    private Integer id;
    
	@Column(name = "cassNumber", nullable = false)
	private Integer cassNumber;

	@Column(name = "cassRemaining", nullable = false)
	private Integer cassRemaining;

	@Column(name = "cassUploaded", nullable = false)
	private Integer cassUploaded;

	@ManyToOne(targetEntity = AtmActualState.class)
	@JoinColumn(name = "atm_state_id")
	private AtmActualState atmState;

	public AtmCashInRecyclingCassette(AtmActualState atmState) {
		super();
		this.atmState = atmState;
	}

	protected AtmCashInRecyclingCassette() {
		super();
	}

	public Integer getCassNumber() {
		return cassNumber;
	}

	public void setCassNumber(Integer cassNumber) {
		this.cassNumber = cassNumber;
	}

	public Integer getCassRemaining() {
		return cassRemaining;
	}

	public void setCassRemaining(Integer cassRemaining) {
		this.cassRemaining = cassRemaining;
	}

	public Integer getCassUploaded() {
		return cassUploaded;
	}

	public void setCassUploaded(Integer cassUploaded) {
		this.cassUploaded = cassUploaded;
	}

}
