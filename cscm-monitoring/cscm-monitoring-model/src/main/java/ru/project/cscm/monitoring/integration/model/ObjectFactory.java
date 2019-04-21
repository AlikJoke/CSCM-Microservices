//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 04:40:54 PM MSK 
//


package ru.project.cscm.monitoring.integration.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.project.cscm.monitoring.integration.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.project.cscm.monitoring.integration.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AtmMonitoringStatistics }
     * 
     */
    public AtmMonitoringStatistics createAtmMonitoringStatistics() {
        return new AtmMonitoringStatistics();
    }

    /**
     * Create an instance of {@link AtmMonitoringStatisticsItem }
     * 
     */
    public AtmMonitoringStatisticsItem createAtmMonitoringStatisticsItem() {
        return new AtmMonitoringStatisticsItem();
    }

    /**
     * Create an instance of {@link AtmMonitoringStatistics.Statistics }
     * 
     */
    public AtmMonitoringStatistics.Statistics createAtmMonitoringStatisticsStatistics() {
        return new AtmMonitoringStatistics.Statistics();
    }

    /**
     * Create an instance of {@link TransactionsStatistics }
     * 
     */
    public TransactionsStatistics createTransactionsStatistics() {
        return new TransactionsStatistics();
    }

    /**
     * Create an instance of {@link EncashmentInfo }
     * 
     */
    public EncashmentInfo createEncashmentInfo() {
        return new EncashmentInfo();
    }

    /**
     * Create an instance of {@link AtmInfo }
     * 
     */
    public AtmInfo createAtmInfo() {
        return new AtmInfo();
    }

    /**
     * Create an instance of {@link CashOutCassetteInfo }
     * 
     */
    public CashOutCassetteInfo createCashOutCassetteInfo() {
        return new CashOutCassetteInfo();
    }

    /**
     * Create an instance of {@link Currency }
     * 
     */
    public Currency createCurrency() {
        return new Currency();
    }

    /**
     * Create an instance of {@link CashInRecyclingCassetteInfo }
     * 
     */
    public CashInRecyclingCassetteInfo createCashInRecyclingCassetteInfo() {
        return new CashInRecyclingCassetteInfo();
    }

    /**
     * Create an instance of {@link CashInCassetteInfo }
     * 
     */
    public CashInCassetteInfo createCashInCassetteInfo() {
        return new CashInCassetteInfo();
    }

    /**
     * Create an instance of {@link AtmMonitoringStatisticsItem.CashInRecyclingCassettes }
     * 
     */
    public AtmMonitoringStatisticsItem.CashInRecyclingCassettes createAtmMonitoringStatisticsItemCashInRecyclingCassettes() {
        return new AtmMonitoringStatisticsItem.CashInRecyclingCassettes();
    }

    /**
     * Create an instance of {@link AtmMonitoringStatisticsItem.CashOutCassettes }
     * 
     */
    public AtmMonitoringStatisticsItem.CashOutCassettes createAtmMonitoringStatisticsItemCashOutCassettes() {
        return new AtmMonitoringStatisticsItem.CashOutCassettes();
    }

    /**
     * Create an instance of {@link AtmMonitoringStatisticsItem.TransactionsByHours }
     * 
     */
    public AtmMonitoringStatisticsItem.TransactionsByHours createAtmMonitoringStatisticsItemTransactionsByHours() {
        return new AtmMonitoringStatisticsItem.TransactionsByHours();
    }

}
