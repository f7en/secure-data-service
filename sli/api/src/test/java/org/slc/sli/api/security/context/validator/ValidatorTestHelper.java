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

package org.slc.sli.api.security.context.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slc.sli.api.constants.EntityNames;
import org.slc.sli.api.constants.ParameterConstants;
import org.slc.sli.api.security.context.PagingRepositoryDelegate;
import org.slc.sli.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorTestHelper {
    @Autowired
    private PagingRepositoryDelegate<Entity> repo;
    
    public String getBadDate() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime past = DateTime.now().minusYears(10);
        return past.toString(fmt);
    }
    
    public void generateStaffEdorg(String staffId, String edOrgId, boolean isExpired) {
        Map<String, Object> staffEdorg = new HashMap<String, Object>();
        staffEdorg.put(ParameterConstants.STAFF_REFERENCE, staffId);
        staffEdorg.put(ParameterConstants.EDUCATION_ORGANIZATION_REFERENCE, edOrgId);
        expireAssociation(isExpired, staffEdorg);
        repo.create(EntityNames.STAFF_ED_ORG_ASSOCIATION, staffEdorg);
    }
    
    public Entity generateEdorgWithParent(String parentId) {
        Map<String, Object> edorg = new HashMap<String, Object>();
        if (parentId != null) {
            edorg.put(ParameterConstants.PARENT_EDUCATION_AGENCY_REFERENCE, parentId);
        }
        return repo.create(EntityNames.EDUCATION_ORGANIZATION, edorg);
    }
    
    public Entity generateSection(String edorgId) {
        Map<String, Object> section = new HashMap<String, Object>();
        section.put(ParameterConstants.SCHOOL_ID, edorgId);
        return repo.create(EntityNames.SECTION, section);
        
    }
    
    public void generateSSA(String studentId, String sectionId, boolean isExpired) {
        Map<String, Object> ssaBody = new HashMap<String, Object>();
        ssaBody.put(ParameterConstants.SECTION_ID, sectionId);
        ssaBody.put(ParameterConstants.STUDENT_ID, studentId);
        expireAssociation(isExpired, ssaBody);
        repo.create(EntityNames.STUDENT_SECTION_ASSOCIATION, ssaBody);
    }
    
    private void expireAssociation(boolean isExpired, Map<String, Object> body) {
        if (isExpired) {
            body.put(ParameterConstants.END_DATE, getBadDate());
        }
    }
    
    public void generateTeacherSchool(String teacherId, String edorgId) {
        Map<String, Object> tsaBody = new HashMap<String, Object>();
        tsaBody.put(ParameterConstants.TEACHER_ID, teacherId);
        tsaBody.put(ParameterConstants.SCHOOL_ID, edorgId);
        
        repo.create(EntityNames.TEACHER_SCHOOL_ASSOCIATION, tsaBody);
    }
    
    public void generateTSA(String teacherId, String sectionId, boolean isExpired) {
        Map<String, Object> tsaBody = new HashMap<String, Object>();
        tsaBody.put(ParameterConstants.TEACHER_ID, teacherId);
        tsaBody.put(ParameterConstants.SECTION_ID, sectionId);
        expireAssociation(isExpired, tsaBody);
        repo.create(EntityNames.TEACHER_SECTION_ASSOCIATION, tsaBody);
    }
    
    public Entity generateCohort(String edOrgId) {
        Map<String, Object> cohortBody = new HashMap<String, Object>();
        cohortBody.put("educationOrgId", edOrgId);
        
        return repo.create(EntityNames.COHORT, cohortBody);
    }
    
    public void generateStaffCohort(String teacherId, String cohortId, boolean isExpired, boolean studentAccess) {
        Map<String, Object> staffCohort = new HashMap<String, Object>();
        staffCohort.put(ParameterConstants.STAFF_ID, teacherId);
        staffCohort.put(ParameterConstants.COHORT_ID, cohortId);
        expireAssociation(isExpired, staffCohort);
        staffCohort.put(ParameterConstants.STUDENT_RECORD_ACCESS, studentAccess);
        
        repo.create(EntityNames.STAFF_COHORT_ASSOCIATION, staffCohort);
        
    }
    
    public void generateStudentCohort(String studentId, String cohortId, boolean isExpired) {
        Map<String, Object> studentCohort = new HashMap<String, Object>();
        studentCohort.put(ParameterConstants.STUDENT_ID, studentId);
        studentCohort.put(ParameterConstants.COHORT_ID, cohortId);
        expireAssociation(isExpired, studentCohort);
        
        repo.create(EntityNames.STUDENT_COHORT_ASSOCIATION, studentCohort);
        
    }
    
    public void generateStudentProgram(String studentId, String programId, boolean isExpired) {
        Map<String, Object> studentProgram = new HashMap<String, Object>();
        studentProgram.put(ParameterConstants.STUDENT_ID, studentId);
        studentProgram.put(ParameterConstants.PROGRAM_ID, programId);
        expireAssociation(isExpired, studentProgram);
        
        repo.create(EntityNames.STUDENT_PROGRAM_ASSOCIATION, studentProgram);
        
    }
    
    public Entity generateEdorgWithProgram(List<String> programIds) {
        Map<String, Object> edorgBody = new HashMap<String, Object>();
        edorgBody.put(ParameterConstants.PROGRAM_REFERENCE, programIds);
        return repo.create(EntityNames.EDUCATION_ORGANIZATION, edorgBody);
    }
    
    public Entity generateProgram() {
        return repo.create(EntityNames.PROGRAM, new HashMap<String, Object>());
    }
    
    public void generateStaffProgram(String teacherId, String programId, boolean isExpired, boolean studentAccess) {
        Map<String, Object> staffProgram = new HashMap<String, Object>();
        staffProgram.put(ParameterConstants.STAFF_ID, teacherId);
        staffProgram.put(ParameterConstants.PROGRAM_ID, programId);
        expireAssociation(isExpired, staffProgram);
        staffProgram.put(ParameterConstants.STUDENT_RECORD_ACCESS, studentAccess);
        
        repo.create(EntityNames.STAFF_PROGRAM_ASSOCIATION, staffProgram);
        
    }
}
