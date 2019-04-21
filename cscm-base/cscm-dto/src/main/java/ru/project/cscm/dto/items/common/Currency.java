package ru.project.cscm.dto.items.common;

public class Currency {

	private Integer id;
	private String currencyCode;
	private String currency;

	public Currency(Integer id, String currencyCode, String currency) {
		super();
		this.id = id;
		this.currencyCode = currencyCode;
		this.currency = currency;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
