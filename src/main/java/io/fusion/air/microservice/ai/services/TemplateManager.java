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
import io.fusion.air.microservice.ai.examples.core.prompts.StructuredPromptFeelings;
import io.fusion.air.microservice.ai.examples.core.prompts.StructuredPromptRecipe;
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
    public static String structuredTemplate(String data) {
        String[] dArray = data.split(":");
        return structuredTemplate(dArray[0], dArray[1]);
    }

    /**
     * Handle Multiple Structured Templates
     *
     * @param template
     * @param prompt
     */
    public static String structuredTemplate(String template, String prompt) {
        if(template == null || template.length() == 0) {
            return "Invalid Inputs to Structured Template function!";
        }
        String response = "No Template Found!";
        if("[P1".equalsIgnoreCase(template)) {
            String[] data = prompt.split(",");
            String dish = data[0];
            String ingredients = prompt.replaceAll(dish+",", "").trim();
             response = structuredPromptRecipe(dish, ingredients);
        } else {
            System.out.println("No Template found!!! ........... ");
        }
        return response;
    }

    /**
     * Simple Template
     */
    public static String simplePrompt() {
        Map<String, Object> params = new HashMap<>();
        params.put("DishType", "oven dish");
        params.put("Ingredients", "cucumber, potato, tomato, red meat, olives, olive oil");
        return simplePrompt(params);
    }
    /**
     * Simple Template
     */
    public static String simplePrompt(Map<String, Object> params) {
        // Checkout the ChatLanguageModel Implementation details in AiBeans.java
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.getAlgo());
        // Template
        String template = "Create a recipe for a {{DishType}} with the following ingredients: {{Ingredients}}";
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        // Apply Template to the Prompt
        Prompt prompt = promptTemplate.apply(params);

        // Execute the Request
        String response = model.generate(prompt.text());
        AiBeans.printResult(prompt.text(), response);

        return response;
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
                .createChatLanguageModel(AiConstants.getAlgo());
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
        String response = model.generate(prompt.text());
        AiBeans.printResult(prompt.text(), response);
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
    public static String structuredPromptRecipe() {
        return structuredPromptRecipe("oven dish", "cucumber, potato, tomato, red meat, olives, olive oil");
    }

    /**
     * Structured Recipe Prompt Example
     *
     * @param dish
     * @param ingredients
     * @see AiBeans
     * @see StructuredPromptRecipe
     */
    public static String structuredPromptRecipe(String dish, String ingredients) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.getAlgo());
        // Structured Prompt
        StructuredPromptRecipe recipePrompt = new StructuredPromptRecipe(dish, asList(ingredients));
        // Created Prompt
        Prompt prompt = StructuredPromptProcessor.toPrompt(recipePrompt);
        // Execute the Request
        String response = model.generate(prompt.text());
        AiBeans.printResult(prompt.text(), response);
        return response;
    }

    /**
     * Feelings Prompt
     * @param _feelings
     * @param _content
     * @return
     */
    public static String structuredPromptFeelings(String _feelings, String _content) {
        return structuredPromptFeelings( _feelings,  _content,  false);
    }

    /**
     * Feelings Prompt
     *
     * @param _feelings
     * @param _content
     * @param _print
     * @return
     */
    public static String structuredPromptFeelings(String _feelings, String _content, boolean _print) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.getAlgo());
        // Structured Prompt
        StructuredPromptFeelings feelingsPrompt = new StructuredPromptFeelings(_feelings, _content);
        // Created Prompt
        Prompt prompt = StructuredPromptProcessor.toPrompt(feelingsPrompt);
        // Execute the Request
        String response = model.generate(prompt.text());
        if(_print) {
            AiBeans.printResult(prompt.text(), response);
        }
        return response;
    }
}
