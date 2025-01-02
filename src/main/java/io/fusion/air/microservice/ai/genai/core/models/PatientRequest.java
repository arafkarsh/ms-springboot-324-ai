/**
 * (C) Copyright 2024 Araf Karsh Hamid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fusion.air.microservice.ai.genai.core.models;

/**
 * Patient Diagnosis Request
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class PatientRequest {

    private final String patientName;
    private final String patientId;
    private final boolean validData;
    private final String userQuery;

    /**
     * Create Patient Request
     *
     * @param patientName
     * @param patientId
     * @param userQuery
     */
    public PatientRequest(String patientName, String patientId, String userQuery) {
        this.patientName = (patientName == null || patientName.isEmpty()) ? null : patientName;
        this.patientId = (patientId == null || patientId.isEmpty()) ? null : patientId;
        this.userQuery = userQuery;
        validData       = (this.patientName != null && this.patientId != null);
    }

    /**
     * Returns Patient Name
     * @return
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Returns Patient Id
     * @return
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Returns true if the Patient Name OR ID is found.
     * @return
     */
    public boolean isValidData() {
        return validData;
    }

    /**
     * Returns the User Query
     * @return
     */
    public String getUserQuery() {
        return userQuery;
    }
}
