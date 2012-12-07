//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.06 at 10:00:50 AM EST 
//


package org.slc.sli.test.edfi.entities;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * CalendarDate record with key fields: Date and EducationOrgReference. Added EducationOrgReference.
 * 
 * <p>Java class for SLC-CalendarDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SLC-CalendarDate">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ed-fi.org/0100}ComplexObjectType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CalendarEvent" type="{http://ed-fi.org/0100}CalendarEventType"/>
 *         &lt;element name="EducationOrgReference" type="{http://ed-fi.org/0100}SLC-EducationalOrgReferenceType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLC-CalendarDate", propOrder = {
    "date",
    "calendarEvent",
    "educationOrgReference"
})
@XmlRootElement(name = "CalendarDate") 
public class SLCCalendarDate
    extends ComplexObjectType
{

    @XmlElement(name = "Date", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String date;
    @XmlElement(name = "CalendarEvent", required = true)
    protected CalendarEventType calendarEvent;
    @XmlElement(name = "EducationOrgReference", required = true)
    protected SLCEducationalOrgReferenceType educationOrgReference;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the calendarEvent property.
     * 
     * @return
     *     possible object is
     *     {@link CalendarEventType }
     *     
     */
    public CalendarEventType getCalendarEvent() {
        return calendarEvent;
    }

    /**
     * Sets the value of the calendarEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarEventType }
     *     
     */
    public void setCalendarEvent(CalendarEventType value) {
        this.calendarEvent = value;
    }

    /**
     * Gets the value of the educationOrgReference property.
     * 
     * @return
     *     possible object is
     *     {@link SLCEducationalOrgReferenceType }
     *     
     */
    public SLCEducationalOrgReferenceType getEducationOrgReference() {
        return educationOrgReference;
    }

    /**
     * Sets the value of the educationOrgReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SLCEducationalOrgReferenceType }
     *     
     */
    public void setEducationOrgReference(SLCEducationalOrgReferenceType value) {
        this.educationOrgReference = value;
    }

}
