//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.20 at 03:09:04 PM EDT 
//


package org.slc.sli.sample.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A unique number or alphanumeric code assigned to an assessment by a school, school system, a state, or other agency or entity.
 * 
 * <p>Java class for AssessmentIdentificationCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssessmentIdentificationCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://ed-fi.org/0100}IdentificationCode"/>
 *       &lt;/sequence>
 *       &lt;attribute name="IdentificationSystem" use="required" type="{http://ed-fi.org/0100}AssessmentIdentificationSystemType" />
 *       &lt;attribute name="AssigningOrganizationCode" type="{http://ed-fi.org/0100}IdentificationCode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssessmentIdentificationCode", propOrder = {
    "id"
})
public class AssessmentIdentificationCode {

    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "IdentificationSystem", required = true)
    protected AssessmentIdentificationSystemType identificationSystem;
    @XmlAttribute(name = "AssigningOrganizationCode")
    protected String assigningOrganizationCode;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the identificationSystem property.
     * 
     * @return
     *     possible object is
     *     {@link AssessmentIdentificationSystemType }
     *     
     */
    public AssessmentIdentificationSystemType getIdentificationSystem() {
        return identificationSystem;
    }

    /**
     * Sets the value of the identificationSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssessmentIdentificationSystemType }
     *     
     */
    public void setIdentificationSystem(AssessmentIdentificationSystemType value) {
        this.identificationSystem = value;
    }

    /**
     * Gets the value of the assigningOrganizationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssigningOrganizationCode() {
        return assigningOrganizationCode;
    }

    /**
     * Sets the value of the assigningOrganizationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssigningOrganizationCode(String value) {
        this.assigningOrganizationCode = value;
    }

}
