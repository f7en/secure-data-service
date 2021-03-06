//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.31 at 10:43:34 AM EDT 
//


package org.slc.sli.test.edfi.entitiesR1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 This association relates students to their parents,
 *                 guardians, or caretakers.
 *             
 * 
 * <p>Java class for studentParentAssociation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="studentParentAssociation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="studentId" type="{http://slc-sli/ed-org/0.1}reference"/>
 *         &lt;element name="relation" type="{http://slc-sli/ed-org/0.1}relationType" minOccurs="0"/>
 *         &lt;element name="primaryContactStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="livesWith" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="emergencyContactStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="contactPriority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="contactRestrictions" type="{http://slc-sli/ed-org/0.1}contactRestrictions" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studentParentAssociation", propOrder = {
    "studentId",
    "relation",
    "primaryContactStatus",
    "livesWith",
    "emergencyContactStatus",
    "contactPriority",
    "contactRestrictions"
})
public class StudentParentAssociation {

    @XmlElement(required = true)
    protected String studentId;
    protected RelationType relation;
    protected Boolean primaryContactStatus;
    protected Boolean livesWith;
    protected Boolean emergencyContactStatus;
    protected Integer contactPriority;
    protected String contactRestrictions;

    /**
     * Gets the value of the studentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the value of the studentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentId(String value) {
        this.studentId = value;
    }

    /**
     * Gets the value of the relation property.
     * 
     * @return
     *     possible object is
     *     {@link RelationType }
     *     
     */
    public RelationType getRelation() {
        return relation;
    }

    /**
     * Sets the value of the relation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationType }
     *     
     */
    public void setRelation(RelationType value) {
        this.relation = value;
    }

    /**
     * Gets the value of the primaryContactStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrimaryContactStatus() {
        return primaryContactStatus;
    }

    /**
     * Sets the value of the primaryContactStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrimaryContactStatus(Boolean value) {
        this.primaryContactStatus = value;
    }

    /**
     * Gets the value of the livesWith property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLivesWith() {
        return livesWith;
    }

    /**
     * Sets the value of the livesWith property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLivesWith(Boolean value) {
        this.livesWith = value;
    }

    /**
     * Gets the value of the emergencyContactStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEmergencyContactStatus() {
        return emergencyContactStatus;
    }

    /**
     * Sets the value of the emergencyContactStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmergencyContactStatus(Boolean value) {
        this.emergencyContactStatus = value;
    }

    /**
     * Gets the value of the contactPriority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getContactPriority() {
        return contactPriority;
    }

    /**
     * Sets the value of the contactPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setContactPriority(Integer value) {
        this.contactPriority = value;
    }

    /**
     * Gets the value of the contactRestrictions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactRestrictions() {
        return contactRestrictions;
    }

    /**
     * Sets the value of the contactRestrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactRestrictions(String value) {
        this.contactRestrictions = value;
    }

}
