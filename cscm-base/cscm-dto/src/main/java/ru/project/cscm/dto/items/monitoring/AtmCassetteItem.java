package ru.project.cscm.dto.items.monitoring;

import ru.project.cscm.dto.items.common.Currency;
import ru.project.cscm.dto.items.enums.AtmCassetteType;

public class AtmCassetteItem {

    private Integer number;
    private Integer denom;
    private Currency curr;
    private Integer capacity;
    private AtmCassetteType type;
    protected boolean balanceAlert;
    private Boolean isCassNotWorking;
    private Integer notesCount;

    private Integer currCode;
    private String currency;
    private String currCodeA3;

    private boolean capacityResetNeeded = false;

    public AtmCassetteItem() {
        super();
    }

    public boolean isCassNotWorking() {
        return isCassNotWorking;
    }

    public void setCassNotWorking(boolean isCassNotWorking) {
        this.isCassNotWorking = isCassNotWorking;
    }

    public AtmCassetteItem(Integer number, Integer denom, Integer capacity, Integer currId, String currCode, String currency,
            AtmCassetteType type, Boolean isCassNotWorking, Integer notesCount) {
        this.number = number;
        this.denom = denom;
        this.capacity = capacity;
        this.curr = new Currency(currId, currCode, currency);
        this.type = type;
        this.isCassNotWorking = isCassNotWorking;
        this.notesCount = notesCount;
    }

    public AtmCassetteItem(Integer number, Integer denom, Integer capacity, Integer currId, String currCode, String currency, Integer type,
            Boolean isCassNotWorking, Integer notesCount) {
        this.number = number;
        this.denom = denom;
        this.capacity = capacity;
        this.curr = new Currency(currId, currCode, currency);
        this.type = AtmCassetteType.valueOf(type);
        this.notesCount = notesCount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDenom() {
        return denom;
    }

    public void setDenom(int denom) {
        this.denom = denom;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Currency getCurr() {
        if (curr == null) {
            curr = new Currency(this.currCode, this.currCodeA3, this.currency);
        }

        return curr;
    }

    public void setCurr(Currency curr) {
        this.curr = curr;
    }

    public void setCurrCode(Integer currCode) {
        this.currCode = currCode;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCurrCodeA3(String currCodeA3) {
        this.currCodeA3 = currCodeA3;
    }

    public AtmCassetteType getType() {
        return type;
    }

    public void setType(AtmCassetteType type) {
        this.type = type;
    }

    protected boolean isBalanceAlert() {
        return balanceAlert;
    }

    protected void setBalanceAlert(boolean balanceAlert) {
        this.balanceAlert = balanceAlert;
    }

    public boolean isCapacityResetNeeded() {
        return capacityResetNeeded;
    }

    public void setCapacityResetNeeded(boolean capacityResetNeeded) {
        this.capacityResetNeeded = capacityResetNeeded;
    }

    public int getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(int notesCount) {
        this.notesCount = notesCount;
    }

}
