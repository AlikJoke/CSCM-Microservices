//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.13 at 07:13:52 PM MSK 
//


package ru.project.cscm.integration.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.project.cscm.integration.model package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.project.cscm.integration.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AtmActualStateStatistics }
     * 
     */
    public AtmActualStateStatistics createAtmActualStateStatistics() {
        return new AtmActualStateStatistics();
    }

    /**
     * Create an instance of {@link AtmActualStateStatisticsItem }
     * 
     */
    public AtmActualStateStatisticsItem createAtmActualStateStatisticsItem() {
        return new AtmActualStateStatisticsItem();
    }

    /**
     * Create an instance of {@link AtmActualStateStatistics.Statistics }
     * 
     */
    public AtmActualStateStatistics.Statistics createAtmActualStateStatisticsStatistics() {
        return new AtmActualStateStatistics.Statistics();
    }

    /**
     * Create an instance of {@link CashOutStatistics }
     * 
     */
    public CashOutStatistics createCashOutStatistics() {
        return new CashOutStatistics();
    }

    /**
     * Create an instance of {@link CashInRecyclingCassetteStatistics }
     * 
     */
    public CashInRecyclingCassetteStatistics createCashInRecyclingCassetteStatistics() {
        return new CashInRecyclingCassetteStatistics();
    }

    /**
     * Create an instance of {@link CashInStatistics }
     * 
     */
    public CashInStatistics createCashInStatistics() {
        return new CashInStatistics();
    }

    /**
     * Create an instance of {@link RejectStatistics }
     * 
     */
    public RejectStatistics createRejectStatistics() {
        return new RejectStatistics();
    }

    /**
     * Create an instance of {@link AtmActualStateStatisticsItem.RejectStat }
     * 
     */
    public AtmActualStateStatisticsItem.RejectStat createAtmActualStateStatisticsItemRejectStat() {
        return new AtmActualStateStatisticsItem.RejectStat();
    }

}
