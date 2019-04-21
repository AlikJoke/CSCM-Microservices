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
@Table(name = "t_cscm_atm_trans")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY)
public class AtmTransactionsByHour {

    @Id
    @GeneratedValue
    private Integer id;
    
	@Column(name = "hour", nullable = false)
	private Integer hour;

	@Column(name = "transactionsCount", nullable = false)
	private Integer transactionsCount;

	@ManyToOne(targetEntity = AtmActualState.class)
	@JoinColumn(name = "atm_state_id")
	private AtmActualState atmState;

	protected AtmTransactionsByHour() {
		super();
	}

	public AtmTransactionsByHour(AtmActualState atmState) {
		this.atmState = atmState;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getTransactionsCount() {
		return transactionsCount;
	}

	public void setTransactionsCount(Integer transactionsCount) {
		this.transactionsCount = transactionsCount;
	}
}
