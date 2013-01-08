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

package org.slc.sli.api.security.context.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slc.sli.api.constants.EntityNames;
import org.slc.sli.api.constants.ParameterConstants;
import org.slc.sli.api.security.context.PagingRepositoryDelegate;
import org.slc.sli.api.security.context.validator.DateHelper;
import org.slc.sli.api.util.SecurityUtil;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.NeutralCriteria;
import org.slc.sli.domain.NeutralQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Contains helper methods for traversing the edorg hierarchy.
 * 
 * Assumptions it makes
 * 
 * <ul>
 * <li>SEAs, LEAs, and Schools are all edorgs with organizationCategories of 'State Education
 * Agency' 'Local Education Agency', and 'School' respectively.</li>
 * <li>The parentEducationAgencyReference of a school always points to an LEA</li>
 * <li>The parentEducationAgencyReference of an LEA can point to either an SEA or another LEA</li>
 * <li>SEAs don't have a parentEducationAgencyReference and therefore are always at the top of the
 * tree</li>
 * </ul>
 * 
 * 
 */
@Component
public class EdOrgHelper {

    @Autowired
    protected PagingRepositoryDelegate<Entity> repo;

    @Autowired
    protected DateHelper dateHelper;

    /**
     * Traverse the edorg hierarchy and find all the SEAs the user is associated with, directly or
     * indirectly.
     * 
     * @param user
     * @return a list of entity IDs
     */
    public List<String> getSEAs(Entity user) {
        Set<String> directAssoc = getDirectEdorgs(user);
        NeutralQuery query = new NeutralQuery(0);
        query.addCriteria(new NeutralCriteria("_id", "in", directAssoc, false));

        Set<String> entities = new HashSet<String>();
        for (Entity entity : repo.findAll(EntityNames.EDUCATION_ORGANIZATION, query)) {
            entities.add(getSEAOfEdOrg(entity));
        }

        return new ArrayList<String>(entities);
    }

    /**
     * Determine the district of the user.
     * 
     * If the user is directly associated with an SEA, this is any LEA directly below the SEA. If
     * the user is directly
     * associated with an LEA, this is the top-most LEA i.e. the LEA directly associated with the
     * SEA.
     * 
     * @param user
     * @return a list of entity IDs
     */
    public List<String> getDistricts(Entity user) {
        Set<String> directAssoc = getDirectEdorgs(user);
        NeutralQuery query = new NeutralQuery(0);
        query.addCriteria(new NeutralCriteria("_id", "in", directAssoc, false));

        Set<String> entities = new HashSet<String>();
        for (Entity entity : repo.findAll(EntityNames.EDUCATION_ORGANIZATION, query)) {
            if (isLEA(entity)) {
                entities.add(getTopLEAOfEdOrg(entity).getEntityId());
            } else if (isSchool(entity)) {
                entities.add(getTopLEAOfEdOrg(entity).getEntityId());
            } else { // isSEA
                entities.addAll(getChildLEAsOfEdOrg(entity));
            }
        }

        return new ArrayList<String>(entities);
    }

    public List<String> getChildLEAsOfEdOrg(Entity edorgEntity) {
        List<String> toReturn = new ArrayList<String>();
        NeutralQuery query = new NeutralQuery(0);
        query.addCriteria(new NeutralCriteria("parentEducationAgencyReference", "=", edorgEntity.getEntityId()));
        for (Entity entity : repo.findAll(EntityNames.EDUCATION_ORGANIZATION, query)) {
            if (isLEA(entity)) {
                toReturn.add(entity.getEntityId());

            }
        }

        return toReturn;
    }

    /**
     * Get an ordered list of the parents of an edorg.
     * 
     * The order of the list starts with the direct parent of the edorg and ends with the SEA
     * 
     * @param edOrg
     * @return
     */
    public List<String> getParentEdOrgs(final Entity edOrg) {
        List<String> toReturn = new ArrayList<String>();

        Entity currentEdOrg = edOrg;

        if (currentEdOrg != null && currentEdOrg.getBody() != null) {
            while (currentEdOrg.getBody().get("parentEducationAgencyReference") != null) {
                String parentId = (String) currentEdOrg.getBody().get("parentEducationAgencyReference");
                toReturn.add(parentId);
                currentEdOrg = repo.findById(EntityNames.EDUCATION_ORGANIZATION, parentId);
            }
        }
        return toReturn;
    }

    /**
     * Walks the edorg hierarchy to get all schools
     * 
     * @param principal
     * @return
     */
    public List<String> getUserSchools(Entity principal) {
        List<String> schools = new ArrayList<String>();

        // Get direct associations
        Set<String> ids = getDirectEdorgs(principal);

        // get edorg entities
        while (!ids.isEmpty()) {
            NeutralQuery nq = new NeutralQuery();
            nq.addCriteria(new NeutralCriteria("parentEducationAgencyReference", "in", ids));
            Iterable<Entity> childEdorgs = repo.findAll(EntityNames.EDUCATION_ORGANIZATION, nq);

            ids.clear();
            for (Entity e : childEdorgs) {
                if (isSchool(e)) {
                    schools.add(e.getEntityId());
                } else {
                    ids.add(e.getEntityId());
                }
            }
        }

        return schools;
    }

    /**
     * Finds schools directly associated to this user
     * 
     * @param principal
     * @return
     */
    public List<String> getDirectSchools(Entity principal) {
        Set<String> ids = getDirectEdorgs(principal);
        Iterable<Entity> edorgs = repo.findAll(EntityNames.EDUCATION_ORGANIZATION, new NeutralQuery(
                new NeutralCriteria("_id", "in", ids, false)));

        List<String> schools = new ArrayList<String>();
        for (Entity e : edorgs) {
            if (isSchool(e)) {
                schools.add(e.getEntityId());
            }
        }

        return schools;
    }
    
    public List<String> getSubEdOrgHierarchy(Entity principal) {
        List<String> result = new ArrayList<String>();
        Set<String> directEdOrgs = getDirectEdorgs(principal);
        if (!directEdOrgs.isEmpty()) {
            result.addAll(directEdOrgs);
            result.addAll(getChildEdOrgs(new TreeSet<String>(directEdOrgs)));
        }
        return result;

    }

    /**
     * Recursively returns the list of all child edorgs
     * 
     * @param edOrgs
     * @return
     */
    public Set<String> getChildEdOrgs(Set<String> edOrgs) {
        NeutralQuery query = new NeutralQuery(new NeutralCriteria(ParameterConstants.PARENT_EDUCATION_AGENCY_REFERENCE,
                NeutralCriteria.CRITERIA_IN, edOrgs));
        Iterable<Entity> childrenIds = repo.findAll(EntityNames.EDUCATION_ORGANIZATION, query);
        Set<String> children = new HashSet<String>();
        for (Entity child : childrenIds) {
            children.add(child.getEntityId());
        }
        if (!children.isEmpty()) {
            children.addAll(getChildEdOrgs(children));
        }
        return children;
    }

    private Entity getTopLEAOfEdOrg(Entity entity) {
        if (entity.getBody().containsKey("parentEducationAgencyReference")) {
            Entity parentEdorg = repo.findById(EntityNames.EDUCATION_ORGANIZATION,
                    (String) entity.getBody().get("parentEducationAgencyReference"));
            if (isLEA(parentEdorg)) {
                return getTopLEAOfEdOrg(parentEdorg);
            }
        }
        return entity;
    }

    private String getSEAOfEdOrg(Entity entity) {
        if (isSEA(entity)) {
            return entity.getEntityId();
        } else {
            Entity parentEdorg = repo.findById(EntityNames.EDUCATION_ORGANIZATION,
                    (String) entity.getBody().get("parentEducationAgencyReference"));
            if (parentEdorg != null) {
                return getSEAOfEdOrg(parentEdorg);
            } else {
                warn("EdOrg {} is missing parent SEA", entity.getEntityId());
                return null;
            }
        }
    }

    /**
     * Get the collection of ed-orgs that will determine a user's security context
     * 
     * @param principal
     * @return
     */
    public Collection<String> getUserEdOrgs(Entity principal) {
        return (isTeacher(principal)) ? getDirectSchools(principal) : getStaffEdOrgLineage();
    }

    /**
     * Will go through staffEdorgAssociations that are current and get the descendant
     * edorgs that you have.
     * 
     * @return a set of the edorgs you are associated to and their children.
     */
    public Set<String> getStaffEdOrgLineage() {
        Set<String> edOrgLineage = getStaffCurrentAssociatedEdOrgs();
        return getEdorgDescendents(edOrgLineage);
    }

    public Set<String> getEdorgDescendents(Set<String> edOrgLineage) {
        edOrgLineage.addAll(getChildEdOrgs(edOrgLineage));
        return edOrgLineage;
    }

    /**
     * Get current ed-org associations for a staff member
     * 
     * @return
     */
    public Set<String> getStaffCurrentAssociatedEdOrgs() {
        NeutralQuery basicQuery = new NeutralQuery(new NeutralCriteria(ParameterConstants.STAFF_REFERENCE,
                NeutralCriteria.OPERATOR_EQUAL, SecurityUtil.getSLIPrincipal().getEntity().getEntityId()));
        Iterable<Entity> staffEdOrgs = repo.findAll(EntityNames.STAFF_ED_ORG_ASSOCIATION, basicQuery);
        List<Entity> staffEdOrgAssociations = new LinkedList<Entity>();
        if (staffEdOrgs != null) {
            for (Entity staffEdOrg : staffEdOrgs) {
                if (!isFieldExpired(staffEdOrg.getBody(), ParameterConstants.END_DATE, false)) {
                    staffEdOrgAssociations.add(staffEdOrg);
                }
            }
        }
        Set<String> edOrgIds = new HashSet<String>();
        for (Entity association : staffEdOrgAssociations) {
            edOrgIds.add((String) association.getBody().get(ParameterConstants.EDUCATION_ORGANIZATION_REFERENCE));
        }
        return edOrgIds;
        
    }

    public boolean isFieldExpired(Map<String, Object> body, String fieldName, boolean useGracePeriod) {
        return dateHelper.isFieldExpired(body, fieldName, useGracePeriod);
    }

    @SuppressWarnings("unchecked")
    public boolean isLEA(Entity entity) {
        List<String> category = (List<String>) entity.getBody().get("organizationCategories");

        if (category.contains("Local Education Agency")) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean isSEA(Entity entity) {
        List<String> category = (List<String>) entity.getBody().get("organizationCategories");

        if (category.contains("State Education Agency")) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean isSchool(Entity entity) {
        List<String> category = (List<String>) entity.getBody().get("organizationCategories");

        if (category.contains("School")) {
            return true;
        }
        return false;
    }

    private boolean isTeacher(Entity principal) {
        return principal.getType().equals(EntityNames.TEACHER);
    }

    private boolean isStaff(Entity principal) {
        return principal.getType().equals(EntityNames.STAFF);
    }
    
    public Set<String> getDirectEdorgs(Entity principal) {
        NeutralQuery basicQuery = new NeutralQuery(new NeutralCriteria(ParameterConstants.STAFF_REFERENCE,
                NeutralCriteria.OPERATOR_EQUAL, principal.getEntityId()));
        Iterable<Entity> tsas = repo.findAll(EntityNames.STAFF_ED_ORG_ASSOCIATION, basicQuery);
        Set<String> edorgs = new HashSet<String>();
        for (Entity tsa : tsas) {
            if (!dateHelper.isFieldExpired(tsa.getBody(), ParameterConstants.END_DATE, false)) {
                edorgs.add((String) tsa.getBody().get(ParameterConstants.EDUCATION_ORGANIZATION_REFERENCE));
            }
        }
        return edorgs;
    }

    public Set<String> getDirectEdorgs() {
        return getDirectEdorgs(SecurityUtil.getSLIPrincipal().getEntity());
    }

}
