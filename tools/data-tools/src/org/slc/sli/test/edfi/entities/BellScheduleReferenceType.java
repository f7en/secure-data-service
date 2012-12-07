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
 * Provides alternative references for a bell schedule during interchange. Use XML IDREF to reference a course record that is included in the interchange
 * 
 * <p>Java class for BellScheduleReferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BellScheduleReferenceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ed-fi.org/0100}ReferenceType">
 *       &lt;sequence>
 *         &lt;element name="BellScheduleIdentity" type="{http://ed-fi.org/0100}BellScheduleIdentityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BellScheduleReferenceType", propOrder = {
    "bellScheduleIdentity"
})
public class BellScheduleReferenceType
    extends ReferenceType
{

    @XmlElement(name = "BellScheduleIdentity")
    protected BellScheduleIdentityType bellScheduleIdentity;

    /**
     * Gets the value of the bellScheduleIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link BellScheduleIdentityType }
     *     
     */
    public BellScheduleIdentityType getBellScheduleIdentity() {
        return bellScheduleIdentity;
    }

    /**
     * Sets the value of the bellScheduleIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BellScheduleIdentityType }
     *     
     */
    public void setBellScheduleIdentity(BellScheduleIdentityType value) {
        this.bellScheduleIdentity = value;
    }

}
