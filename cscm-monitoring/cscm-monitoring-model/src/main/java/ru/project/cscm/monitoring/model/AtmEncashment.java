package ru.project.cscm.monitoring.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "t_cscm_atm_encashment")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY)
@Validated
public class AtmEncashment {

    @Id
    @GeneratedValue
    private Integer id;
    
	@OneToOne(targetEntity = AtmActualState.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "atm_state_id")
	private AtmActualState atmState;

	@Column(name = "isExpress", nullable = false)
	private boolean isExpress;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "encashmentPlannedDate")
	@Temporal(TemporalType.DATE)
	private Calendar encashmentPlannedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "lastEncashmentDate")
	@Temporal(TemporalType.DATE)
	private Calendar lastEncashmentDate;

	@Column(name = "encashmentSumm")
	private Double encashmentSumm;

	protected AtmEncashment() {
		super();
	}

	public AtmEncashment(@NotNull AtmActualState atmState) {
		this.atmState = atmState;
	}

	public boolean isExpress() {
		return isExpress;
	}

	public void setExpress(boolean isExpress) {
		this.isExpress = isExpress;
	}

	public Calendar getEncashmentPlannedDate() {
		return encashmentPlannedDate;
	}

	public void setEncashmentPlannedDate(Calendar encashmentPlannedDate) {
		this.encashmentPlannedDate = encashmentPlannedDate;
	}

	public Calendar getLastEncashmentDate() {
		return lastEncashmentDate;
	}

	public void setLastEncashmentDate(Calendar lastEncashmentDate) {
		this.lastEncashmentDate = lastEncashmentDate;
	}

	public Double getEncashmentSumm() {
		return encashmentSumm;
	}

	public void setEncashmentSumm(Double encashmentSumm) {
		this.encashmentSumm = encashmentSumm;
	}
}
