//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.06 at 10:00:50 AM EST 
//


package org.slc.sli.test.edfi.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Changed to use an embedded EducationalOrgReference.
 * 
 * <p>Java class for SLC-DisciplineIncidentIdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SLC-DisciplineIncidentIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncidentIdentifier" type="{http://ed-fi.org/0100}IncidentIdentifier"/>
 *         &lt;element name="EducationalOrgReference" type="{http://ed-fi.org/0100}SLC-EducationalOrgReferenceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLC-DisciplineIncidentIdentityType", propOrder = {
    "incidentIdentifier",
    "educationalOrgReference"
})
public class SLCDisciplineIncidentIdentityType {

    @XmlElement(name = "IncidentIdentifier", required = true)
    protected String incidentIdentifier;
    @XmlElement(name = "EducationalOrgReference", required = true)
    protected SLCEducationalOrgReferenceType educationalOrgReference;

    /**
     * Gets the value of the incidentIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentIdentifier() {
        return incidentIdentifier;
    }

    /**
     * Sets the value of the incidentIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentIdentifier(String value) {
        this.incidentIdentifier = value;
    }

    /**
     * Gets the value of the educationalOrgReference property.
     * 
     * @return
     *     possible object is
     *     {@link SLCEducationalOrgReferenceType }
     *     
     */
    public SLCEducationalOrgReferenceType getEducationalOrgReference() {
        return educationalOrgReference;
    }

    /**
     * Sets the value of the educationalOrgReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLCEducationalOrgReferenceType }
     *     
     */
    public void setEducationalOrgReference(SLCEducationalOrgReferenceType value) {
        this.educationalOrgReference = value;
    }

}
