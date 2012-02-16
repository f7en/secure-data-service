//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.12 at 04:54:37 PM EST 
//


package org.slc.sli.test.edfi.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LeaveEventCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LeaveEventCategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Vacation"/>
 *     &lt;enumeration value="Jury"/>
 *     &lt;enumeration value="Training"/>
 *     &lt;enumeration value="Flex Time"/>
 *     &lt;enumeration value="Work Compensation"/>
 *     &lt;enumeration value="Administrative"/>
 *     &lt;enumeration value="Annual leave"/>
 *     &lt;enumeration value="Bereavement"/>
 *     &lt;enumeration value="Compensatory leave time"/>
 *     &lt;enumeration value="Family and medical leave"/>
 *     &lt;enumeration value="Government-requested"/>
 *     &lt;enumeration value="Military leave"/>
 *     &lt;enumeration value="Personal"/>
 *     &lt;enumeration value="Release time"/>
 *     &lt;enumeration value="Sabbatical leave"/>
 *     &lt;enumeration value="Sick leave"/>
 *     &lt;enumeration value="Suspension"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LeaveEventCategoryType")
@XmlEnum
public enum LeaveEventCategoryType {

    @XmlEnumValue("Vacation")
    VACATION("Vacation"),
    @XmlEnumValue("Jury")
    JURY("Jury"),
    @XmlEnumValue("Training")
    TRAINING("Training"),
    @XmlEnumValue("Flex Time")
    FLEX_TIME("Flex Time"),
    @XmlEnumValue("Work Compensation")
    WORK_COMPENSATION("Work Compensation"),
    @XmlEnumValue("Administrative")
    ADMINISTRATIVE("Administrative"),
    @XmlEnumValue("Annual leave")
    ANNUAL_LEAVE("Annual leave"),
    @XmlEnumValue("Bereavement")
    BEREAVEMENT("Bereavement"),
    @XmlEnumValue("Compensatory leave time")
    COMPENSATORY_LEAVE_TIME("Compensatory leave time"),
    @XmlEnumValue("Family and medical leave")
    FAMILY_AND_MEDICAL_LEAVE("Family and medical leave"),
    @XmlEnumValue("Government-requested")
    GOVERNMENT_REQUESTED("Government-requested"),
    @XmlEnumValue("Military leave")
    MILITARY_LEAVE("Military leave"),
    @XmlEnumValue("Personal")
    PERSONAL("Personal"),
    @XmlEnumValue("Release time")
    RELEASE_TIME("Release time"),
    @XmlEnumValue("Sabbatical leave")
    SABBATICAL_LEAVE("Sabbatical leave"),
    @XmlEnumValue("Sick leave")
    SICK_LEAVE("Sick leave"),
    @XmlEnumValue("Suspension")
    SUSPENSION("Suspension"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    LeaveEventCategoryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LeaveEventCategoryType fromValue(String v) {
        for (LeaveEventCategoryType c: LeaveEventCategoryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
