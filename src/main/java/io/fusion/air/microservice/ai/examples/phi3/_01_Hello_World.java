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
package io.fusion.air.microservice.ai.examples.phi3;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _01_Hello_World {

    public static void main(String[] args) {
        // Create Chat Language Model Mistral
        ChatLanguageModel model = AiBeans.getChatLanguageModelLlama(AiConstants.OLLAMA_PHI_3);
        String request = "Explain French Revolution";
        String response = model.generate(request);

        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_PHI_3);
        AiBeans.printResult(request, response);
    }
}
