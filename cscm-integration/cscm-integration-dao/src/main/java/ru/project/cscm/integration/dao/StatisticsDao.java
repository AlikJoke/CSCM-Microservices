package ru.project.cscm.integration.dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.project.cscm.integration.model.Statistics;
import ru.project.cscm.integration.model.enums.StatisticsType;

@Repository
public interface StatisticsDao extends CrudRepository<Statistics, Integer> {

    List<Statistics> findAllByType(@NotNull StatisticsType type);
}
