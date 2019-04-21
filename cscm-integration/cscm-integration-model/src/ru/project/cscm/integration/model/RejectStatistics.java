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
 * <p>Java class for rejectStatistics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rejectStatistics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="atmId" type="{}notEmptyString"/>
 *         &lt;element name="statDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="billsCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="billsRemaining" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rejectStatistics", propOrder = {

})
public class RejectStatistics {

    @XmlElement(required = true)
    protected String atmId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statDate;
    protected int billsCount;
    protected int billsRemaining;

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

}