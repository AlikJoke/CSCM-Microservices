package ru.project.cscm.ws.core.resources;

import ru.project.cscm.dto.items.common.Currency;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResource {

	private final Integer id;
	private final String currencyCode;
	private final String currency;

	@JsonCreator
	public CurrencyResource(@JsonProperty("id") Integer id,
			@JsonProperty("currencyCode") String currencyCode,
			@JsonProperty("currency") String currency) {
		super();
		this.id = id;
		this.currencyCode = currencyCode;
		this.currency = currency;
	}
	
	public CurrencyResource(Currency currency) {
		this.id = currency.getId();
		this.currencyCode = currency.getCurrencyCode();
		this.currency = currency.getCurrency();
	}

	public Integer getId() {
		return id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getCurrency() {
		return currency;
	}

}
