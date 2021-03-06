package ru.project.cscm.dto.items.monitoring;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class AtmRecyclingCassetteItem extends AtmCassetteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private int amountOut;
	private int amountIn;
	private int amountInit;
	private int amountLeft;
	private int amountLeftFE;
	private String codeA3;
	private String pbClass;
	
	public void setAmountInit(int amountInit) {
		this.amountInit = amountInit;
	}

	public int getAmountInit() {
		return amountInit;
	}

	public void setCodeA3(String codeA3) {
		this.codeA3 = codeA3;
	}

	public String getCodeA3() {
		return codeA3;
	}

	public void setPbClass(String pbClass) {
		this.pbClass = pbClass;
	}

	public String getPbClass() {
		return pbClass;
	}

	public int getAmountOut() {
		return amountOut;
	}

	public void setAmountOut(int amountOut) {
		this.amountOut = amountOut;
	}

	public int getAmountIn() {
		return amountIn;
	}

	public void setAmountIn(int amountIn) {
		this.amountIn = amountIn;
	}

	public int getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(int amountLeft) {
		this.amountLeft = amountLeft;
	}

	public int getAmountLeftFE() {
		return amountLeftFE;
	}

	public void setAmountLeftFE(int amountLeftFE) {
		this.amountLeftFE = amountLeftFE;
	}

	public boolean isBalanceAlert() {
		return super.isBalanceAlert();
	}

	public void setBalanceAlert(boolean balanceAlert) {
		super.setBalanceAlert(balanceAlert);
	}
}
