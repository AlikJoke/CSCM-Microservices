package ru.project.cscm.monitoring.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "t_cscm_atm_cashin_cass")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY)
public class AtmCashInCassette {

	@Column(name = "cassRemaining", nullable = false)
	private Integer cassRemaining;
	
	@Column(name = "cassUploaded", nullable = false)
	private Integer cassUploaded;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar forthcomingDate;
	
	@OneToOne(targetEntity = AtmActualState.class)
	@JoinColumn(name = "atm_state_id")
	private AtmActualState atmState;
	
    @Id
    @GeneratedValue
    private Integer id;

	public AtmCashInCassette(AtmActualState atmState) {
		super();
		this.atmState = atmState;
	}
	
	protected AtmCashInCassette() {
		super();
	}

	public Integer getCassRemaining() {
		return cassRemaining;
	}

	public Integer getCassUploaded() {
		return cassUploaded;
	}

	public Calendar getForthcomingDate() {
		return forthcomingDate;
	}

	public void setCassRemaining(Integer cassRemaining) {
		this.cassRemaining = cassRemaining;
	}

	public void setCassUploaded(Integer cassUploaded) {
		this.cassUploaded = cassUploaded;
	}

	public void setForthcomingDate(Calendar forthcomingDate) {
		this.forthcomingDate = forthcomingDate;
	}

}
