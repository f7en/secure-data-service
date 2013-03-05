//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.31 at 10:43:34 AM EDT 
//


package org.slc.sli.test.edfi.entitiesR1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *                 This entity represents a tool, instrument, process,
 *                 or exhibition composed of a systematic sampling of
 *                 behavior for
 *                 measuring a student's competence,
 *                 knowledge, skills or behavior. An
 *                 assessment can be
 *                 used to measure differences in individuals or
 *                 groups and changes in performance from one occasion
 *                 to the next.
 *             
 * 
 * <p>Java class for assessment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assessment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assessmentTitle" type="{http://slc-sli/ed-org/0.1}assessmentTitle"/>
 *         &lt;element name="assessmentFamilyHierarchyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assessmentIdentificationCode" type="{http://slc-sli/ed-org/0.1}assessmentIdentificationCode" maxOccurs="unbounded"/>
 *         &lt;element name="assessmentCategory" type="{http://slc-sli/ed-org/0.1}assessmentCategoryType" minOccurs="0"/>
 *         &lt;element name="academicSubject" type="{http://slc-sli/ed-org/0.1}academicSubjectType" minOccurs="0"/>
 *         &lt;element name="gradeLevelAssessed" type="{http://slc-sli/ed-org/0.1}gradeLevelType" minOccurs="0"/>
 *         &lt;element name="lowestGradeLevelAssessed" type="{http://slc-sli/ed-org/0.1}gradeLevelType" minOccurs="0"/>
 *         &lt;element name="assessmentPerformanceLevel" type="{http://slc-sli/ed-org/0.1}assessmentPerformanceLevel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contentStandard" type="{http://slc-sli/ed-org/0.1}contentStandardType" minOccurs="0"/>
 *         &lt;element name="assessmentForm" type="{http://slc-sli/ed-org/0.1}assessmentForm" minOccurs="0"/>
 *         &lt;element name="version" type="{http://slc-sli/ed-org/0.1}version" minOccurs="0"/>
 *         &lt;element name="revisionDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="maxRawScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minRawScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nomenclature" type="{http://slc-sli/ed-org/0.1}nomenclature" minOccurs="0"/>
 *         &lt;element name="assessmentPeriodDescriptor" type="{http://slc-sli/ed-org/0.1}assessmentPeriodDescriptor" minOccurs="0"/>
 *         &lt;element name="objectiveAssessment" type="{http://slc-sli/ed-org/0.1}objectiveAssessment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="assessmentItem" type="{http://slc-sli/ed-org/0.1}assessmentItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sectionReferences" type="{http://slc-sli/ed-org/0.1}reference" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assessment", propOrder = {
    "assessmentTitle",
    "assessmentFamilyHierarchyName",
    "assessmentIdentificationCode",
    "assessmentCategory",
    "academicSubject",
    "gradeLevelAssessed",
    "lowestGradeLevelAssessed",
    "assessmentPerformanceLevel",
    "contentStandard",
    "assessmentForm",
    "version",
    "revisionDate",
    "maxRawScore",
    "minRawScore",
    "nomenclature",
    "assessmentPeriodDescriptor",
    "objectiveAssessment",
    "assessmentItem",
    "sectionReferences"
})
public class Assessment {

    @XmlElement(required = true)
    protected String assessmentTitle;
    protected String assessmentFamilyHierarchyName;
    @XmlElement(required = true)
    protected List<AssessmentIdentificationCode> assessmentIdentificationCode;
    protected AssessmentCategoryType assessmentCategory;
    protected AcademicSubjectType academicSubject;
    protected GradeLevelType gradeLevelAssessed;
    protected GradeLevelType lowestGradeLevelAssessed;
    protected List<AssessmentPerformanceLevel> assessmentPerformanceLevel;
    protected ContentStandardType contentStandard;
    protected String assessmentForm;
    protected Integer version;
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String revisionDate;
    protected Integer maxRawScore;
    protected Integer minRawScore;
    protected String nomenclature;
    protected AssessmentPeriodDescriptor assessmentPeriodDescriptor;
    protected List<ObjectiveAssessment> objectiveAssessment;
    protected List<AssessmentItem> assessmentItem;
    protected List<String> sectionReferences;

    /**
     * Gets the value of the assessmentTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    /**
     * Sets the value of the assessmentTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentTitle(String value) {
        this.assessmentTitle = value;
    }

    /**
     * Gets the value of the assessmentFamilyHierarchyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentFamilyHierarchyName() {
        return assessmentFamilyHierarchyName;
    }

    /**
     * Sets the value of the assessmentFamilyHierarchyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentFamilyHierarchyName(String value) {
        this.assessmentFamilyHierarchyName = value;
    }

    /**
     * Gets the value of the assessmentIdentificationCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assessmentIdentificationCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssessmentIdentificationCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssessmentIdentificationCode }
     * 
     * 
     */
    public List<AssessmentIdentificationCode> getAssessmentIdentificationCode() {
        if (assessmentIdentificationCode == null) {
            assessmentIdentificationCode = new ArrayList<AssessmentIdentificationCode>();
        }
        return this.assessmentIdentificationCode;
    }

    /**
     * Gets the value of the assessmentCategory property.
     * 
     * @return
     *     possible object is
     *     {@link AssessmentCategoryType }
     *     
     */
    public AssessmentCategoryType getAssessmentCategory() {
        return assessmentCategory;
    }

    /**
     * Sets the value of the assessmentCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssessmentCategoryType }
     *     
     */
    public void setAssessmentCategory(AssessmentCategoryType value) {
        this.assessmentCategory = value;
    }

    /**
     * Gets the value of the academicSubject property.
     * 
     * @return
     *     possible object is
     *     {@link AcademicSubjectType }
     *     
     */
    public AcademicSubjectType getAcademicSubject() {
        return academicSubject;
    }

    /**
     * Sets the value of the academicSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcademicSubjectType }
     *     
     */
    public void setAcademicSubject(AcademicSubjectType value) {
        this.academicSubject = value;
    }

    /**
     * Gets the value of the gradeLevelAssessed property.
     * 
     * @return
     *     possible object is
     *     {@link GradeLevelType }
     *     
     */
    public GradeLevelType getGradeLevelAssessed() {
        return gradeLevelAssessed;
    }

    /**
     * Sets the value of the gradeLevelAssessed property.
     * 
     * @param value
     *     allowed object is
     *     {@link GradeLevelType }
     *     
     */
    public void setGradeLevelAssessed(GradeLevelType value) {
        this.gradeLevelAssessed = value;
    }

    /**
     * Gets the value of the lowestGradeLevelAssessed property.
     * 
     * @return
     *     possible object is
     *     {@link GradeLevelType }
     *     
     */
    public GradeLevelType getLowestGradeLevelAssessed() {
        return lowestGradeLevelAssessed;
    }

    /**
     * Sets the value of the lowestGradeLevelAssessed property.
     * 
     * @param value
     *     allowed object is
     *     {@link GradeLevelType }
     *     
     */
    public void setLowestGradeLevelAssessed(GradeLevelType value) {
        this.lowestGradeLevelAssessed = value;
    }

    /**
     * Gets the value of the assessmentPerformanceLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assessmentPerformanceLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssessmentPerformanceLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssessmentPerformanceLevel }
     * 
     * 
     */
    public List<AssessmentPerformanceLevel> getAssessmentPerformanceLevel() {
        if (assessmentPerformanceLevel == null) {
            assessmentPerformanceLevel = new ArrayList<AssessmentPerformanceLevel>();
        }
        return this.assessmentPerformanceLevel;
    }

    /**
     * Gets the value of the contentStandard property.
     * 
     * @return
     *     possible object is
     *     {@link ContentStandardType }
     *     
     */
    public ContentStandardType getContentStandard() {
        return contentStandard;
    }

    /**
     * Sets the value of the contentStandard property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentStandardType }
     *     
     */
    public void setContentStandard(ContentStandardType value) {
        this.contentStandard = value;
    }

    /**
     * Gets the value of the assessmentForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentForm() {
        return assessmentForm;
    }

    /**
     * Sets the value of the assessmentForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentForm(String value) {
        this.assessmentForm = value;
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

    /**
     * Gets the value of the revisionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevisionDate() {
        return revisionDate;
    }

    /**
     * Sets the value of the revisionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevisionDate(String value) {
        this.revisionDate = value;
    }

    /**
     * Gets the value of the maxRawScore property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxRawScore() {
        return maxRawScore;
    }

    /**
     * Sets the value of the maxRawScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxRawScore(Integer value) {
        this.maxRawScore = value;
    }

    /**
     * Gets the value of the minRawScore property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinRawScore() {
        return minRawScore;
    }

    /**
     * Sets the value of the minRawScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinRawScore(Integer value) {
        this.minRawScore = value;
    }

    /**
     * Gets the value of the nomenclature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomenclature() {
        return nomenclature;
    }

    /**
     * Sets the value of the nomenclature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomenclature(String value) {
        this.nomenclature = value;
    }

    /**
     * Gets the value of the assessmentPeriodDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link AssessmentPeriodDescriptor }
     *     
     */
    public AssessmentPeriodDescriptor getAssessmentPeriodDescriptor() {
        return assessmentPeriodDescriptor;
    }

    /**
     * Sets the value of the assessmentPeriodDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssessmentPeriodDescriptor }
     *     
     */
    public void setAssessmentPeriodDescriptor(AssessmentPeriodDescriptor value) {
        this.assessmentPeriodDescriptor = value;
    }

    /**
     * Gets the value of the objectiveAssessment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectiveAssessment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectiveAssessment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectiveAssessment }
     * 
     * 
     */
    public List<ObjectiveAssessment> getObjectiveAssessment() {
        if (objectiveAssessment == null) {
            objectiveAssessment = new ArrayList<ObjectiveAssessment>();
        }
        return this.objectiveAssessment;
    }

    /**
     * Gets the value of the assessmentItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assessmentItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssessmentItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssessmentItem }
     * 
     * 
     */
    public List<AssessmentItem> getAssessmentItem() {
        if (assessmentItem == null) {
            assessmentItem = new ArrayList<AssessmentItem>();
        }
        return this.assessmentItem;
    }

    /**
     * Gets the value of the sectionReferences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sectionReferences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSectionReferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSectionReferences() {
        if (sectionReferences == null) {
            sectionReferences = new ArrayList<String>();
        }
        return this.sectionReferences;
    }

}
