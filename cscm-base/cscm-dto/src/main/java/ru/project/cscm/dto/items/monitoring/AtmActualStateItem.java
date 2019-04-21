package ru.project.cscm.dto.items.monitoring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.project.cscm.dto.items.common.CmUtils;
import ru.project.cscm.dto.items.enums.AtmMalfunction;
import ru.project.cscm.dto.items.enums.AtmState;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class AtmActualStateItem {

	private final String state;
	private final String city;
	private final String street;
	private final AtmState atmState;
	private final AtmMalfunction malfunction;
	private final Integer avgTransactionsInHour;
	private final Integer avgTransactionsInDay;
	private final int atmID;
	private final String model;
	private final String extAtmId;

	private final List<AtmCashOutCassetteItem> cashOutCassettes = new ArrayList<AtmCashOutCassetteItem>();
	private final List<AtmRecyclingCassetteItem> cashInRCassettes = new ArrayList<AtmRecyclingCassetteItem>();

	private final int cashInCapacity;
	private final int cashInInit;
	private final int cashInLeft;

	private final Date statDate;
	private final Date statLoadDate;
	private final int cashInRInit;

	private final Date outOfCashOutDate;

	private int lastWithdrHours = 0;
	private int lastAddHours = 0;

	private final String atmName;
	private final String desc;

	private final Date dateForthcomingEncashment;
	private final Date lastEncashmentDate;
	private final Date futureEncashmentDate;
	private final Integer plannedEncashmentSumm;
	private final boolean emergencyEncashment;

	private final boolean approved;
	private final Integer encId;
	private final Integer cashInEncId;

	private final boolean atmIsOk = true;
	
    public AtmActualStateItem(Integer cashInEncId, Integer encId, String state, String city, String street, Integer atmState,
			Integer atmMalfunction, Integer atmID, String model,
			Integer cashInCapacity, Date lastEncashmentDate,
			Date futureEncashmentDate, Integer plannedEncashmentSumm,
			Integer cashInInit, Integer cashInLeft, Integer rejectInit,
			Integer rejectLeft, Date statDate, Date statLoadDate,
			Integer cashInRInit,
			Integer lastWithdrHours, Integer lastAddHours, String atmName,
			Date dateForthcomingEncashment, Boolean emergencyEncashment,
			Boolean approved,
			Date outOfCashOutDate, Integer avgTransactionsInDay,
			Integer avgTransactionsInHour, String extAtmId) {
		this.cashInEncId = cashInEncId;
		this.atmState = AtmState.value(atmState);
		this.encId = encId;
		this.malfunction = AtmMalfunction.value(atmMalfunction);
		this.model = model;
		this.cashInCapacity = cashInCapacity;
		this.state = state;
		this.city = city;
		this.street = street;
		this.lastEncashmentDate = lastEncashmentDate;
		this.futureEncashmentDate = futureEncashmentDate;
		this.plannedEncashmentSumm = plannedEncashmentSumm;
		this.avgTransactionsInDay = avgTransactionsInDay;
		this.avgTransactionsInHour = avgTransactionsInHour;

		this.extAtmId = extAtmId;
		this.atmID = atmID;
		this.desc = CmUtils.getAtmFullAdrress(this.state, " " + this.city, " "
				+ this.street);
		this.cashInInit = cashInInit == null ? 0 : cashInInit;
		this.cashInLeft = cashInLeft == null ? 0 : cashInLeft;
		this.statDate = statDate;
		this.statLoadDate = statLoadDate;
		this.cashInRInit = cashInRInit == null ? 0 : cashInRInit;

		this.outOfCashOutDate = outOfCashOutDate;
		this.lastWithdrHours = lastWithdrHours == null ? 0 : lastWithdrHours;
		this.lastAddHours = lastAddHours == null ? 0 : lastAddHours;
		this.atmName = atmName;
		this.dateForthcomingEncashment = dateForthcomingEncashment;
		this.emergencyEncashment = emergencyEncashment == null ? false
				: emergencyEncashment;
		this.approved = approved == null ? false : approved;
	}

	public int getLastWithdrHours() {
		return lastWithdrHours;
	}

	public void setLastWithdrHours(int lastWithdrHours) {
		this.lastWithdrHours = lastWithdrHours;
	}

	public int getLastAddHours() {
		return lastAddHours;
	}

	public void setLastAddHours(int lastAddHours) {
		this.lastAddHours = lastAddHours;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public AtmState getAtmState() {
		return atmState;
	}

	public AtmMalfunction getMalfunction() {
		return malfunction;
	}

	public Date getRurExhaustionDate() {
        return this.getCashOutCassettes().stream().filter(c -> c.getCurr().getCurrencyCode().equalsIgnoreCase("RUR")).findFirst()
                .map(c -> c.getExhaustionDate()).orElse(null);
	}

    public Integer getRurDemandValue() {
        return this.getCashOutCassettes().stream().filter(c -> c.getCurr().getCurrencyCode().equalsIgnoreCase("RUR")).findFirst()
                .map(c -> c.getDemandValue()).orElse(null);
    }

	public Date getUsdExhaustionDate() {
	    return this.getCashOutCassettes().stream().filter(c -> c.getCurr().getCurrencyCode().equalsIgnoreCase("USD")).findFirst()
                .map(c -> c.getExhaustionDate()).orElse(null);
	}

	public Integer getUsdDemandValue() {
	    return this.getCashOutCassettes().stream().filter(c -> c.getCurr().getCurrencyCode().equalsIgnoreCase("USD")).findFirst()
                .map(c -> c.getDemandValue()).orElse(null);
	}

	public Date getEuroExhaustionDate() {
	    return this.getCashOutCassettes().stream().filter(c -> c.getCurr().getCurrencyCode().equalsIgnoreCase("EUR")).findFirst()
                .map(c -> c.getExhaustionDate()).orElse(null);
	}

	public Integer getEuroDemandValue() {
	    return this.getCashOutCassettes().stream().filter(c -> c.getCurr().getCurrencyCode().equalsIgnoreCase("EUR")).findFirst()
                .map(c -> c.getDemandValue()).orElse(null);
	}

	public Integer getAvgTransactionsInHour() {
		return avgTransactionsInHour;
	}

	public Integer getAvgTransactionsInDay() {
		return avgTransactionsInDay;
	}

	public int getAtmID() {
		return atmID;
	}

	public String getModel() {
		return model;
	}

	public List<AtmCashOutCassetteItem> getCashOutCassettes() {
		return cashOutCassettes;
	}

	public List<AtmRecyclingCassetteItem> getCashInRCassettes() {
		return cashInRCassettes;
	}

	public int getCashInCapacity() {
		return cashInCapacity;
	}

	public int getCashInInit() {
		return cashInInit;
	}

	public int getCashInLeft() {
		return cashInLeft;
	}

	public Date getStatDate() {
		return statDate;
	}

	public Date getStatLoadDate() {
		return statLoadDate;
	}

	public int getCashInRInit() {
		return cashInRInit;
	}

	public Date getOutOfCashOutDate() {
		return outOfCashOutDate;
	}

	public String getAtmName() {
		return atmName;
	}

	public String getDesc() {
		return desc;
	}

	public Date getDateForthcomingEncashment() {
		return dateForthcomingEncashment;
	}

	public Date getLastEncashmentDate() {
		return lastEncashmentDate;
	}

	public Date getFutureEncashmentDate() {
		return futureEncashmentDate;
	}

	public Integer getPlannedEncashmentSumm() {
		return plannedEncashmentSumm;
	}

	public boolean isEmergencyEncashment() {
		return emergencyEncashment;
	}

	public boolean isApproved() {
		return approved;
	}

	public boolean isAtmIsOk() {
		return atmIsOk;
	}

	public Integer getEncId() {
		return this.encId;
	}
	
	public String getExtAtmId() {
	    return this.extAtmId;
	}
	
	public Integer getCashInEncId() {
		return this.cashInEncId;
	}
}
