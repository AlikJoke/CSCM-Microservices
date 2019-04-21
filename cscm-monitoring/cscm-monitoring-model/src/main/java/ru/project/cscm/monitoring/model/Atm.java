package ru.project.cscm.monitoring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "t_cscm_atm")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE)
@Validated
public class Atm {

	@Column(name = "model", nullable = false)
	@NotEmpty
	private String model;

	@Column(name = "address", nullable = false)
	@NotEmpty
	private String address;

	@Column(name = "atmName", nullable = false)
	@NotEmpty
	private String atmName;

	@Column(name = "extAtmId", nullable = false)
	@Id
	private String extAtmId;

	@OneToOne(targetEntity = AtmActualState.class, mappedBy = "atm", cascade = CascadeType.ALL)
	private AtmActualState atmState;

	protected Atm() {
		super();
	}
	
	public Atm(final String atmId) {
		this.extAtmId = atmId;
	}

	@NotEmpty
	public String getModel() {
		return model;
	}

	@NotEmpty
	public String getAddress() {
		return address;
	}

	@NotEmpty
	public String getAtmName() {
		return atmName;
	}

	public AtmActualState getAtmState() {
		return atmState;
	}

	public void setAtmState(AtmActualState atmState) {
		this.atmState = atmState;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}

}
