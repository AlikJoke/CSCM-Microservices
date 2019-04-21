//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.13 at 07:13:52 PM MSK 
//


package ru.project.cscm.integration.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AtmActualStateStatisticsItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AtmActualStateStatisticsItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="atmId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cashInCapacity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cashInRecyclingInitial" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="outOfCashOutDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="outOfCashInDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="outOfCashOutCurrency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rurExhaustionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="rurDemandValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="eurExhaustionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="eurDemandValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="usdExhaustionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="usdDemandValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="avgTransInHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="avgTransInDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastAdditionHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastWithdrawalHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="outOfCashInResp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="outOfCashOutResp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rejectInitial" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cashInInitial" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currencyRemainingAlert" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cashInStat" type="{}cashInStatistics"/>
 *         &lt;element name="rejectStat">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="rejectStat" type="{}rejectStatistics" minOccurs="0"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="cashOutStat" type="{}cashOutStatistics"/>
 *         &lt;element name="atmState">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="OPENED"/>
 *               &lt;enumeration value="CLOSED"/>
 *               &lt;enumeration value="DISCONNECTED"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="atmProblem">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="CARD_READER"/>
 *               &lt;enumeration value="RECEIPT_PRINTER"/>
 *               &lt;enumeration value="DISPENSER"/>
 *               &lt;enumeration value="BILL_ACCEPTOR"/>
 *               &lt;enumeration value="JOURNAL_PRINTER"/>
 *               &lt;enumeration value="IS_FULL"/>
 *               &lt;enumeration value="FIREWALL"/>
 *               &lt;enumeration value="NONE"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AtmActualStateStatisticsItem", propOrder = {

})
public class AtmActualStateStatisticsItem {

    @XmlElement(required = true)
    protected String atmId;
    @XmlElement(required = true)
    protected int cashInCapacity;
    protected int cashInRecyclingInitial;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar outOfCashOutDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar outOfCashInDate;
    protected int outOfCashOutCurrency;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rurExhaustionDate;
    protected double rurDemandValue;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eurExhaustionDate;
    protected double eurDemandValue;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar usdExhaustionDate;
    protected double usdDemandValue;
    protected int avgTransInHour;
    protected int avgTransInDay;
    protected int lastAdditionHours;
    protected int lastWithdrawalHours;
    protected int outOfCashInResp;
    protected int outOfCashOutResp;
    protected int rejectInitial;
    protected int cashInInitial;
    protected boolean currencyRemainingAlert;
    @XmlElement(required = true)
    protected CashInStatistics cashInStat;
    @XmlElement(required = true)
    protected AtmActualStateStatisticsItem.RejectStat rejectStat;
    @XmlElement(required = true)
    protected CashOutStatistics cashOutStat;
    @XmlElement(required = true)
    protected String atmState;
    @XmlElement(required = true)
    protected String atmProblem;

    /**
     * Gets the value of the atmId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmId() {
        return atmId;
    }

    /**
     * Sets the value of the atmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmId(String value) {
        this.atmId = value;
    }

    /**
     * Gets the value of the cashInCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCashInCapacity() {
        return cashInCapacity;
    }

    /**
     * Sets the value of the cashInCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCashInCapacity(String value) {
        this.cashInCapacity = value;
    }

    /**
     * Gets the value of the cashInRecyclingInitial property.
     * 
     */
    public int getCashInRecyclingInitial() {
        return cashInRecyclingInitial;
    }

    /**
     * Sets the value of the cashInRecyclingInitial property.
     * 
     */
    public void setCashInRecyclingInitial(int value) {
        this.cashInRecyclingInitial = value;
    }

    /**
     * Gets the value of the outOfCashOutDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOutOfCashOutDate() {
        return outOfCashOutDate;
    }

    /**
     * Sets the value of the outOfCashOutDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOutOfCashOutDate(XMLGregorianCalendar value) {
        this.outOfCashOutDate = value;
    }

    /**
     * Gets the value of the outOfCashInDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOutOfCashInDate() {
        return outOfCashInDate;
    }

    /**
     * Sets the value of the outOfCashInDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOutOfCashInDate(XMLGregorianCalendar value) {
        this.outOfCashInDate = value;
    }

    /**
     * Gets the value of the outOfCashOutCurrency property.
     * 
     */
    public int getOutOfCashOutCurrency() {
        return outOfCashOutCurrency;
    }

    /**
     * Sets the value of the outOfCashOutCurrency property.
     * 
     */
    public void setOutOfCashOutCurrency(int value) {
        this.outOfCashOutCurrency = value;
    }

    /**
     * Gets the value of the rurExhaustionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRurExhaustionDate() {
        return rurExhaustionDate;
    }

    /**
     * Sets the value of the rurExhaustionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRurExhaustionDate(XMLGregorianCalendar value) {
        this.rurExhaustionDate = value;
    }

    /**
     * Gets the value of the rurDemandValue property.
     * 
     */
    public double getRurDemandValue() {
        return rurDemandValue;
    }

    /**
     * Sets the value of the rurDemandValue property.
     * 
     */
    public void setRurDemandValue(double value) {
        this.rurDemandValue = value;
    }

    /**
     * Gets the value of the eurExhaustionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEurExhaustionDate() {
        return eurExhaustionDate;
    }

    /**
     * Sets the value of the eurExhaustionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEurExhaustionDate(XMLGregorianCalendar value) {
        this.eurExhaustionDate = value;
    }

    /**
     * Gets the value of the eurDemandValue property.
     * 
     */
    public double getEurDemandValue() {
        return eurDemandValue;
    }

    /**
     * Sets the value of the eurDemandValue property.
     * 
     */
    public void setEurDemandValue(double value) {
        this.eurDemandValue = value;
    }

    /**
     * Gets the value of the usdExhaustionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUsdExhaustionDate() {
        return usdExhaustionDate;
    }

    /**
     * Sets the value of the usdExhaustionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUsdExhaustionDate(XMLGregorianCalendar value) {
        this.usdExhaustionDate = value;
    }

    /**
     * Gets the value of the usdDemandValue property.
     * 
     */
    public double getUsdDemandValue() {
        return usdDemandValue;
    }

    /**
     * Sets the value of the usdDemandValue property.
     * 
     */
    public void setUsdDemandValue(double value) {
        this.usdDemandValue = value;
    }

    /**
     * Gets the value of the avgTransInHour property.
     * 
     */
    public int getAvgTransInHour() {
        return avgTransInHour;
    }

    /**
     * Sets the value of the avgTransInHour property.
     * 
     */
    public void setAvgTransInHour(int value) {
        this.avgTransInHour = value;
    }

    /**
     * Gets the value of the avgTransInDay property.
     * 
     */
    public int getAvgTransInDay() {
        return avgTransInDay;
    }

    /**
     * Sets the value of the avgTransInDay property.
     * 
     */
    public void setAvgTransInDay(int value) {
        this.avgTransInDay = value;
    }

    /**
     * Gets the value of the lastAdditionHours property.
     * 
     */
    public int getLastAdditionHours() {
        return lastAdditionHours;
    }

    /**
     * Sets the value of the lastAdditionHours property.
     * 
     */
    public void setLastAdditionHours(int value) {
        this.lastAdditionHours = value;
    }

    /**
     * Gets the value of the lastWithdrawalHours property.
     * 
     */
    public int getLastWithdrawalHours() {
        return lastWithdrawalHours;
    }

    /**
     * Sets the value of the lastWithdrawalHours property.
     * 
     */
    public void setLastWithdrawalHours(int value) {
        this.lastWithdrawalHours = value;
    }

    /**
     * Gets the value of the outOfCashInResp property.
     * 
     */
    public int getOutOfCashInResp() {
        return outOfCashInResp;
    }

    /**
     * Sets the value of the outOfCashInResp property.
     * 
     */
    public void setOutOfCashInResp(int value) {
        this.outOfCashInResp = value;
    }

    /**
     * Gets the value of the outOfCashOutResp property.
     * 
     */
    public int getOutOfCashOutResp() {
        return outOfCashOutResp;
    }

    /**
     * Sets the value of the outOfCashOutResp property.
     * 
     */
    public void setOutOfCashOutResp(int value) {
        this.outOfCashOutResp = value;
    }

    /**
     * Gets the value of the rejectInitial property.
     * 
     */
    public int getRejectInitial() {
        return rejectInitial;
    }

    /**
     * Sets the value of the rejectInitial property.
     * 
     */
    public void setRejectInitial(int value) {
        this.rejectInitial = value;
    }

    /**
     * Gets the value of the cashInInitial property.
     * 
     */
    public int getCashInInitial() {
        return cashInInitial;
    }

    /**
     * Sets the value of the cashInInitial property.
     * 
     */
    public void setCashInInitial(int value) {
        this.cashInInitial = value;
    }

    /**
     * Gets the value of the currencyRemainingAlert property.
     * 
     */
    public boolean isCurrencyRemainingAlert() {
        return currencyRemainingAlert;
    }

    /**
     * Sets the value of the currencyRemainingAlert property.
     * 
     */
    public void setCurrencyRemainingAlert(boolean value) {
        this.currencyRemainingAlert = value;
    }

    /**
     * Gets the value of the cashInStat property.
     * 
     * @return
     *     possible object is
     *     {@link CashInStatistics }
     *     
     */
    public CashInStatistics getCashInStat() {
        return cashInStat;
    }

    /**
     * Sets the value of the cashInStat property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashInStatistics }
     *     
     */
    public void setCashInStat(CashInStatistics value) {
        this.cashInStat = value;
    }

    /**
     * Gets the value of the rejectStat property.
     * 
     * @return
     *     possible object is
     *     {@link AtmActualStateStatisticsItem.RejectStat }
     *     
     */
    public AtmActualStateStatisticsItem.RejectStat getRejectStat() {
        return rejectStat;
    }

    /**
     * Sets the value of the rejectStat property.
     * 
     * @param value
     *     allowed object is
     *     {@link AtmActualStateStatisticsItem.RejectStat }
     *     
     */
    public void setRejectStat(AtmActualStateStatisticsItem.RejectStat value) {
        this.rejectStat = value;
    }

    /**
     * Gets the value of the cashOutStat property.
     * 
     * @return
     *     possible object is
     *     {@link CashOutStatistics }
     *     
     */
    public CashOutStatistics getCashOutStat() {
        return cashOutStat;
    }

    /**
     * Sets the value of the cashOutStat property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashOutStatistics }
     *     
     */
    public void setCashOutStat(CashOutStatistics value) {
        this.cashOutStat = value;
    }

    /**
     * Gets the value of the atmState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmState() {
        return atmState;
    }

    /**
     * Sets the value of the atmState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmState(String value) {
        this.atmState = value;
    }

    /**
     * Gets the value of the atmProblem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtmProblem() {
        return atmProblem;
    }

    /**
     * Sets the value of the atmProblem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtmProblem(String value) {
        this.atmProblem = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="rejectStat" type="{}rejectStatistics" minOccurs="0"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "rejectStat"
    })
    public static class RejectStat {

        protected List<RejectStatistics> rejectStat;

        /**
         * Gets the value of the rejectStat property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rejectStat property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRejectStat().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RejectStatistics }
         * 
         * 
         */
        public List<RejectStatistics> getRejectStat() {
            if (rejectStat == null) {
                rejectStat = new ArrayList<RejectStatistics>();
            }
            return this.rejectStat;
        }

    }

}