package ru.project.cscm.monitoring.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import ru.project.cscm.monitoring.model.enums.AtmMalfunction;
import ru.project.cscm.monitoring.model.enums.AtmState;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_cscm_atm_state")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY)
public class AtmActualState {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(targetEntity = AtmEncashment.class, mappedBy = "atmState", cascade = CascadeType.ALL)
    private AtmEncashment encashment;

    @OneToOne(targetEntity = AtmCashInCassette.class, mappedBy = "atmState", cascade = CascadeType.ALL)
    private AtmCashInCassette cashInCassette;

    @OneToMany(targetEntity = AtmCashOutCassette.class, mappedBy = "atmState", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AtmCashOutCassette> cashOutCassettes;

    @OneToMany(targetEntity = AtmCashInRecyclingCassette.class, mappedBy = "atmState", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AtmCashInRecyclingCassette> cashInRecyclingCassettes;

    @OneToMany(targetEntity = AtmTransactionsByHour.class, mappedBy = "atmState", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AtmTransactionsByHour> transactionsByHourStatistics;

    @OneToOne(targetEntity = Atm.class)
    @JoinColumn(name = "atm_id")
    private Atm atm;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private AtmState state;

    @Column(name = "malfunction", nullable = false)
    @Enumerated(EnumType.STRING)
    private AtmMalfunction malfunction;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "statDate")
    private Calendar statDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @Column(name = "lastCashOutDate")
    private Calendar lastCashOutDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @Column(name = "lastCashInDate")
    private Calendar lastCashInDate;

    @Column(name = "avgTransactionsInDay")
    private Integer avgTransactionsInDay;

    @Column(name = "isIncidentExists", nullable = false)
    private boolean isIncidentExists;

    @Column(name = "dateToCurrencyEnd")
    @Temporal(TemporalType.DATE)
    private Calendar dateToCurrencyEnd;

    public AtmActualState(Atm atm) {
        super();
        this.atm = atm;
    }

    protected AtmActualState() {
        super();
    }

    public AtmEncashment getEncashment() {
        return encashment;
    }

    public void setEncashment(AtmEncashment encashment) {
        this.encashment = encashment;
    }

    public AtmCashInCassette getCashInCassette() {
        return cashInCassette;
    }

    public void setCashInCassette(AtmCashInCassette cashInCassette) {
        this.cashInCassette = cashInCassette;
    }

    public List<AtmCashOutCassette> getCashOutCassettes() {

        if (this.cashOutCassettes == null) {
            this.cashOutCassettes = new ArrayList<>();
        }

        return cashOutCassettes;
    }

    public void setCashOutCassettes(List<AtmCashOutCassette> cashOutCassettes) {
        this.cashOutCassettes = cashOutCassettes;
    }

    public List<AtmCashInRecyclingCassette> getCashInRecyclingCassettes() {

        if (this.cashInRecyclingCassettes == null) {
            this.cashInRecyclingCassettes = new ArrayList<>();
        }

        return cashInRecyclingCassettes;
    }

    public void setCashInRecyclingCassettes(List<AtmCashInRecyclingCassette> cashInRecyclingCassettes) {
        this.cashInRecyclingCassettes = cashInRecyclingCassettes;
    }

    public AtmState getState() {
        return state;
    }

    public void setState(AtmState state) {
        this.state = state;
    }

    public AtmMalfunction getMalfunction() {
        return malfunction;
    }

    public void setMalfunction(AtmMalfunction malfunction) {
        this.malfunction = malfunction;
    }

    public Integer getLastCashOutHours() {

        if (this.lastCashOutDate == null) {
            return null;
        }

        return (int) (Calendar.getInstance().getTimeInMillis() - this.lastCashOutDate.getTimeInMillis()) / 3_600_000;
    }

    public Integer getLastCashInHours() {

        if (this.lastCashInDate == null) {
            return null;
        }

        return (int) (Calendar.getInstance().getTimeInMillis() - this.lastCashInDate.getTimeInMillis()) / 3_600_000;
    }

    public Integer getAvgTransactionsInDay() {
        return avgTransactionsInDay;
    }

    public void setAvgTransactionsInDay(Integer avgTransactionsInDay) {
        this.avgTransactionsInDay = avgTransactionsInDay;
    }

    public boolean isIncidentExists() {
        return isIncidentExists;
    }

    public void setIncidentExists(boolean isIncidentExists) {
        this.isIncidentExists = isIncidentExists;
    }

    public List<AtmTransactionsByHour> getTransactionsByHourStatistics() {
        return transactionsByHourStatistics;
    }

    public void setTransactionsByHourStatistics(List<AtmTransactionsByHour> transactionsByHourStatistics) {
        this.transactionsByHourStatistics = transactionsByHourStatistics;
    }

    public Calendar getStatDate() {
        return statDate;
    }

    public void setStatDate(Calendar statDate) {
        this.statDate = statDate;
    }

    public Integer getDateToCurrencyEnd() {
        final long currentTime = Calendar.getInstance().getTimeInMillis();
        if (dateToCurrencyEnd.getTimeInMillis() < currentTime) {
            return null;
        }

        return (int) ((this.dateToCurrencyEnd.getTimeInMillis() - currentTime) / (1000 * 60 * 60 * 24));
    }

    public void setDateToCurrencyEnd(Calendar dateToCurrencyEnd) {
        this.dateToCurrencyEnd = dateToCurrencyEnd;
    }

    public void setLastCashOutDate(Calendar lastCashOutDate) {
        this.lastCashOutDate = lastCashOutDate;
    }

    public void setLastCashInDate(Calendar lastCashInDate) {
        this.lastCashInDate = lastCashInDate;
    }

}
