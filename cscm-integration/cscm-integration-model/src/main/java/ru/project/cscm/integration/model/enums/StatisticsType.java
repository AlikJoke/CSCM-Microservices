package ru.project.cscm.integration.model.enums;

import java.util.stream.Stream;

public enum StatisticsType {

    ATM_STATISTICS("AtmStatistics", "xsd/AtmActualStateStatistics.xsd"),

    MONITORING_STATISTICS("AtmMonitoringStatistics", "xsd/AtmMonitoringStatistics.xsd");

    private final String alias;
    private final String xsdPath;

    private StatisticsType(final String alias, final String xsdPath) {
        this.alias = alias;
        this.xsdPath = xsdPath;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getXsdPath() {
        return this.xsdPath;
    }

    public static StatisticsType value(final String alias) {

        return Stream.of(StatisticsType.values()).filter(s -> s.getAlias().equalsIgnoreCase(alias)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
