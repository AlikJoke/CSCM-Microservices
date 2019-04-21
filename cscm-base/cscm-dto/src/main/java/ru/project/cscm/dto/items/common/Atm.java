package ru.project.cscm.dto.items.common;

import ru.project.cscm.dto.items.core.IdentifiableObject;

public class Atm implements IdentifiableObject<Integer>, Comparable<Atm> {

	private final Integer id;
	private String name;
	private String street;
	private String city;
	private String state;
	private String instituteId;
	private String extAtmId;
	private String model;

	private Currency mainCurrency;
	private Currency secondaryCurrency;
	private Currency secondaryCurrency2;
	private Currency secondaryCurrency3;

	public Atm(Integer atmId, String name, String street, String city,
			String state, String instituteId, String extAtmId, String model) {
		this.id = atmId;
		this.name = name;
		this.street = street;
		this.instituteId = instituteId;
		this.state = state;
		this.city = city;
		this.extAtmId = extAtmId;
		this.model = model;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Atm obj) {
		return CmUtils.compareIdentifiables(this, obj);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Atm && id == ((Atm) obj).getId();
	}

	@Override
	public int hashCode() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public String getExtAtmId() {
		return extAtmId;
	}

	public void setExtAtmId(String extAtmId) {
		this.extAtmId = extAtmId;
	}

	public Currency getMainCurrency() {
		return mainCurrency;
	}

	public void setMainCurrency(Currency mainCurrency) {
		this.mainCurrency = mainCurrency;
	}

	public Currency getSecondaryCurrency() {
		return secondaryCurrency;
	}

	public void setSecondaryCurrency(Currency secondaryCurrency) {
		this.secondaryCurrency = secondaryCurrency;
	}

	public Currency getSecondaryCurrency2() {
		return secondaryCurrency2;
	}

	public void setSecondaryCurrency2(Currency secondaryCurrency2) {
		this.secondaryCurrency2 = secondaryCurrency2;
	}

	public Currency getSecondaryCurrency3() {
		return secondaryCurrency3;
	}

	public void setSecondaryCurrency3(Currency secondaryCurrency3) {
		this.secondaryCurrency3 = secondaryCurrency3;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
