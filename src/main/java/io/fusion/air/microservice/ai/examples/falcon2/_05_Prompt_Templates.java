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
package io.fusion.air.microservice.ai.examples.falcon2;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.services.TemplateManager;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;


/**
 * Prompt Examples
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _05_Prompt_Templates {

    public static void main(String[] args) {
        // Create Chat Language Model Google Falcon 2
        ChatLanguageModel model = AiBeans.getChatLanguageModelLlama(AiConstants.OLLAMA_FALCON_2);
        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_FALCON_2);

        System.out.println("Prompt Example 1 >>--------------------------------------------");
        TemplateManager.simplePrompt(model);
        System.out.println("Prompt Example 2 >>--------------------------------------------");
        TemplateManager.complexPrompt(model);
        System.out.println("Prompt Example 3 >>--------------------------------------------");
        TemplateManager.structuredPromptRecipe(model);
        System.out.println("Prompt Example 4 >>--------------------------------------------");
        TemplateManager.structuredTemplate(
                "[P1","oven dish, cucumber, potato, tomato, red meat, olives, olive oil",
                model
        );
    }

}
