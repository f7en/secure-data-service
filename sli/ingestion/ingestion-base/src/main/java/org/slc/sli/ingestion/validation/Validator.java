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

package org.slc.sli.ingestion.validation;

import org.slc.sli.ingestion.reporting.AbstractMessageReport;
import org.slc.sli.ingestion.reporting.AbstractReportStats;

/**
 * Validator Interface.
 *
 * @author okrook
 *
 * @param <T>
 *            Type of the object being validated
 */
public interface Validator<T> {

    /**
     * Validates the object.
     *
     * @param object
     *            thing to be validated
     * @param report
     *            receives messages resulting from the validation
     * @param reportStats
     *            track statistics for this validation
     * @return <code>true</code> if the object is valid
     */
    boolean isValid(T object, AbstractMessageReport report, AbstractReportStats reportStats);

}
