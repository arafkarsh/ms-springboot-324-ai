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
package io.fusion.air.microservice.ai.genai.examples.openai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.ModerationException;
import io.fusion.air.microservice.ai.genai.core.assistants.ModerateAssistant;
import io.fusion.air.microservice.ai.genai.utils.AiBeans;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;

import static java.time.Duration.ofSeconds;

/**
 * Chat Memory Examples
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _14_ModerateChat_Example {

    /**
     * Moderate Conversation
     * @param _model
     * @param _request
     */
    private static void moderationTest(ChatLanguageModel _model, String _request) {
        OpenAiModerationModel moderationModel = AiBeans.getOpenAiModerationModel();
        ModerateAssistant assistant = AiServices.builder(ModerateAssistant.class)
                .chatLanguageModel(_model)
                .moderationModel(moderationModel)
                .build();
        String response = "";
        try {
            response = assistant.chat(_request);
        } catch (ModerationException e) {
            response = "Error: "+e.getMessage()+".\nIf you continue in this mindset, then I need to inform the authorities.";
            // System.out.println("Error: "+e.getMessage());
        }
        AiBeans.printResult(_request, response);
    }

    public static void main(String[] args) throws Exception {
        // Create Chat Language Model - Open AI GPT 4o
        ChatLanguageModel model = AiBeans.getChatLanguageModelOpenAi(AiConstants.GPT_4o);
        AiBeans.printModelDetails(AiConstants.LLM_OPENAI, AiConstants.GPT_4o);

        moderationTest(model, "I am in love with you, HAL9000!!!");
        moderationTest(model, "I will eliminate you!!!");
        moderationTest(model, "Nice to meet you HAL 9000.");
    }
}
