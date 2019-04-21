package ru.project.cscm.dao.monitoring;

import java.sql.Timestamp;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.AtmCassettesMapper;
import ru.project.cscm.dto.items.enums.AtmCassetteType;
import ru.project.cscm.dto.items.monitoring.AtmActualStateItem;
import ru.project.cscm.dto.items.monitoring.AtmCassetteItem;

@Service
public class AtmCassettesService extends AbstractService<AtmCassettesMapper> {

    @Override
    protected Class<AtmCassettesMapper> getMapperClass() {
        return AtmCassettesMapper.class;
    }

    public AtmCassetteItem findAtmCassette(final Integer atmId, final AtmCassetteType type, final Integer cassetteNumber) {

        return this.getMapper().getAtmCassette(atmId, type.getId(), cassetteNumber);
    }

    public void saveAtmCassette(final Integer atmId, final AtmCassetteItem cassette) {

        this.getMapper().saveAtmCassettes(atmId, cassette.getType().getId(), cassette.getCurr().getId(), cassette.getNumber(),
                cassette.getDenom(), cassette.getCapacity(), cassette.isCassNotWorking() ? 1 : 0, cassette.getNotesCount());
        this.getMapper().flush();
    }

    public void updateAtmCassette(final Integer atmId, final AtmCassetteItem cassette) {

        this.getMapper().updateAtmCassettes(atmId, cassette.getType().getId(), cassette.getNumber(), cassette.isCassNotWorking() ? 1 : 0);
        this.getMapper().flush();
    }

    public void fillAtmCashOutCassettes(final AtmActualStateItem stateItem) {

        stateItem.getCashOutCassettes().addAll(
                this.getMapper().getAtmCashOutCassettesList(stateItem.getAtmID(), AtmCassetteType.CASH_OUT_CASS.getId(),
                        stateItem.getEncId(), new Timestamp(stateItem.getStatDate().getTime())));
    }

    public void fillAtmRecyclingCassettes(final AtmActualStateItem stateItem) {

        stateItem.getCashInRCassettes().addAll(
                this.getMapper().getAtmRecyclingCassettesList(stateItem.getAtmID(), stateItem.getCashInEncId(),
                        new Timestamp(stateItem.getStatDate().getTime()), AtmCassetteType.CASH_IN_R_CASS.getId()));
    }

    public void deleteAtmCassettes(final Integer atmId) {

        this.getMapper().deleteAtmCassettes(atmId);
        this.getMapper().flush();
    }

    public void saveAtmCassettes(final AtmActualStateItem stateItem) {
        Stream.concat(stateItem.getCashOutCassettes().stream(), stateItem.getCashInRCassettes().stream()).forEach(
                c -> this.getMapper().saveAtmCassettes(stateItem.getAtmID(), c.getType().getId(), c.getCurr().getId(), c.getNumber(),
                        c.getDenom(), c.getCapacity(), c.isCassNotWorking() ? 1 : 0, c.getNotesCount()));
        this.getMapper().flush();
    }
}
