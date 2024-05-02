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
package io.fusion.air.microservice.ai.utils;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.model.chat.ChatLanguageModel;

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
}
