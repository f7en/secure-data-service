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
 * <p>Java class for SubmissionCertificationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubmissionCertificationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Official"/>
 *     &lt;enumeration value="Unofficial"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SubmissionCertificationType")
@XmlEnum
public enum SubmissionCertificationType {

    @XmlEnumValue("Official")
    OFFICIAL("Official"),
    @XmlEnumValue("Unofficial")
    UNOFFICIAL("Unofficial");
    private final String value;

    SubmissionCertificationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SubmissionCertificationType fromValue(String v) {
        for (SubmissionCertificationType c: SubmissionCertificationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
