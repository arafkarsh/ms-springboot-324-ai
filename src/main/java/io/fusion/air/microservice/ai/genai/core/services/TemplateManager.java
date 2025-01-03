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
package io.fusion.air.microservice.ai.genai.core.services;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import io.fusion.air.microservice.ai.genai.core.prompts.StructuredPromptFeelings;
import io.fusion.air.microservice.ai.genai.core.prompts.StructuredPromptRecipe;
import io.fusion.air.microservice.ai.genai.utils.AiBeans;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;
import io.fusion.air.microservice.utils.Std;

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

    public static final String OVEN_DISH = "oven dish";
    public static final String DISH_TYPE = "DishType";
    public static final String INGREDIENTS = "Ingredients";
    public static final String INVALID_INPUTS = "Invalid Inputs";
    public static final String RECIPE = "cucumber, potato, tomato, red meat, olives, olive oil";


    private TemplateManager() {}

    /**
     * Handle Multiple Structured Templates
     * @param data
     * @return
     */
    public static String structuredTemplate(String data) {
        return structuredTemplate( data,  true);
    }

    /**
     * Handle Multiple Structured Templates
     * @param data
     */
    public static String structuredTemplate(String data, boolean printResult) {
        if(data == null) {
            return INVALID_INPUTS;
        }
        String[] dArray = data.split(":");
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel());
        return structuredTemplate(dArray[0], dArray[1], model, printResult);
    }

    /**
     * Handle Multiple Structured Templates
     *
     * @param template
     * @param prompt
     * @return
     */
    public static String structuredTemplate(String template, String prompt) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel());
        return structuredTemplate( template,  prompt, model);
    }

    /**
     * Handle Multiple Structured Templates
     * @param template
     * @param prompt
     * @param model
     * @return
     */
    public static String structuredTemplate(String template, String prompt, ChatLanguageModel model) {
        return structuredTemplate( template,  prompt,  model,  true);
    }

    /**
     * Handle Multiple Structured Templates
     * @param template
     * @param prompt
     */
    public static String structuredTemplate(String template, String prompt, ChatLanguageModel model, boolean printResult) {
        if(template == null || template.isEmpty()) {
            return "Invalid Inputs to Structured Template function!";
        }
        String response = "No Template Found!";
        if("[P1".equalsIgnoreCase(template)) {
            String[] data = prompt.split(",");
            String dish = data[0];
            String ingredients = prompt.replaceAll(dish+",", "").trim();
             response = structuredPromptRecipe(dish, ingredients, model, printResult);
        } else {
            Std.println("No Template found!!! ........... ");
        }
        return response;
    }

    /**
     * Simple Template
     */
    public static String simplePrompt() {
        Map<String, Object> params = new HashMap<>();
        params.put(DISH_TYPE, OVEN_DISH);
        params.put(INGREDIENTS, RECIPE);
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel());
        return simplePrompt(params, model);
    }

    /**
     * Simple Template
     * @param model
     * @return
     */
    public static String simplePrompt(ChatLanguageModel model) {
        Map<String, Object> params = new HashMap<>();
        params.put(DISH_TYPE, OVEN_DISH);
        params.put(INGREDIENTS, RECIPE);
        return simplePrompt(params, model);
    }

    /**
     * Simple Template
     *
     * @param params
     * @return
     */
    public static String simplePrompt(Map<String, Object> params) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel());
        return simplePrompt(params, model);
    }

    /**
     * Simple Template
     *
     * @param params
     * @param model
     * @return
     */
    public static String simplePrompt(Map<String, Object> params, ChatLanguageModel model) {
        if(params == null || params.size() == 0 || model == null) {
            return "Invalid Input";
        }
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
     * Complex Template
     */
    public static String complexPrompt() {
        Map<String, Object> params = new HashMap<>();
        params.put(DISH_TYPE, OVEN_DISH);
        params.put(INGREDIENTS, RECIPE);
        return complexPrompt(params);
    }

    /**
     * Complex Template
     * @param model
     * @return
     */
    public static String complexPrompt(ChatLanguageModel model) {
        Map<String, Object> params = new HashMap<>();
        params.put(DISH_TYPE, OVEN_DISH);
        params.put(INGREDIENTS, RECIPE);
        return complexPrompt(params, model);
    }

    /**
     * Complex Template
     * @param params
     * @return
     */
    public static String complexPrompt(Map<String, Object> params) {
        // Checkout the ChatLanguageModel Implementation details in AiBeans.java
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel());
        return complexPrompt(params, model);
    }

    /**
     * Complex Template
     *
     * @param params
     * @param model
     * @return
     */
    public static String complexPrompt(Map<String, Object> params, ChatLanguageModel model) {
        if(params == null || params.size() == 0 || model == null) {
            return INVALID_INPUTS;
        }
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
        return response;
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
        // Apply Template to the Prompt
        /**
         PromptTemplate promptTemplate = PromptTemplate.from(template);
         Map<String, Object> params = new HashMap<>();
         params.put("movie", _movieName);
          promptTemplate.apply(params);
         */
        return PromptTemplate.from(template);
    }

    /**
     * Structured Prompt Example - Recipe
     */
    public static String structuredPromptRecipe() {
        return structuredPromptRecipe(OVEN_DISH, RECIPE);
    }

    /**
     * Structured Prompt Example - Recipe
     * @param model
     * @return
     */
    public static String structuredPromptRecipe(ChatLanguageModel model) {
        return structuredPromptRecipe(OVEN_DISH, RECIPE, model, true);
    }

    /**
     * Structured Prompt Example - Recipe
     *
     * @param dish
     * @param ingredients
     * @return
     */
    public static String structuredPromptRecipe(String dish, String ingredients) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel());
        return structuredPromptRecipe(dish, ingredients,  model, true);
    }

    /**
     * Structured Prompt Example - Recipe
     *
     * @param dish
     * @param ingredients
     * @param model
     * @see AiBeans
     * @see StructuredPromptRecipe
     */
    public static String structuredPromptRecipe(String dish, String ingredients,
                                                ChatLanguageModel model, boolean printResult) {
        if(dish == null || ingredients == null || model == null) {
            return INVALID_INPUTS;
        }
        // Structured Prompt
        StructuredPromptRecipe recipePrompt = new StructuredPromptRecipe(dish, asList(ingredients));
        // Created Prompt
        Prompt prompt = StructuredPromptProcessor.toPrompt(recipePrompt);
        // Execute the Request
        String response = model.generate(prompt.text());
        if(printResult) AiBeans.printResult(prompt.text(), response);
        return response;
    }

    /**
     * Structured Prompt Example - Feelings
     *
     * @param feelings
     * @param content
     * @return
     */
    public static String structuredPromptFeelings(String feelings, String content) {
        return structuredPromptFeelings( feelings,  content,  false);
    }

    /**
     * Structured Prompt Example - Feelings
     *
     * @param feelings
     * @param content
     * @param print
     * @return
     */
    public static String structuredPromptFeelings(String feelings, String content, boolean print) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel());
        return structuredPromptFeelings( feelings,  content, model,  print);
    }

    /**
     * Structured Prompt Example - Feelings
     *
     * @param feelings
     * @param content
     * @param model
     * @param print
     * @return
     */
    public static String structuredPromptFeelings(String feelings, String content,
                                                  ChatLanguageModel model, boolean print) {
        if(feelings == null || content == null || model == null ){
            return "Invalid Input";
        }
        // Structured Prompt
        StructuredPromptFeelings feelingsPrompt = new StructuredPromptFeelings(feelings, content);
        // Created Prompt
        Prompt prompt = StructuredPromptProcessor.toPrompt(feelingsPrompt);
        // Execute the Request
        String response = model.generate(prompt.text());
        if(print) {
            AiBeans.printResult(prompt.text(), response);
        }
        return response;
    }
}
