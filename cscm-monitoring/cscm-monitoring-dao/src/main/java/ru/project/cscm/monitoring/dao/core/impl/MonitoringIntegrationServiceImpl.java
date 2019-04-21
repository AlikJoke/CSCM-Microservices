package ru.project.cscm.monitoring.dao.core.impl;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.project.cscm.monitoring.dao.core.AtmActualStateService;
import ru.project.cscm.monitoring.dao.core.CurrencyService;
import ru.project.cscm.monitoring.dao.core.MonitoringIntegrationService;
import ru.project.cscm.monitoring.integration.model.AtmMonitoringStatistics;
import ru.project.cscm.monitoring.integration.model.AtmMonitoringStatisticsItem;
import ru.project.cscm.monitoring.integration.model.CashInRecyclingCassetteInfo;
import ru.project.cscm.monitoring.integration.model.CashOutCassetteInfo;
import ru.project.cscm.monitoring.integration.model.TransactionsStatistics;
import ru.project.cscm.monitoring.model.Atm;
import ru.project.cscm.monitoring.model.AtmActualState;
import ru.project.cscm.monitoring.model.AtmCashInCassette;
import ru.project.cscm.monitoring.model.AtmCashInRecyclingCassette;
import ru.project.cscm.monitoring.model.AtmCashOutCassette;
import ru.project.cscm.monitoring.model.AtmEncashment;
import ru.project.cscm.monitoring.model.AtmTransactionsByHour;
import ru.project.cscm.monitoring.model.Currency;
import ru.project.cscm.monitoring.model.enums.AtmMalfunction;
import ru.project.cscm.monitoring.model.enums.AtmState;

@Service
public class MonitoringIntegrationServiceImpl implements MonitoringIntegrationService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringIntegrationServiceImpl.class);

    @Autowired
    private AtmActualStateService atmService;

    @Autowired
    private CurrencyService currencyService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean load(@NotNull AtmMonitoringStatistics stats) {

        Objects.requireNonNull(stats);

        try {
            stats.getStatistics().getStatistics().forEach(s -> this.processOneItem(s));

            return true;
        } catch (Exception e) {
            logger.error("Exception while loading statistics", e);
        }

        return false;
    }

    private void processOneItem(final AtmMonitoringStatisticsItem statsItem) {

        if (this.atmService.findById(statsItem.getAtmInfo().getAtmId()).isPresent()) {
            this.atmService.deleteById(statsItem.getAtmInfo().getAtmId());
        }

        final Atm atm = new Atm(statsItem.getAtmInfo().getAtmId());
        atm.setAddress(statsItem.getAtmInfo().getAddress());
        atm.setAtmName(statsItem.getAtmInfo().getAtmName());
        atm.setModel(statsItem.getAtmInfo().getModel());

        final AtmActualState atmState = new AtmActualState(atm);
        atm.setAtmState(atmState);

        atmState.setAvgTransactionsInDay(statsItem.getAvgTransactionsInDay());
        atmState.setDateToCurrencyEnd(statsItem.getDateToCurrencyEnd().toGregorianCalendar());
        atmState.setIncidentExists(statsItem.isIsIncidentExists());
        atmState.setLastCashInDate(statsItem.getLastCashInDate().toGregorianCalendar());
        atmState.setLastCashOutDate(statsItem.getLastCashOutDate().toGregorianCalendar());
        atmState.setMalfunction(AtmMalfunction.valueOf(statsItem.getAtmProblem()));
        atmState.setStatDate(statsItem.getStatDate().toGregorianCalendar());
        atmState.setState(AtmState.valueOf(statsItem.getAtmState()));
        atmState.setTransactionsByHourStatistics(statsItem.getTransactionsByHours().getTransactionsByHour().stream()
                .map(t -> this.composeTransactionsByHour(t, atmState)).collect(Collectors.toList()));

        final AtmEncashment encashment = new AtmEncashment(atmState);
        encashment.setEncashmentPlannedDate(statsItem.getEncashmentInfo().getEncashmentPlannedDate().toGregorianCalendar());
        encashment.setLastEncashmentDate(statsItem.getEncashmentInfo().getLastEncashmentDate().toGregorianCalendar());
        encashment.setExpress(statsItem.getEncashmentInfo().isIsExpress());
        encashment.setEncashmentSumm(statsItem.getEncashmentInfo().getEncashmentSumm());

        atmState.setEncashment(encashment);

        final AtmCashInCassette cashInCassette = new AtmCashInCassette(atmState);
        cashInCassette.setCassUploaded(statsItem.getCashInCassette().getCassUploaded());
        cashInCassette.setCassRemaining(statsItem.getCashInCassette().getCassRemaining());
        cashInCassette.setForthcomingDate(statsItem.getCashInCassette().getForthcomingDate().toGregorianCalendar());

        atmState.setCashInCassette(cashInCassette);

        statsItem.getCashOutCassettes().getCashOutCassette().forEach(c -> processCashOutCassette(c, atmState));
        statsItem.getCashInRecyclingCassettes().getCashInRecyclingCassette().forEach(c -> this.processCashInRecyclingCassette(c, atmState));

        this.atmService.save(atm);
    }

    private AtmTransactionsByHour composeTransactionsByHour(TransactionsStatistics transactionsStats, AtmActualState atmState) {

        final AtmTransactionsByHour th = new AtmTransactionsByHour(atmState);
        th.setHour(transactionsStats.getHour());
        th.setTransactionsCount(transactionsStats.getTransactionsCount());

        return th;
    }

    private void processCashInRecyclingCassette(CashInRecyclingCassetteInfo c, AtmActualState atmState) {

        final AtmCashInRecyclingCassette cassette = new AtmCashInRecyclingCassette(atmState);
        cassette.setCassRemaining(c.getRemaining());
        cassette.setCassUploaded(c.getUploaded());
        cassette.setCassNumber(c.getNumber());

        atmState.getCashInRecyclingCassettes().add(cassette);
    }

    private void processCashOutCassette(CashOutCassetteInfo c, AtmActualState atmState) {

        final AtmCashOutCassette cassette = new AtmCashOutCassette(atmState, prepareCurrency(c.getCurrency()), c.getNumber());
        cassette.setCassRemaining(c.getRemaining());
        cassette.setCassUploaded(c.getUploaded());
        cassette.setDemandValue(c.getDemand());
        cassette.setDenomination(c.getDenomination());
        cassette.setExhaustionDate(c.getExhaustionDate().toGregorianCalendar());
        cassette.setIsWorking(c.isIsWorking());
        cassette.setNotesCount(c.getNotesCount());

        atmState.getCashOutCassettes().add(cassette);
    }

    private Currency prepareCurrency(final ru.project.cscm.monitoring.integration.model.Currency currency) {

        Currency currencyInStorage = this.currencyService.findById(currency.getId()).orElse(null);
        if (currencyInStorage == null) {
            currencyInStorage = this.currencyService.save(new Currency(currency.getId(), currency.getCode(), currency.getDescription()));
        }

        return currencyInStorage;
    }
}
