/*
 * Copyright 2012-2013 inBloom, Inc. and its affiliates.
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

package org.slc.sli.api.security.context.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slc.sli.api.security.context.validator.StudentValidatorHelper;
import org.slc.sli.common.util.datetime.DateHelper;
import org.slc.sli.dal.convert.SuperDocIdUtility;
import org.slc.sli.domain.Entity;

/**
 * Contains helper methods to traverse to the sections of a given user
 *
 */
@Component
public class SectionHelper {

    @Autowired
    private DateHelper dateHelper;

    @Autowired
    private StudentValidatorHelper studentHelper;

    /**
     * Traverse the edorg hierarchy and find all the SEAs the user is associated with, directly or
     * indirectly.
     *
     * @param teacher
     * @return a list of sections for the teacher
     */
    public List<String> getTeachersSections(Entity teacher) {
        return studentHelper.getTeachersSectionIds(teacher, false);
    }

    public List<String> getStudentsSections(Entity student) {
        // Throw exception if calling this function as a non-student
        if (!"student".equals(student.getType())) {
            throw new IllegalArgumentException("Cannot get a student's sections if not a student");
        }

        List<Map<String, Object>> sections = student.getDenormalizedData().get("section");
        List<String> sectionIds = new ArrayList<String>();
        for (Map<String, Object> section : sections) {
            if(!dateHelper.isFieldExpired(section, "endDate", false)) {
                sectionIds.add((String) section.get("_id"));
            }
        }

        return sectionIds;
    }

    public List<String> getSectionsFromStudentSectionAssociation(String[] studentSectionIds) {
        List<String> sectionIds = new ArrayList<String>();
        for (String ssaId : studentSectionIds) {
            String parentId = SuperDocIdUtility.getParentId(ssaId);
            sectionIds.add(parentId);
        }
        return sectionIds;
    }
}
