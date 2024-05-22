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

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;

import java.util.HashMap;
import java.util.Map;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

/**
 * Custom Data Analyzer
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class CustomDataAnalyzer {

    /**
     * Process User Query
     *
     * @param _query
     * @return
     */
    public static String processUserQuery(String _query) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModelOpenAi(AiConstants.getAlgo(), false, false);
        return processUserQuery( _query,  model);
    }

    /**
     * Process User Query
     *
     * @param _query
     * @param _model
     * @return
     */
    public static String processUserQuery(String _query, ChatLanguageModel _model) {
        return _model.generate(_query);
    }

    /**
     * Process the Data with Custom Data
     * @param _request
     */
    public static String processFile(String _request) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModelOpenAi();
        return processFile(_request, "bramayugam.txt");
    }

    /**
     * Process the Data with Custom Data
     * @param _request
     * @param _fileName
     * @return
     */
    public static String processFile(String _request, String _fileName) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModelOpenAi();
        return processFile( _request,  _fileName,  model);
    }

    /**
     * Process the Data with Custom Data
     * @param _request
     * @param _fileName
     * @param _model
     * @return
     */
    public static String processFile(String _request, String _fileName, ChatLanguageModel _model) {
        ConversationalRetrievalChain chain = RAGBuilder.createConversationalRetrievalChain(_fileName, _model);
        String response = chain.execute(_request);
        AiBeans.printResult(_request, response);
        return response;
    }

    /**
     * Process the Data from Multiple Files
     * @param _request
     * @return
     */
    public static String processMultiFiles(String _request) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModelOpenAi();
        return processMultiFiles( _request,  model);
    }

    /**
     * Process the Data from Multiple Files
     * @param _request
     * @param _chatLanguageModel
     * @return
     */
    public static String processMultiFiles(String _request, ChatLanguageModel _chatLanguageModel) {
        ConversationalRetrievalChain chain = RAGBuilder.createMovieDatabaseChain(_chatLanguageModel);
        PromptTemplate promptTemplate = TemplateManager.createMoviePrompt();
        Map<String, Object> params = new HashMap<>();
        params.put("movieName", _request);
        Prompt prompt = promptTemplate.apply(params);
        String response = chain.execute(prompt.text());

        AiBeans.printResult(_request, response);
        return response;
    }
}
