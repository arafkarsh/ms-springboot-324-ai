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
package io.fusion.air.microservice.ai.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.langchain4j.model.output.structured.Description;
import io.fusion.air.microservice.utils.Utils;

import java.util.List;

/**
 * Recipe Data Model
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class Recipe {

    @JsonProperty
    @Description("short title, 5 words maximum")
    private String title;

    @JsonProperty
    @Description("short description, 3 sentences maximum")
    private String description;

    @JsonProperty
    @Description("each step (5 to 10 steps) should be described in 6 to 12 words, steps should rhyme with each other")
    private List<String> steps;

    @JsonProperty
    private Integer preparationTimeMinutes;

    @JsonIgnore
    @Override
    public String toString() {
        return Utils.toJsonString(this);
    }
}
