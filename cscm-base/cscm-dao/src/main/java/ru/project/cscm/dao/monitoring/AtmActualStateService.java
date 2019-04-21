package ru.project.cscm.dao.monitoring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.AtmActualStateMapper;
import ru.project.cscm.dto.items.filters.MonitoringFilter;
import ru.project.cscm.dto.items.monitoring.AtmActualStateItem;

@Service
public class AtmActualStateService extends AbstractService<AtmActualStateMapper> {

    @Autowired
    private AtmCassettesService cassettesService;

    @Override
    protected Class<AtmActualStateMapper> getMapperClass() {
        return AtmActualStateMapper.class;
    }

    public List<AtmActualStateItem> getActualStateList(MonitoringFilter filter) {
        final List<AtmActualStateItem> states = this.getMapper().getAtmActualStateList(filter);

        states.forEach(s -> {

            this.cassettesService.fillAtmCashOutCassettes(s);
            this.cassettesService.fillAtmRecyclingCassettes(s);
        });

        return states;
    }
}
