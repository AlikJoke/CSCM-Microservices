package ru.project.cscm.dto.items.monitoring;

import java.io.Serializable;
import java.util.Date;

import ru.project.cscm.dto.items.enums.AtmCassetteType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class AtmCashOutCassetteItem extends AtmCassetteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer amountLeft;
	private Integer amountLeftFE;
	private Integer amountInit;
	private String pbClass;

	private Integer demandValue;
	private Date exhaustionDate;
	
	private boolean emergencyBillsWarning;

    public AtmCashOutCassetteItem() {
		super();
	}

	public AtmCashOutCassetteItem(Integer number, Integer denom,
	        Integer capacity, Integer currId, 
	        String currCode, String currency,
	        AtmCassetteType type, Boolean isCassNotWorking, 
	        Integer notesCount, Integer amountLeft,
	        Integer demandValue, Date exhaustionDate) {
		super(number, denom, capacity, currId, currCode, currency,
				AtmCassetteType.CASH_OUT_CASS, isCassNotWorking, notesCount);
		this.amountLeft = amountLeft;
		this.demandValue = demandValue;
		this.exhaustionDate = exhaustionDate;
	}

	public void setAmountLeft(int amountLeft) {
		this.amountLeft = amountLeft;
	}

	public int getAmountLeft() {
		return amountLeft;
	}

	public Integer getDemandValue() {
        return demandValue;
    }

    public void setDemandValue(Integer demandValue) {
        this.demandValue = demandValue;
    }

    public Date getExhaustionDate() {
        return exhaustionDate;
    }

    public void setExhaustionDate(Date exhaustionDate) {
        this.exhaustionDate = exhaustionDate;
    }

    public int getAmountLeftFE() {
		return amountLeftFE;
	}

	public void setAmountLeftFE(int amountLeftFE) {
		this.amountLeftFE = amountLeftFE;
	}

	public void setAmountInit(int amountInit) {
		this.amountInit = amountInit;
	}

	public int getAmountInit() {
		return amountInit;
	}

	public void setPbClass(String pbClass) {
		this.pbClass = pbClass;
	}

	public String getPbClass() {
		return pbClass;
	}

	public boolean isBalanceAlert() {
		return super.isBalanceAlert();
	}

	public void setBalanceAlert(boolean balanceAlert) {
		super.setBalanceAlert(balanceAlert);
	}

	public boolean isEmergencyBillsWarning() {
		return emergencyBillsWarning;
	}

	public void setEmergencyBillsWarning(boolean emergencyBillsWarning) {
		this.emergencyBillsWarning = emergencyBillsWarning;
	}

}
