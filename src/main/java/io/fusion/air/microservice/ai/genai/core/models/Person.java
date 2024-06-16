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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.fusion.air.microservice.utils.Utils;

import java.time.LocalDate;

/**
 * Person Data Model with following fields
 *
 * 1. First Name, Middle Name and Last Name
 * 2. Date of Birth
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class Person {

    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    /**
     {
         "Person": {
         "firstName": "John",
         "lastName": "Doe",
         "birthDate": "1968-07-04"
         }
     }
     * @return
     */
    @Override
    @JsonIgnore
    public String toString() {
        return Utils.toJsonString(this);
    }
}
