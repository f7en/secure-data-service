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
package org.slc.sli.bulk.extract.context.resolver.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.MapMaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import org.slc.sli.bulk.extract.context.resolver.ContextResolver;
import org.slc.sli.common.constants.EntityNames;
import org.slc.sli.common.util.datetime.DateHelper;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.NeutralCriteria;
import org.slc.sli.domain.NeutralQuery;
import org.slc.sli.domain.Repository;

/**
 * resolve a staff or teacher
 * 
 * @author ycao
 * 
 */
@Component
public class StaffTeacherContextResolver implements ContextResolver {
    private static final Logger LOG = LoggerFactory.getLogger(SectionContextResolver.class);

    @Autowired
    private EducationOrganizationContextResolver edOrgResolver;
    
    @Autowired
    @Qualifier("secondaryRepo")
    private Repository<Entity> repo;
    
    @Autowired
    private DateHelper dateHelper;

    private final Map<String, Set<String>> staffEdOrgCache = new MapMaker().softValues().makeMap();
    
    private final static String STAFF_REFERENCE = "staffReference";
    private final static String EDORG_REFERENCE = "educationOrganizationReference";
    private final static String END_DATE = "endDate";

    @Override
    public Set<String> findGoverningLEA(Entity entity) {
        Set<String> leas = new HashSet<String>();
        if (getStaffEdOrgCache().containsKey(entity.getEntityId())) {
            LOG.debug("got LEAs from cache for {}", entity);
            return getStaffEdOrgCache().get(entity.getEntityId());
        }
       
        String id = entity.getEntityId();
        if (id == null) {
            return leas;
        }
        
        Iterable<Entity> staffEdorgAssociations = repo.findAll(EntityNames.STAFF_ED_ORG_ASSOCIATION, buildStaffEdorgQuery(id));
        for (Entity association : staffEdorgAssociations) {
            if (!dateHelper.isFieldExpired(association.getBody(), END_DATE)) {
                String edorgReference = (String) association.getBody().get(EDORG_REFERENCE);
                if (edorgReference != null) {
                    leas.addAll(edOrgResolver.findGoverningLEA(edorgReference));
                }
            }
        }
        
        getStaffEdOrgCache().put(entity.getEntityId(), leas);
        return leas;
    }
    
    NeutralQuery buildStaffEdorgQuery(String id) {
        NeutralQuery q = new NeutralQuery(new NeutralCriteria(STAFF_REFERENCE, NeutralCriteria.OPERATOR_EQUAL, id));
        return q;
    }

    private Map<String, Set<String>> getStaffEdOrgCache() {
        return this.staffEdOrgCache;
    }

    void setDateHelper(DateHelper dateHelper) {
        this.dateHelper = dateHelper;
    }

}
