//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.21 at 04:40:54 PM MSK 
//


package ru.project.cscm.monitoring.integration.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CashInCassetteInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CashInCassetteInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="cassRemaining" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cassUploaded" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="forthcomingDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CashInCassetteInfo", propOrder = {

})
public class CashInCassetteInfo {

    protected int cassRemaining;
    protected int cassUploaded;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar forthcomingDate;

    /**
     * Gets the value of the cassRemaining property.
     * 
     */
    public int getCassRemaining() {
        return cassRemaining;
    }

    /**
     * Sets the value of the cassRemaining property.
     * 
     */
    public void setCassRemaining(int value) {
        this.cassRemaining = value;
    }

    /**
     * Gets the value of the cassUploaded property.
     * 
     */
    public int getCassUploaded() {
        return cassUploaded;
    }

    /**
     * Sets the value of the cassUploaded property.
     * 
     */
    public void setCassUploaded(int value) {
        this.cassUploaded = value;
    }

    /**
     * Gets the value of the forthcomingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getForthcomingDate() {
        return forthcomingDate;
    }

    /**
     * Sets the value of the forthcomingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setForthcomingDate(XMLGregorianCalendar value) {
        this.forthcomingDate = value;
    }

}
