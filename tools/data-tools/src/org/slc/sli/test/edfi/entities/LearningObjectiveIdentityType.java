/*
 * Copyright 2012 Shared Learning Collaborative, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.31 at 09:35:49 AM EDT 
//


package org.slc.sli.test.edfi.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Encapsulates the possible attributes that can be used to lookup the identity of the Learning Objectives.
 * 
 * <p>Java class for LearningObjectiveIdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LearningObjectiveIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="LearningObjectiveId" type="{http://ed-fi.org/0100}LearningStandardId"/>
 *         &lt;element name="Objective" type="{http://ed-fi.org/0100}Objective"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLC-LearningObjectiveIdentityType", propOrder = {
    "objective", "academicSubject", "objectiveGradeLevel"
})
public class LearningObjectiveIdentityType {

    @XmlElement(name = "Objective")
    protected String objective;
    @XmlElement(name = "AcademicSubject")
    protected AcademicSubjectType academicSubject;
    @XmlElement(name = "ObjectiveGradeLevel")
    protected GradeLevelType objectiveGradeLevel;
    
    public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public AcademicSubjectType getAcademicSubject() {
		return academicSubject;
	}
	public void setAcademicSubject(AcademicSubjectType academicSubject) {
		this.academicSubject = academicSubject;
	}
	public GradeLevelType getObjectiveGradeLevel() {
		return objectiveGradeLevel;
	}
	public void setObjectiveGradeLevel(GradeLevelType objectiveGradeLevel) {
		this.objectiveGradeLevel = objectiveGradeLevel;
	}
	
    
	
}
