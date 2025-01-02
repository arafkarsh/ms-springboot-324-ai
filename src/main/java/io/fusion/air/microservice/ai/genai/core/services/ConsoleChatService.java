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

import io.fusion.air.microservice.ai.genai.core.assistants.Assistant;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ConsoleChatService implements Assistant {
    /**
     * Chat Conversation
     *
     * @param userMessage
     * @return
     */
    @Override
    public String chat(String userMessage) {
        if (userMessage.startsWith("IMAGE: ")) {
            String query = userMessage.replaceAll("IMAGE:", "");
            return ImageBuilder.downloadImage(ImageBuilder.createImage(query));
        } else if (userMessage.startsWith("CUSTOM: ")) {
            String query = userMessage.replaceAll("CUSTOM:", "");
            return CustomDataAnalyzer.processFile(query, false);
        } else if (userMessage.startsWith("[P1")) {
            return TemplateManager.structuredTemplate(userMessage, false);
        } else if (userMessage.startsWith("[P2")) {
            String[] dArray = userMessage.split(":");
            return CustomDataAnalyzer.processMultiFiles(dArray[1]);
        }
        return CustomDataAnalyzer.processUserQuery(userMessage);
    }

    /**
     * Chat Memory with a Memory ID
     *
     * @param memoryId
     * @param userMessage
     * @return
     */
    @Override
    public String chat(String memoryId, String userMessage) {
        return "";
    }
}
