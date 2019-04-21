package ru.project.cscm.monitoring.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "t_cscm_atm_cashout_cass")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY)
public class AtmCashOutCassette {

    @Id
    @GeneratedValue
    private Integer id;
    
	@ManyToOne(targetEntity = AtmActualState.class)
	@JoinColumn(name = "atm_state_id")
	private AtmActualState atmState;

	@Column(name = "denomination", nullable = false)
	private Integer denomination;

	@Column(name = "isWorking", nullable = false)
	private Boolean isWorking;

	@ManyToOne(targetEntity = Currency.class)
	@JoinColumn(name = "currency")
	private Currency currency;

	@Column(name = "cassNumber", nullable = false)
	private Integer cassNumber;

	@Column(name = "cassRemaining", nullable = false)
	private Integer cassRemaining;

	@Column(name = "cassUploaded", nullable = false)
	private Integer cassUploaded;

	@Column(name = "notesCount", nullable = false)
	private Integer notesCount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "exhaustionDate")
	private Calendar exhaustionDate;

	@Column(name = "demandValue")
	private Integer demandValue;

	protected AtmCashOutCassette() {
		super();
	}

	public AtmCashOutCassette(AtmActualState atmState, Currency currency,
			Integer cassNumber) {
		super();
		this.currency = currency;
		this.cassNumber = cassNumber;
		this.atmState = atmState;
	}

	public Integer getDenomination() {
		return denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public Boolean getIsWorking() {
		return isWorking;
	}

	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
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

	public Integer getNotesCount() {
		return notesCount;
	}

	public void setNotesCount(Integer notesCount) {
		this.notesCount = notesCount;
	}

	public Calendar getExhaustionDate() {
		return exhaustionDate;
	}

	public void setExhaustionDate(Calendar exhaustionDate) {
		this.exhaustionDate = exhaustionDate;
	}

	public Integer getDemandValue() {
		return demandValue;
	}

	public void setDemandValue(Integer demandValue) {
		this.demandValue = demandValue;
	}

}
