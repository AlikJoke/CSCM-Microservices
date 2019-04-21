package ru.project.cscm.monitoring.dao.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.project.cscm.monitoring.model.Currency;

@Repository
public interface CurrencyService extends CrudRepository<Currency, Integer> {

}
