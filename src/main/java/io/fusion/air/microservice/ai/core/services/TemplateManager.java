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
package io.fusion.air.microservice.ai.core.services;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import io.fusion.air.microservice.ai.core.prompts.StructuredPromptFeelings;
import io.fusion.air.microservice.ai.core.prompts.StructuredPromptRecipe;
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
     * @param _data
     */
    public static String structuredTemplate(String _data) {
        if(_data == null) {
            return "Invalid Inputs";
        }
        String[] dArray = _data.split(":");
        return structuredTemplate(dArray[0], dArray[1]);
    }

    /**
     * Handle Multiple Structured Templates
     *
     * @param _template
     * @param _prompt
     * @return
     */
    public static String structuredTemplate(String _template, String _prompt) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getAlgo());
        return structuredTemplate( _template,  _prompt, model);
    }

    /**
     * Handle Multiple Structured Templates
     *
     * @param _template
     * @param _prompt
     */
    public static String structuredTemplate(String _template, String _prompt, ChatLanguageModel _model) {
        if(_template == null || _template.length() == 0) {
            return "Invalid Inputs to Structured Template function!";
        }
        String response = "No Template Found!";
        if("[P1".equalsIgnoreCase(_template)) {
            String[] data = _prompt.split(",");
            String dish = data[0];
            String ingredients = _prompt.replaceAll(dish+",", "").trim();
             response = structuredPromptRecipe(dish, ingredients, _model);
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
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getAlgo());
        return simplePrompt(params, model);
    }

    /**
     * Simple Template
     * @param _model
     * @return
     */
    public static String simplePrompt(ChatLanguageModel _model) {
        Map<String, Object> params = new HashMap<>();
        params.put("DishType", "oven dish");
        params.put("Ingredients", "cucumber, potato, tomato, red meat, olives, olive oil");
        return simplePrompt(params, _model);
    }

    /**
     * Simple Template
     *
     * @param _params
     * @return
     */
    public static String simplePrompt(Map<String, Object> _params) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getAlgo());
        return simplePrompt(_params, model);
    }

    /**
     * Simple Template
     *
     * @param _params
     * @param _model
     * @return
     */
    public static String simplePrompt(Map<String, Object> _params, ChatLanguageModel _model) {
        if(_params == null || _params.size() == 0 || _model == null) {
            return "Invalid Input";
        }
        // Template
        String template = "Create a recipe for a {{DishType}} with the following ingredients: {{Ingredients}}";
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        // Apply Template to the Prompt
        Prompt prompt = promptTemplate.apply(_params);

        // Execute the Request
        String response = _model.generate(prompt.text());
        AiBeans.printResult(prompt.text(), response);

        return response;
    }

    /**
     * Complex Template
     */
    public static String complexPrompt() {
        Map<String, Object> params = new HashMap<>();
        params.put("DishType", "oven dish");
        params.put("Ingredients", "cucumber, potato, tomato, red meat, olives, olive oil");
        return complexPrompt(params);
    }

    /**
     * Complex Template
     * @param _model
     * @return
     */
    public static String complexPrompt(ChatLanguageModel _model) {
        Map<String, Object> params = new HashMap<>();
        params.put("DishType", "oven dish");
        params.put("Ingredients", "cucumber, potato, tomato, red meat, olives, olive oil");
        return complexPrompt(params, _model);
    }

    /**
     * Complex Template
     * @param _params
     * @return
     */
    public static String complexPrompt(Map<String, Object> _params) {
        // Checkout the ChatLanguageModel Implementation details in AiBeans.java
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getAlgo());
        return complexPrompt(_params, model);
    }

    /**
     * Complex Template
     *
     * @param _params
     * @param _model
     * @return
     */
    public static String complexPrompt(Map<String, Object> _params, ChatLanguageModel _model) {
        if(_params == null || _params.size() == 0 || _model == null) {
            return "Invalid Inputs";
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
        Prompt prompt = promptTemplate.apply(_params);

        // Execute the Request
        String response = _model.generate(prompt.text());
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
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        // Apply Template to the Prompt
        // Map<String, Object> params = new HashMap<>();
        // params.put("movie", _movieName);
         // promptTemplate.apply(params);
        return promptTemplate;
    }

    /**
     * Structured Prompt Example - Recipe
     */
    public static String structuredPromptRecipe() {
        return structuredPromptRecipe("oven dish", "cucumber, potato, tomato, red meat, olives, olive oil");
    }

    /**
     * Structured Prompt Example - Recipe
     * @param _model
     * @return
     */
    public static String structuredPromptRecipe(ChatLanguageModel _model) {
        return structuredPromptRecipe("oven dish", "cucumber, potato, tomato, red meat, olives, olive oil", _model);
    }

    /**
     * Structured Prompt Example - Recipe
     *
     * @param _dish
     * @param _ingredients
     * @return
     */
    public static String structuredPromptRecipe(String _dish, String _ingredients) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getAlgo());
        return structuredPromptRecipe(_dish, _ingredients,  model);
    }

    /**
     * Structured Prompt Example - Recipe
     *
     * @param _dish
     * @param _ingredients
     * @param _model
     * @see AiBeans
     * @see StructuredPromptRecipe
     */
    public static String structuredPromptRecipe(String _dish, String _ingredients, ChatLanguageModel _model) {
        if(_dish == null || _ingredients == null || _model == null) {
            return "Invalid Inputs";
        }
        // Structured Prompt
        StructuredPromptRecipe recipePrompt = new StructuredPromptRecipe(_dish, asList(_ingredients));
        // Created Prompt
        Prompt prompt = StructuredPromptProcessor.toPrompt(recipePrompt);
        // Execute the Request
        String response = _model.generate(prompt.text());
        AiBeans.printResult(prompt.text(), response);
        return response;
    }

    /**
     * Structured Prompt Example - Feelings
     *
     * @param _feelings
     * @param _content
     * @return
     */
    public static String structuredPromptFeelings(String _feelings, String _content) {
        return structuredPromptFeelings( _feelings,  _content,  false);
    }

    /**
     * Structured Prompt Example - Feelings
     *
     * @param _feelings
     * @param _content
     * @param _print
     * @return
     */
    public static String structuredPromptFeelings(String _feelings, String _content, boolean _print) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getAlgo());
        return structuredPromptFeelings( _feelings,  _content, model,  _print);
    }

    /**
     * Structured Prompt Example - Feelings
     *
     * @param _feelings
     * @param _content
     * @param _model
     * @param _print
     * @return
     */
    public static String structuredPromptFeelings(String _feelings, String _content,
                                                  ChatLanguageModel _model, boolean _print) {
        if(_feelings == null || _content == null || _model == null ){
            return "Invalid Input";
        }
        // Structured Prompt
        StructuredPromptFeelings feelingsPrompt = new StructuredPromptFeelings(_feelings, _content);
        // Created Prompt
        Prompt prompt = StructuredPromptProcessor.toPrompt(feelingsPrompt);
        // Execute the Request
        String response = _model.generate(prompt.text());
        if(_print) {
            AiBeans.printResult(prompt.text(), response);
        }
        return response;
    }
}
