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

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import io.fusion.air.microservice.ai.genai.utils.AiBeans;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom Data Analyzer
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class CustomDataAnalyzer {

    private CustomDataAnalyzer() {
    }

    /**
     * Process User Query
     *
     * @param query
     * @return
     */
    public static String processUserQuery(String query) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getOpenAIDefaultModel(), false, false);
        return processUserQuery( query,  model);
    }

    /**
     * Process User Query
     *
     * @param query
     * @param model
     * @return
     */
    public static String processUserQuery(String query, ChatLanguageModel model) {
        return model.generate(query);
    }

    /**
     * Process the Data with Custom Data
     * @param request
     */
    public static String processFile(String request) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModelOpenAi();
        return processFile(request, "bramayugam.txt", model, true);
    }

    /**
     *
     * @param request
     * @param printResult
     * @return
     */
    public static String processFile(String request, boolean printResult) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModelOpenAi();
        return processFile(request, "bramayugam.txt", model, printResult);
    }

    /**
     * Process the Data with Custom Data
     * @param request
     * @param fileName
     * @return
     */
    public static String processFile(String request, String fileName) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModelOpenAi();
        return processFile( request,  fileName,  model);
    }

    /**
     * Process the Data with Custom Data
     * @param request
     * @param fileName
     * @param model
     * @return
     */
    public static String processFile(String request, String fileName, ChatLanguageModel model) {
        return processFile( request,  fileName,  model,  true);
    }

    /**
     * Process the Data with Custom Data
     * @param request
     * @param fileName
     * @param model
     * @param printResult
     * @return
     */
    public static String processFile(String request, String fileName, ChatLanguageModel model, boolean printResult) {
        ConversationalRetrievalChain chain = RAGBuilder.createConversationalRetrievalChain(fileName, model);
        String response = chain.execute(request);
        if(printResult) AiBeans.printResult(request, response);
        return response;
    }

    /**
     * Process the Data from Multiple Files
     * @param request
     * @return
     */
    public static String processMultiFiles(String request) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModelOpenAi();
        return processMultiFiles( request,  model, false);
    }

    /**
     * Process the Data from Multiple Files
     * @param request
     * @param chatLanguageModel
     * @return
     */
    public static String processMultiFiles(String request, ChatLanguageModel chatLanguageModel ) {
        return processMultiFiles( request,  chatLanguageModel,  true);
    }
    /**
     * Process the Data from Multiple Files
     * @param request
     * @param chatLanguageModel
     * @return
     */
    public static String processMultiFiles(String request, ChatLanguageModel chatLanguageModel, boolean printResult) {
        ConversationalRetrievalChain chain = RAGBuilder.createMovieDatabaseChain(chatLanguageModel);
        PromptTemplate promptTemplate = TemplateManager.createMoviePrompt();
        Map<String, Object> params = new HashMap<>();
        params.put("movieName", request);
        Prompt prompt = promptTemplate.apply(params);
        String response = chain.execute(prompt.text());

        if(printResult) AiBeans.printResult(request, response);
        return response;
    }
}
