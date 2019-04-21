package ru.project.cscm.monitoring.dao.core;

import javax.validation.constraints.NotNull;

import ru.project.cscm.monitoring.integration.model.AtmMonitoringStatistics;

public interface MonitoringIntegrationService {

	boolean load(@NotNull AtmMonitoringStatistics stats);
}
