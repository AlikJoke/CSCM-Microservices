package ru.project.cscm.dto.items.monitoring;

public final class BalanceItem {

	private final String atmId;
	private final String cassType;
	private final String cassNumber;
	private final String remainingLoad;
	private final String remainingCalc;

	public BalanceItem(String atmId, String cassType, String cassNumber,
			String remainingLoad, String remainingCalc) {
		super();
		this.atmId = atmId;
		this.cassType = cassType;
		this.cassNumber = cassNumber;
		this.remainingLoad = remainingLoad;
		this.remainingCalc = remainingCalc;
	}

	public String getAtmId() {
		return atmId;
	}

	public String getCassType() {
		return cassType;
	}

	public String getCassNumber() {
		return cassNumber;
	}

	public String getRemainingLoad() {
		return remainingLoad;
	}

	public String getRemainingCalc() {
		return remainingCalc;
	}
}