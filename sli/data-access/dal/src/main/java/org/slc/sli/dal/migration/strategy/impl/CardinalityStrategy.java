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

package org.slc.sli.dal.migration.strategy.impl;

import java.util.Map;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slc.sli.dal.migration.strategy.MigrationException;
import org.slc.sli.dal.migration.strategy.MigrationStrategy;
import org.slc.sli.domain.Entity;

/**
 * Supports the migration of entities by cardinality changes.
 * 
 * @author jcole
 */

public class CardinalityStrategy implements MigrationStrategy {

    public static final String FIELD_NAME = "fieldName";
    public static final String MIN_COUNT = "minCount";
    public static final String MAX_COUNT = "maxCount";
    public static final String DEFAULT_VALUE = "defaultValue";

    private String fieldName;
    private String minCount;
    private String maxCount;
    private Object defaultValue;

    @Override
    public Entity migrate(Entity entity) throws MigrationException {

        // the only 2 cases that impact cardinality are if a field has 
        // gone from minCount of 0 to 1, or if maxCount goes from many to 1

        Object valueObject = entity.getBody().get(fieldName);
        if (valueObject == null) {
            if (minCount == "1") {

                // this is the case for a field that has gone from optional to required

                try {
                    PropertyUtils.setProperty(entity.getBody(), fieldName, defaultValue);
                } catch (IllegalAccessException e) {
                    throw new MigrationException(e);
                } catch (InvocationTargetException e) {
                    throw new MigrationException(e);
                } catch (NoSuchMethodException e) {
                    throw new MigrationException(e);
                }
            }
        }
        else if (valueObject instanceof List) {
            if (maxCount == "1") {

                // this is the case where we've gone from many to 1

                List valueList = (List) valueObject;
                Object fieldValue = defaultValue;
                if (valueList.size() > 0) {
                    fieldValue = valueList.get(0);
                }

                try {
                    PropertyUtils.setProperty(entity.getBody(), fieldName, fieldValue);
                } catch (IllegalAccessException e) {
                    throw new MigrationException(e);
                } catch (InvocationTargetException e) {
                    throw new MigrationException(e);
                } catch (NoSuchMethodException e) {
                    throw new MigrationException(e);
                }
            }
        }

        return entity;
    }

    @Override
    public void setParameters(Map<String, Object> parameters) throws MigrationException {

        if (parameters == null) {
            throw new MigrationException(new IllegalArgumentException("Cardinality strategy missing required arguments: "));
        }

        if (!parameters.containsKey(FIELD_NAME)) {
            throw new MigrationException(new IllegalArgumentException("Cardinality strategy missing required argument: " + FIELD_NAME));
        }

        if (!parameters.containsKey(MIN_COUNT)) {
            throw new MigrationException(new IllegalArgumentException("Cardinality strategy missing required argument: " + MIN_COUNT));
        }

        if (!parameters.containsKey(MAX_COUNT)) {
            throw new MigrationException(new IllegalArgumentException("Cardinality strategy missing required argument: " + MAX_COUNT));
        }

        this.fieldName = parameters.get(FIELD_NAME).toString();
        this.minCount = parameters.get(MIN_COUNT).toString();
        this.maxCount = parameters.get(MAX_COUNT).toString();
        this.defaultValue = DEFAULT_VALUE;
    }

}
