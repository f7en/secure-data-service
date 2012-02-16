//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.12 at 04:54:37 PM EST 
//


package org.slc.sli.test.edfi.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Encapsulates the possible attributes that can be used to lookup the identity of assessment families.
 * 
 * <p>Java class for AssessmentFamilyIdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssessmentFamilyIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="AssessmentFamilyIdentificationCode" type="{http://ed-fi.org/0100}AssessmentIdentificationCode" maxOccurs="unbounded"/>
 *         &lt;sequence>
 *           &lt;element name="AssessmentFamilyTitle" type="{http://ed-fi.org/0100}AssessmentTitle"/>
 *           &lt;element name="Version" type="{http://ed-fi.org/0100}Version" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssessmentFamilyIdentityType", propOrder = {
    "assessmentFamilyIdentificationCode",
    "assessmentFamilyTitle",
    "version"
})
public class AssessmentFamilyIdentityType {

    @XmlElement(name = "AssessmentFamilyIdentificationCode")
    protected List<AssessmentIdentificationCode> assessmentFamilyIdentificationCode;
    @XmlElement(name = "AssessmentFamilyTitle")
    protected String assessmentFamilyTitle;
    @XmlElement(name = "Version")
    protected Integer version;

    /**
     * Gets the value of the assessmentFamilyIdentificationCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assessmentFamilyIdentificationCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssessmentFamilyIdentificationCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssessmentIdentificationCode }
     * 
     * 
     */
    public List<AssessmentIdentificationCode> getAssessmentFamilyIdentificationCode() {
        if (assessmentFamilyIdentificationCode == null) {
            assessmentFamilyIdentificationCode = new ArrayList<AssessmentIdentificationCode>();
        }
        return this.assessmentFamilyIdentificationCode;
    }

    /**
     * Gets the value of the assessmentFamilyTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentFamilyTitle() {
        return assessmentFamilyTitle;
    }

    /**
     * Sets the value of the assessmentFamilyTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentFamilyTitle(String value) {
        this.assessmentFamilyTitle = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

}
