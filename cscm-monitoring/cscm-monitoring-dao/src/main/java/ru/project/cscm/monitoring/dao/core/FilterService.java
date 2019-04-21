package ru.project.cscm.monitoring.dao.core;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.project.cscm.monitoring.integration.model.MonitoringFilter;
import ru.project.cscm.monitoring.model.MonitoringFilterId;

@Repository
public interface FilterService extends CrudRepository<MonitoringFilter, MonitoringFilterId> {

    @Query("select f from monitoring_filter f where f.id.user = :user")
    List<MonitoringFilter> findAllByUser(@Param("user") @NotEmpty String user);
    
    @Query("select count(f) from monitoring_filter f where f.id.user = :user")
    Integer findCountAllByUser(@Param("user") @NotEmpty String user);
}
