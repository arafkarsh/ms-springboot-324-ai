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
package io.fusion.air.microservice.ai.examples;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import io.fusion.air.microservice.ai.examples.models.StructuredRecipePrompt;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _05_PromptTemplates {

    public static void main(String[] args) {
        // simplePrompt();
        structuredPrompt();
    }

    /**
     * Simple Prompt
     */
    public static void simplePrompt() {
        // Checkout the ChatLanguageModel Implementation details in AiBeans.java
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.GPT_3_5_TURBO);

        String template = "Create a recipe for a {{DishType}} with the following ingredients: {{Ingredients}}";
        PromptTemplate promptTemplate = PromptTemplate.from(template);

        Map<String, Object> params = new HashMap<>();
        params.put("DishType", "oven dish");
        params.put("Ingredients", "cucumber, potato, tomato, red meat, olives, olive oil");

        Prompt prompt = promptTemplate.apply(params);

        System.out.println("Request:  >>> \n"+prompt.text());
        String response = model.generate(prompt.text());
        System.out.println("Response: >>> \n"+response);
    }

    /**
     * Structured Prompt Example
     *
     * @see AiBeans
     * @see StructuredRecipePrompt
     */
    public static void structuredPrompt() {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.GPT_3_5_TURBO);
        StructuredRecipePrompt recipePrompt = new StructuredRecipePrompt(
                "oven dish",
                asList("cucumber", "potato", "tomato", "red meat", "olives", "olive oil")
        );
        Prompt prompt = StructuredPromptProcessor.toPrompt(recipePrompt);
        System.out.println("Request:  >>> \n"+prompt.text());
        System.out.println("-------------------------------------------------------------");
        String response = model.generate(prompt.text());
        System.out.println("Response: >>> \n"+response);
        System.out.println("-------------------------------------------------------------");
    }
}
