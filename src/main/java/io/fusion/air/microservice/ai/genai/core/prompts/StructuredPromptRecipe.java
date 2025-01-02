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
package io.fusion.air.microservice.ai.genai.core.prompts;

import dev.langchain4j.model.input.structured.StructuredPrompt;

import java.util.List;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@StructuredPrompt({
        "Create a recipe of a {{dish}} that can be prepared using only {{ingredients}}.",
        "Structure your answer in the following way:",

        "Recipe name: ...",
        "Description: ...",
        "Preparation time: ...",

        "Required ingredients:",
        "- ...",
        "- ...",

        "Instructions:",
        "- ...",
        "- ..."
})
public class StructuredPromptRecipe {

    private final String dish;
    private final List<String> ingredients;

    /**
     *
     * @param dish
     * @param ingredients
     */
    public StructuredPromptRecipe(String dish, List<String> ingredients) {
        this.dish = dish;
        this.ingredients = ingredients;
    }

    public String getDish() {
        return dish;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
