//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.13 at 07:13:52 PM MSK 
//


package ru.project.cscm.integration.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for cashInStatistics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cashInStatistics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="statDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="encDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="billsCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="billsRemaining" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="availableCoeff" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="cassValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cassCurrency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cassCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="actionType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="BILLS_LOAD"/>
 *               &lt;enumeration value="BILLS_UNLOAD"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cassNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cassIsPresent" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cassIsWorking" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cashInStatistics", propOrder = {

})
public class CashInStatistics {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar encDate;
    protected int billsCount;
    protected int billsRemaining;
    protected double availableCoeff;
    protected int cassValue;
    protected int cassCurrency;
    protected int cassCount;
    @XmlElement(required = true)
    protected String actionType;
    protected int cassNumber;
    protected boolean cassIsPresent;
    protected boolean cassIsWorking;

    /**
     * Gets the value of the statDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatDate() {
        return statDate;
    }

    /**
     * Sets the value of the statDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatDate(XMLGregorianCalendar value) {
        this.statDate = value;
    }

    /**
     * Gets the value of the encDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEncDate() {
        return encDate;
    }

    /**
     * Sets the value of the encDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEncDate(XMLGregorianCalendar value) {
        this.encDate = value;
    }

    /**
     * Gets the value of the billsCount property.
     * 
     */
    public int getBillsCount() {
        return billsCount;
    }

    /**
     * Sets the value of the billsCount property.
     * 
     */
    public void setBillsCount(int value) {
        this.billsCount = value;
    }

    /**
     * Gets the value of the billsRemaining property.
     * 
     */
    public int getBillsRemaining() {
        return billsRemaining;
    }

    /**
     * Sets the value of the billsRemaining property.
     * 
     */
    public void setBillsRemaining(int value) {
        this.billsRemaining = value;
    }

    /**
     * Gets the value of the availableCoeff property.
     * 
     */
    public double getAvailableCoeff() {
        return availableCoeff;
    }

    /**
     * Sets the value of the availableCoeff property.
     * 
     */
    public void setAvailableCoeff(double value) {
        this.availableCoeff = value;
    }

    /**
     * Gets the value of the cassValue property.
     * 
     */
    public int getCassValue() {
        return cassValue;
    }

    /**
     * Sets the value of the cassValue property.
     * 
     */
    public void setCassValue(int value) {
        this.cassValue = value;
    }

    /**
     * Gets the value of the cassCurrency property.
     * 
     */
    public int getCassCurrency() {
        return cassCurrency;
    }

    /**
     * Sets the value of the cassCurrency property.
     * 
     */
    public void setCassCurrency(int value) {
        this.cassCurrency = value;
    }

    /**
     * Gets the value of the cassCount property.
     * 
     */
    public int getCassCount() {
        return cassCount;
    }

    /**
     * Sets the value of the cassCount property.
     * 
     */
    public void setCassCount(int value) {
        this.cassCount = value;
    }

    /**
     * Gets the value of the actionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Sets the value of the actionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionType(String value) {
        this.actionType = value;
    }

    /**
     * Gets the value of the cassNumber property.
     * 
     */
    public int getCassNumber() {
        return cassNumber;
    }

    /**
     * Sets the value of the cassNumber property.
     * 
     */
    public void setCassNumber(int value) {
        this.cassNumber = value;
    }

    /**
     * Gets the value of the cassIsPresent property.
     * 
     */
    public boolean isCassIsPresent() {
        return cassIsPresent;
    }

    /**
     * Sets the value of the cassIsPresent property.
     * 
     */
    public void setCassIsPresent(boolean value) {
        this.cassIsPresent = value;
    }

    /**
     * Gets the value of the cassIsWorking property.
     * 
     */
    public boolean isCassIsWorking() {
        return cassIsWorking;
    }

    /**
     * Sets the value of the cassIsWorking property.
     * 
     */
    public void setCassIsWorking(boolean value) {
        this.cassIsWorking = value;
    }

}