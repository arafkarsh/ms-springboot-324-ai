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
package io.fusion.air.microservice.ai.services;

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
 * Template Manager
 *
 * Manages Structured Templates
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class TemplateManager {


    /**
     * Handle Multiple Structured Templates
     *
     * @param data
     */
    public static void structuredTemplate(String data) {
        String[] dArray = data.split(":");
        structuredTemplate(dArray[0], dArray[1]);
    }

    /**
     * Handle Multiple Structured Templates
     *
     * @param template
     * @param prompt
     */
    public static void structuredTemplate(String template, String prompt) {
        if(template == null || template.length() == 0) {
            return;
        }
        if("[P1".equalsIgnoreCase(template)) {
            String[] data = prompt.split(",");
            String dish = data[0];
            String ingredients = prompt.replaceAll(dish+",", "").trim();
            structuredRecipePrompt(dish, ingredients);
        } else {
            System.out.println("No Template found!!! ........... ");
        }
    }

    /**
     * Simple Template
     */
    public static void simplePrompt() {
        Map<String, Object> params = new HashMap<>();
        params.put("DishType", "oven dish");
        params.put("Ingredients", "cucumber, potato, tomato, red meat, olives, olive oil");
        simplePrompt(params);
    }
    /**
     * Simple Template
     */
    public static void simplePrompt(Map<String, Object> params) {
        // Checkout the ChatLanguageModel Implementation details in AiBeans.java
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.GPT_3_5_TURBO);
        // Template
        String template = "Create a recipe for a {{DishType}} with the following ingredients: {{Ingredients}}";
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        // Apply Template to the Prompt
        Prompt prompt = promptTemplate.apply(params);

        // Execute the Request
        System.out.println("Request:  >>> \n"+prompt.text());
        String response = model.generate(prompt.text());
        System.out.println("Response: >>> \n"+response);
    }

    /**
     * Complex Prompt
     */
    public static void complexPrompt() {
        Map<String, Object> params = new HashMap<>();
        params.put("DishType", "oven dish");
        params.put("Ingredients", "cucumber, potato, tomato, red meat, olives, olive oil");
        complexPrompt(params);
    }

    /**
     * Complex Template
     *
     * @param params
     */
    public static void complexPrompt(Map<String, Object> params ) {
        // Checkout the ChatLanguageModel Implementation details in AiBeans.java
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.GPT_3_5_TURBO);

        // Complex Template
        String template = """
                        Create a recipe of a '{{DishType}}' that can be prepared using only '{{Ingredients}}'.
                        Structure your answer in the following way:
                
                        Recipe name: ...
                        Description: ...
                        Preparation time: ...
                
                        Required ingredients:
                        - ...
                        - ...
                
                        Instructions:
                        - ...
                        - ...
                """;

        PromptTemplate promptTemplate = PromptTemplate.from(template);
        // Apply Template to the Prompt
        Prompt prompt = promptTemplate.apply(params);

        // Execute the Request
        System.out.println("Request:  >>> \n"+prompt.text());
        String response = model.generate(prompt.text());
        System.out.println("Response: >>> \n"+response);
    }

    /**
     * Movie Template
     *
     * @return
     */
    public static PromptTemplate createMoviePrompt() {
        // Complex Template
        String template = """
                        Create a review of the cinema {{movieName}}. 
                        Structure your answer in the following way:
                
                        Movie name: ...
                        Movie Rating: ...
                        Description: ...
                
                        Characters:
                        - ...
                        - ...
                
                        Key Events:
                        - ...
                        - ...
                
                        Review Conclusion: ...
                """;
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        // Apply Template to the Prompt
        // Map<String, Object> params = new HashMap<>();
        // params.put("movie", _movieName);
         // promptTemplate.apply(params);
        return promptTemplate;
    }

    /**
     * Structured Recipe Prompt Example
     *
     */
    public static void structuredRecipePrompt() {
        structuredRecipePrompt("oven dish", "cucumber, potato, tomato, red meat, olives, olive oil");
    }

    /**
     * Structured Recipe Prompt Example
     *
     * @param dish
     * @param ingredients
     * @see AiBeans
     * @see StructuredRecipePrompt
     */
    public static void structuredRecipePrompt(String dish, String ingredients) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.GPT_3_5_TURBO);
        // Structured Prompt
        StructuredRecipePrompt recipePrompt = new StructuredRecipePrompt(dish, asList(ingredients));
        // Created Prompt
        Prompt prompt = StructuredPromptProcessor.toPrompt(recipePrompt);
        // Execute the Request
        System.out.println("Request:  >>> \n"+prompt.text());
        System.out.println("-------------------------------------------------------------");
        String response = model.generate(prompt.text());
        System.out.println("Response: >>> \n"+response);
        System.out.println("-------------------------------------------------------------");
    }
}
