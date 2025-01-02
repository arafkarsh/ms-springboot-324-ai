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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusion.air.microservice.utils.Utils;

/**
 * Patient Data Model with following fields
 *
 * 1. First Name and Last Name
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class Patient {

    @JsonProperty
    private String firstName;
    @JsonProperty
    private String middleName = "";
    @JsonProperty
    private String lastName;

    /**
     {
         "Patient": {
         "firstName": "John",
         "middleName":  "S",
         "lastName": "Doe",
        }
     }
     * @return
     */
    @Override
    @JsonIgnore
    public String toString() {
        return (middleName != null || middleName.isEmpty())
                    ? firstName + " "  + lastName
                    : firstName + " " + middleName + " " + lastName;
    }

    @JsonIgnore
    public String toJsonString() {
        return Utils.toJsonString(this);
    }

    /**
     * Checks if its valid
     * @return
     */
    public boolean isValid() {
        return (!toString().isEmpty());
    }
}
