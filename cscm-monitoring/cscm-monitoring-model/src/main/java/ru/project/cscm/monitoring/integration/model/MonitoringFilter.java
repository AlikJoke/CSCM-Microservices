package ru.project.cscm.monitoring.integration.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.StringUtils;

import ru.project.cscm.monitoring.model.MonitoringFilterId;
import ru.project.cscm.monitoring.model.enums.AtmMalfunction;
import ru.project.cscm.monitoring.model.enums.AtmState;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity(name = "monitoring_filter")
@Table(name = "t_cscm_monitoring_filter")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY)
public class MonitoringFilter {

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "t_cscm_monitoring_filter_atm_states")
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private List<AtmState> states;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "t_cscm_monitoring_filter_atm_malfunctions")
    @Column(name = "mulfunction")
    @Enumerated(EnumType.STRING)
    private List<AtmMalfunction> malfunctions;

    @Column(name = "daysToCashEnd")
    private Integer daysToCashEnd;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "t_cscm_monitoring_filter_cash_state")
    @Column(name = "state")
    private List<Integer> cashState;

    @Column(name = "deviceType")
    private Integer deviceType;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "t_cscm_monitoring_filter_encashment_state")
    @Column(name = "state")
    private List<Integer> encashmentState;

    @Column(name = "name", nullable = false)
    private String name;

    @EmbeddedId
    private MonitoringFilterId id;

    @Column(name = "description")
    private String description;

    public List<AtmState> getStates() {
        return states;
    }

    public List<AtmMalfunction> getMalfunctions() {
        return malfunctions;
    }

    public Integer getDaysToCashEnd() {
        return daysToCashEnd;
    }

    public List<Integer> getCashState() {
        return cashState;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public List<Integer> getEncashmentState() {
        return encashmentState;
    }

    public MonitoringFilterId getId() {
        return id;
    }

    public void setId(MonitoringFilterId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MonitoringFilter(MonitoringFilterId id, String name, String description, List<AtmState> states, List<AtmMalfunction> malfunctions,
            Integer daysToCashEnd, List<Integer> cashState, Integer deviceType, List<Integer> encashmentState) {
        super();
        this.states = states;
        this.malfunctions = malfunctions;
        this.daysToCashEnd = daysToCashEnd;
        this.cashState = cashState;
        this.deviceType = deviceType;
        this.encashmentState = encashmentState;
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public MonitoringFilter(MonitoringFilterId id, String name, String description, String states, String malfunctions, Integer daysToCashEnd,
            String cashState, Integer deviceType, String encashmentState) {
        super();
        this.states = StringUtils.isEmpty(states) ? new ArrayList<>() : Arrays.stream(states.split(";"))
                .map(s -> AtmState.value(Integer.valueOf(s))).collect(Collectors.toList());

        this.malfunctions = StringUtils.isEmpty(malfunctions) ? new ArrayList<>() : Arrays.stream(malfunctions.split(";"))
                .map(s -> AtmMalfunction.value(Integer.valueOf(s))).collect(Collectors.toList());

        this.daysToCashEnd = daysToCashEnd;
        this.cashState = StringUtils.isEmpty(cashState) ? new ArrayList<>() : Arrays.stream(cashState.split(";"))
                .map(s -> Integer.valueOf(s)).collect(Collectors.toList());

        this.deviceType = deviceType;
        this.encashmentState = StringUtils.isEmpty(encashmentState) ? new ArrayList<>() : Arrays.stream(encashmentState.split(";"))
                .map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public MonitoringFilter() {
        super();
    }
}
