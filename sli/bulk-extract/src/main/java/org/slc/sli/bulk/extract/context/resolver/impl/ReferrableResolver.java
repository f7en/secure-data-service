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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.slc.sli.bulk.extract.context.resolver.ContextResolver;
import org.slc.sli.bulk.extract.delta.DeltaEntityIterator;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Resolver that supports the ability to find the reference from an id.
 * @author nbrown
 *
 */
public abstract class ReferrableResolver implements ContextResolver {
    private static final Logger LOG = LoggerFactory.getLogger(ReferrableResolver.class);
    
    @Autowired
    @Qualifier("secondaryRepo")
    private Repository<Entity> repo;
    
    private final Map<String, Set<String>> cache = new HashMap<String, Set<String>>();

    public ReferrableResolver() {
        super();
    }
    
    /**
     * Return a list of edOrg IDs that have ownership of the given entity
     * 
     * @param entity
     *            the entity to extract
     * @return a set of Strings which are IDs of the top level LEA
     */
    public Set<String> findGoverningEdOrgs(Entity entity) {
        LOG.debug("resolving {}", entity);
        if (entity == null || entity.getEntityId() == null) {
            return Collections.emptySet();
        }

        Set<String> edOrgs = resolve(entity, entity);

        return edOrgs;
    }

    /**
     * Find the governing edOrgs based on the id
     * 
     * @param id
     *            the id of the entity to look up
     * @param entityToExtract
     *              the entity to be extracted
     * @return the list of edOrgs
     */
    public Set<String> findGoverningEdOrgs(String id, Entity entityToExtract) {
        if (id == null) {
            return Collections.emptySet();
        }

        Set<String> edOrgs = new HashSet<String>();

        Entity entity = getRepo().findOne(getCollection(), DeltaEntityIterator.buildQuery(getCollection(), id));
        if (entity != null && entity.getEntityId() != null) {
            edOrgs = resolve(entity, entityToExtract);
        }

        return edOrgs;
    }


    protected Repository<Entity> getRepo() {
        return repo;
    }
    
    protected Map<String, Set<String>> getCache() {
        return cache;
    }
    
    protected abstract String getCollection();
    
    protected abstract Set<String> resolve(Entity baseEntity, Entity entityToExtract);

}