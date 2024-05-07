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

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;

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


    public static String processUserQuery(String query) {
        ChatLanguageModel model = new AiBeans()
                .createChatLanguageModel(AiConstants.GPT_3_5_TURBO, false, false);
        return model.generate(query);
    }

    /**
     * Process the Data with Custom Data
     * @param _request
     */
    public static void processFile(String _request) {
            processFile(_request, "bramayugam.txt");
    }

    /**
     * Process the Data with Custom Data
     * @param _request
     * @param _fileName
     */
    public static void processFile(String _request, String _fileName) {
        ConversationalRetrievalChain chain = new AiBeans()
                .createConversationalRetrievalChain(_fileName);
        String response = chain.execute(_request);
        System.out.println("--[Human]----------------------------------------------------------");
        System.out.println(_request);
        System.out.println("--[HAL9000]-------------------------------------------------------");
        System.out.println(response);
        System.out.println("-------------------------------------------------------------------");
    }

    /**
     * Process the Data from Multiple Files
     *
     * @param _request
     */
    public static void processMultiFiles(String _request) {
        ConversationalRetrievalChain chain = new AiBeans()
                .createMovieDatabaseChain();

        System.out.println("--[Human]----------------------------------------------------------");
        System.out.println(_request);
        System.out.println("--[HAL9000]-------------------------------------------------------");

        PromptTemplate promptTemplate = TemplateManager.createMoviePrompt();
        Map<String, Object> params = new HashMap<>();
        params.put("movieName", _request);
        Prompt prompt = promptTemplate.apply(params);
        String response = chain.execute(prompt.text());
        System.out.println(response);
        System.out.println("-------------------------------------------------------------------");
    }
}
