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
package io.fusion.air.microservice.ai.core.prompts;

import dev.langchain4j.model.input.structured.StructuredPrompt;

/**
 * iCare - Diagnosis Structure Prompt
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@StructuredPrompt({
        "Convey the diagnosis for the {{patient}} based on the diagnosis available.",
        "In the Diagnosis Summary, give the diagnosis as well as the prescription along with diagnosis",
        "Diagnosis must contain the date",
        "Structure your answer in the following way:",
        "Name: ...",
        "Patient ID: ...",
        "Date of Birth: ...",

        "Chronic Conditions: ...",

        "Diagnosis Summary:",
        "- ...",
        "- ..."
})
public class StructuredPromptDiagnosisSummary {

    private final String patient;

    /**
     * @param _patient
     */
    public StructuredPromptDiagnosisSummary(String _patient) {
        this.patient = _patient;
    }

    public String getPatient() {
        return patient;
    }
}
