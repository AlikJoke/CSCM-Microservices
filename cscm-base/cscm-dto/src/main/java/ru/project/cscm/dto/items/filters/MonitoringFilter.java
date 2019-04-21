package ru.project.cscm.dto.items.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import ru.project.cscm.dto.items.enums.AtmMalfunction;
import ru.project.cscm.dto.items.enums.AtmState;

public class MonitoringFilter {

	private List<AtmState> states;
	private List<AtmMalfunction> malfunctions;
	private Integer daysToCashEnd;
	private List<Integer> cashState;
	private Integer deviceType;
	private List<Integer> encashmentState;
	private String name;
	private Integer id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public MonitoringFilter(Integer id, String name, String description,
			List<AtmState> states, List<AtmMalfunction> malfunctions,
			Integer daysToCashEnd, List<Integer> cashState, Integer deviceType,
			List<Integer> encashmentState) {
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

	public MonitoringFilter(Integer id, String name, String description,
			String states, String malfunctions, Integer daysToCashEnd,
			String cashState, Integer deviceType, String encashmentState) {
		super();
		this.states = StringUtils.isEmpty(states) ? new ArrayList<>() : Arrays
				.stream(states.split(";"))
				.map(s -> AtmState.value(Integer.valueOf(s)))
				.collect(Collectors.toList());

		this.malfunctions = StringUtils.isEmpty(malfunctions) ? new ArrayList<>() : Arrays
				.stream(malfunctions.split(";"))
				.map(s -> AtmMalfunction.value(Integer.valueOf(s)))
				.collect(Collectors.toList());

		this.daysToCashEnd = daysToCashEnd;
		this.cashState = StringUtils.isEmpty(cashState) ? new ArrayList<>() : Arrays
				.stream(cashState.split(";")).map(s -> Integer.valueOf(s))
				.collect(Collectors.toList());

		this.deviceType = deviceType;
		this.encashmentState = StringUtils.isEmpty(encashmentState) ? new ArrayList<>()
				: Arrays.stream(encashmentState.split(";"))
						.map(s -> Integer.valueOf(s))
						.collect(Collectors.toList());
		this.id = id;
		this.description = description;
		this.name = name;
	}
	
	public MonitoringFilter() {
		super();
	}
}
