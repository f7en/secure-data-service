//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.12 at 04:54:37 PM EST 
//


package org.slc.sli.test.edfi.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This event entity represents an occurrence of an infraction ranging from a minor problem behavior that disrupts the orderly functioning of a school or classroom (such as tardiness) to a criminal act that results in the involvement of a law enforcement official (such as robbery). A single event (e.g., a fight) is one incident regardless of how many perpetrators or victims are involved.  Discipline incidents are events classified as warranting discipline action.
 * 
 * 
 * <p>Java class for DisciplineIncident complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DisciplineIncident">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ed-fi.org/0100}ComplexObjectType">
 *       &lt;sequence>
 *         &lt;element name="IncidentIdentifier" type="{http://ed-fi.org/0100}IncidentIdentifier"/>
 *         &lt;element name="IncidentDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="IncidentTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="IncidentLocation" type="{http://ed-fi.org/0100}IncidentLocationType"/>
 *         &lt;element name="ReporterDescription" type="{http://ed-fi.org/0100}ReporterDescriptionType" minOccurs="0"/>
 *         &lt;element name="ReporterName" type="{http://ed-fi.org/0100}ReporterName" minOccurs="0"/>
 *         &lt;element name="Behaviors" type="{http://ed-fi.org/0100}BehaviorDescriptorType" maxOccurs="unbounded"/>
 *         &lt;element name="SecondaryBehaviors" type="{http://ed-fi.org/0100}SecondaryBehavior" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Weapons" type="{http://ed-fi.org/0100}WeaponsType" minOccurs="0"/>
 *         &lt;element name="ReportedToLawEnforcement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CaseNumber" type="{http://ed-fi.org/0100}CaseNumber" minOccurs="0"/>
 *         &lt;element name="SchoolReference" type="{http://ed-fi.org/0100}EducationalOrgReferenceType"/>
 *         &lt;element name="StaffReference" type="{http://ed-fi.org/0100}StaffReferenceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DisciplineIncident", propOrder = {
    "incidentIdentifier",
    "incidentDate",
    "incidentTime",
    "incidentLocation",
    "reporterDescription",
    "reporterName",
    "behaviors",
    "secondaryBehaviors",
    "weapons",
    "reportedToLawEnforcement",
    "caseNumber",
    "schoolReference",
    "staffReference"
})
public class DisciplineIncident
    extends ComplexObjectType
{

    @XmlElement(name = "IncidentIdentifier", required = true)
    protected String incidentIdentifier;
    @XmlElement(name = "IncidentDate", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar incidentDate;
    @XmlElement(name = "IncidentTime", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "time")
    protected Calendar incidentTime;
    @XmlElement(name = "IncidentLocation", required = true)
    protected IncidentLocationType incidentLocation;
    @XmlElement(name = "ReporterDescription")
    protected ReporterDescriptionType reporterDescription;
    @XmlElement(name = "ReporterName")
    protected String reporterName;
    @XmlElement(name = "Behaviors", required = true)
    protected List<BehaviorDescriptorType> behaviors;
    @XmlElement(name = "SecondaryBehaviors")
    protected List<SecondaryBehavior> secondaryBehaviors;
    @XmlElement(name = "Weapons")
    protected WeaponsType weapons;
    @XmlElement(name = "ReportedToLawEnforcement")
    protected Boolean reportedToLawEnforcement;
    @XmlElement(name = "CaseNumber")
    protected String caseNumber;
    @XmlElement(name = "SchoolReference", required = true)
    protected EducationalOrgReferenceType schoolReference;
    @XmlElement(name = "StaffReference")
    protected StaffReferenceType staffReference;

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
     * Gets the value of the incidentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getIncidentDate() {
        return incidentDate;
    }

    /**
     * Sets the value of the incidentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentDate(Calendar value) {
        this.incidentDate = value;
    }

    /**
     * Gets the value of the incidentTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getIncidentTime() {
        return incidentTime;
    }

    /**
     * Sets the value of the incidentTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentTime(Calendar value) {
        this.incidentTime = value;
    }

    /**
     * Gets the value of the incidentLocation property.
     * 
     * @return
     *     possible object is
     *     {@link IncidentLocationType }
     *     
     */
    public IncidentLocationType getIncidentLocation() {
        return incidentLocation;
    }

    /**
     * Sets the value of the incidentLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link IncidentLocationType }
     *     
     */
    public void setIncidentLocation(IncidentLocationType value) {
        this.incidentLocation = value;
    }

    /**
     * Gets the value of the reporterDescription property.
     * 
     * @return
     *     possible object is
     *     {@link ReporterDescriptionType }
     *     
     */
    public ReporterDescriptionType getReporterDescription() {
        return reporterDescription;
    }

    /**
     * Sets the value of the reporterDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReporterDescriptionType }
     *     
     */
    public void setReporterDescription(ReporterDescriptionType value) {
        this.reporterDescription = value;
    }

    /**
     * Gets the value of the reporterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReporterName() {
        return reporterName;
    }

    /**
     * Sets the value of the reporterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReporterName(String value) {
        this.reporterName = value;
    }

    /**
     * Gets the value of the behaviors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the behaviors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBehaviors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BehaviorDescriptorType }
     * 
     * 
     */
    public List<BehaviorDescriptorType> getBehaviors() {
        if (behaviors == null) {
            behaviors = new ArrayList<BehaviorDescriptorType>();
        }
        return this.behaviors;
    }

    /**
     * Gets the value of the secondaryBehaviors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the secondaryBehaviors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecondaryBehaviors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecondaryBehavior }
     * 
     * 
     */
    public List<SecondaryBehavior> getSecondaryBehaviors() {
        if (secondaryBehaviors == null) {
            secondaryBehaviors = new ArrayList<SecondaryBehavior>();
        }
        return this.secondaryBehaviors;
    }

    /**
     * Gets the value of the weapons property.
     * 
     * @return
     *     possible object is
     *     {@link WeaponsType }
     *     
     */
    public WeaponsType getWeapons() {
        return weapons;
    }

    /**
     * Sets the value of the weapons property.
     * 
     * @param value
     *     allowed object is
     *     {@link WeaponsType }
     *     
     */
    public void setWeapons(WeaponsType value) {
        this.weapons = value;
    }

    /**
     * Gets the value of the reportedToLawEnforcement property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReportedToLawEnforcement() {
        return reportedToLawEnforcement;
    }

    /**
     * Sets the value of the reportedToLawEnforcement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReportedToLawEnforcement(Boolean value) {
        this.reportedToLawEnforcement = value;
    }

    /**
     * Gets the value of the caseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaseNumber() {
        return caseNumber;
    }

    /**
     * Sets the value of the caseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaseNumber(String value) {
        this.caseNumber = value;
    }

    /**
     * Gets the value of the schoolReference property.
     * 
     * @return
     *     possible object is
     *     {@link EducationalOrgReferenceType }
     *     
     */
    public EducationalOrgReferenceType getSchoolReference() {
        return schoolReference;
    }

    /**
     * Sets the value of the schoolReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link EducationalOrgReferenceType }
     *     
     */
    public void setSchoolReference(EducationalOrgReferenceType value) {
        this.schoolReference = value;
    }

    /**
     * Gets the value of the staffReference property.
     * 
     * @return
     *     possible object is
     *     {@link StaffReferenceType }
     *     
     */
    public StaffReferenceType getStaffReference() {
        return staffReference;
    }

    /**
     * Sets the value of the staffReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffReferenceType }
     *     
     */
    public void setStaffReference(StaffReferenceType value) {
        this.staffReference = value;
    }

}
