package ru.project.cscm.ws.core.resources;

import java.util.ArrayList;
import java.util.List;

import ru.project.cscm.dto.items.common.Atm;
import ru.project.cscm.dto.items.common.CmUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtmResource {

	private final int id;
	private final String name;
	private final String street;
	private final String city;
	private final String state;
	private final String instituteId;
	private final String extAtmId;
	private final String fullAddress;
	private final CurrencyResource mainCurrency;
	private final CurrencyResource secondaryCurrency;
	private final CurrencyResource secondary2Currency;
	private final CurrencyResource secondary3Currency;
	private final boolean hasOperator;
	private final List<AtmGroupResource> atmGroups = new ArrayList<>();
	private final String model;

	@JsonCreator
	public AtmResource(
			@JsonProperty("id") Integer atmId,
			@JsonProperty("name") String name,
			@JsonProperty("street") String street,
			@JsonProperty("city") String city,
			@JsonProperty("state") String state,
			@JsonProperty("instituteId") String instituteId,
			@JsonProperty("extAtmId") String extAtmId,
			@JsonProperty("mainCurrency") CurrencyResource mainCurrency,
			@JsonProperty("secondaryCurrency") CurrencyResource secondaryCurrency,
			@JsonProperty("secondary2Currency") CurrencyResource secondary2Currency,
			@JsonProperty("secondary3Currency") CurrencyResource secondary3Currency,
			@JsonProperty("hasOperator") Boolean hasOperator,
			@JsonProperty("model") String model) {
		this.id = atmId;
		this.name = name;
		this.street = street;
		this.instituteId = instituteId;
		this.state = state;
		this.city = city;
		this.model = model;
		this.extAtmId = extAtmId;
		this.fullAddress = CmUtils.getAtmFullAdrress(state, city, street);
		this.mainCurrency = mainCurrency;
		this.secondary2Currency = secondary2Currency;
		this.secondaryCurrency = secondaryCurrency;
		this.secondary3Currency = secondary3Currency;
		this.hasOperator = hasOperator;
	}

	public AtmResource(Atm atm) {
		this.id = atm.getId();
		this.name = atm.getName();
		this.street = atm.getStreet();
		this.instituteId = atm.getInstituteId();
		this.state = atm.getState();
		this.city = atm.getCity();
		this.extAtmId = atm.getExtAtmId();
		this.fullAddress = CmUtils.getAtmFullAdrress(atm.getState(),
				atm.getCity(), atm.getStreet());
		this.mainCurrency = atm.getMainCurrency() == null ? null
				: new CurrencyResource(atm.getMainCurrency());
		this.secondary2Currency = atm.getSecondaryCurrency2() == null ? null
				: new CurrencyResource(atm.getSecondaryCurrency2());
		this.secondaryCurrency = atm.getSecondaryCurrency() == null ? null
				: new CurrencyResource(atm.getSecondaryCurrency());
		this.secondary3Currency = atm.getSecondaryCurrency3() == null ? null
				: new CurrencyResource(atm.getSecondaryCurrency3());
		this.hasOperator = true;
		this.model = atm.getModel();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public String getExtAtmId() {
		return extAtmId;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public CurrencyResource getMainCurrency() {
		return mainCurrency;
	}

	public CurrencyResource getSecondaryCurrency() {
		return secondaryCurrency;
	}

	public CurrencyResource getSecondary2Currency() {
		return secondary2Currency;
	}

	public CurrencyResource getSecondary3Currency() {
		return secondary3Currency;
	}

	public boolean isHasOperator() {
		return hasOperator;
	}

	public List<AtmGroupResource> getAtmGroups() {
		return atmGroups;
	}

	public String getModel() {
		return model;
	}

}
