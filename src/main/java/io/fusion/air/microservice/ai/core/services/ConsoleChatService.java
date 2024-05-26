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

import io.fusion.air.microservice.ai.core.assistants.Assistant;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ConsoleChatService implements Assistant {
    /**
     * Chat Conversation
     *
     * @param userQuery
     * @return
     */
    @Override
    public String chat(String userQuery) {
        if (userQuery.startsWith("IMAGE: ")) {
            String query = userQuery.replaceAll("IMAGE:", "");
            return ImageBuilder.downloadImage(ImageBuilder.createImage(query));
        } else if (userQuery.startsWith("CUSTOM: ")) {
            String query = userQuery.replaceAll("CUSTOM:", "");
            return CustomDataAnalyzer.processFile(query, false);
        } else if (userQuery.startsWith("[P1")) {
            return TemplateManager.structuredTemplate(userQuery, false);
        } else if (userQuery.startsWith("[P2")) {
            String[] dArray = userQuery.split(":");
            return CustomDataAnalyzer.processMultiFiles(dArray[1]);
        }
        return CustomDataAnalyzer.processUserQuery(userQuery);
    }

    /**
     * Chat Memory with a Memory ID
     *
     * @param _memoryId
     * @param _userMessage
     * @return
     */
    @Override
    public String chat(String _memoryId, String _userMessage) {
        return "";
    }
}
