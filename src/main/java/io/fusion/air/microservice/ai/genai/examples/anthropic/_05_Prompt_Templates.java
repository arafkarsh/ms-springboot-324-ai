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
package io.fusion.air.microservice.ai.genai.examples.anthropic;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.genai.core.services.TemplateManager;
import io.fusion.air.microservice.ai.genai.utils.AiBeans;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;
import io.fusion.air.microservice.utils.Std;


/**
 * Prompt Examples
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _05_Prompt_Templates {

    public static void main(String[] args) {
        // Create Chat Language Model - Anthropic Claude 3 Haiku
        ChatLanguageModel model = AiBeans.getChatLanguageModelAnthropic(AiConstants.ANTHROPIC_CLAUDE_3_HAIKU);
        AiBeans.printModelDetails(AiConstants.LLM_ANTHROPIC, AiConstants.ANTHROPIC_CLAUDE_3_HAIKU);

        Std.println("Prompt Example 1 >>--------------------------------------------");
        TemplateManager.simplePrompt(model);
        AiBeans.sleep();

        Std.println("Prompt Example 2 >>--------------------------------------------");
        TemplateManager.complexPrompt(model);
        AiBeans.sleep();

        Std.println("Prompt Example 3 >>--------------------------------------------");
        TemplateManager.structuredPromptRecipe(model);
        AiBeans.sleep();

        Std.println("Prompt Example 4 >>--------------------------------------------");
        TemplateManager.structuredTemplate(
                "[P1","oven dish, cucumber, potato, tomato, red meat, olives, olive oil",
                model
        );
    }

}
