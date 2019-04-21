package ru.project.cscm.monitoring.dao.core;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.repository.NoRepositoryBean;

import ru.project.cscm.monitoring.integration.model.MonitoringFilter;
import ru.project.cscm.monitoring.model.Atm;

@NoRepositoryBean
public interface AtmActualStateService extends AtmService {

    List<Atm> findActualStateByFilter(@NotNull MonitoringFilter filter);
}
