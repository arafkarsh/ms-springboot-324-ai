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
package io.fusion.air.microservice.ai.examples.assistants;

import io.fusion.air.microservice.ai.examples.models.Recipe;
import io.fusion.air.microservice.ai.examples.prompts.StructuredPromptRecipe;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public interface Chef {

    public Recipe createRecipeFrom(String... ingredients);

    public Recipe createRecipe(StructuredPromptRecipe prompt);
}
