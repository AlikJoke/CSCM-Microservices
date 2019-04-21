package ru.project.cscm.monitoring.dao.core.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.project.cscm.monitoring.dao.core.AtmActualStateService;
import ru.project.cscm.monitoring.dao.core.AtmService;
import ru.project.cscm.monitoring.integration.model.MonitoringFilter;
import ru.project.cscm.monitoring.model.Atm;

import com.google.common.collect.Lists;

@Service
public class AtmActualStateServiceImpl implements AtmActualStateService {

    @Autowired
    private AtmService atmService;

    @Override
    public <S extends Atm> S save(S entity) {
        return this.atmService.save(entity);
    }

    @Override
    public <S extends Atm> Iterable<S> saveAll(Iterable<S> entities) {
        return this.atmService.saveAll(entities);
    }

    @Override
    public Optional<Atm> findById(String id) {
        return this.atmService.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return this.atmService.existsById(id);
    }

    @Override
    public Iterable<Atm> findAll() {
        return this.atmService.findAll();
    }

    @Override
    public Iterable<Atm> findAllById(Iterable<String> ids) {
        return this.atmService.findAllById(ids);
    }

    @Override
    public long count() {
        return this.atmService.count();
    }

    @Override
    public void deleteById(String id) {
        this.atmService.deleteById(id);
    }

    @Override
    public void delete(Atm entity) {
        this.atmService.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Atm> entities) {
        this.atmService.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        this.atmService.deleteAll();
    }

    @Override
    public List<Atm> findActualStateByFilter(@NotNull MonitoringFilter filter) {
        return Lists.newArrayList(this.atmService.findAll());
    }

}
