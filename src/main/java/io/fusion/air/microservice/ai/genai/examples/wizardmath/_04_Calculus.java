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
package io.fusion.air.microservice.ai.genai.examples.wizardmath;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.genai.utils.AiBeans;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;

/**
 * Microsoft - Wizard Math
 * Source: https://ollama.com/blog/wizardmath-examples
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _04_Calculus {

    /**
     * Math DownloadAllData - Pre Calculus
     * @param model
     */
    public static void testPreCalculus(ChatLanguageModel model) {
        String request = """
                Compute $\\begin{pmatrix} 4 \\\\ 5 \\\\ -1 \\end{pmatrix} \\times \\begin{pmatrix} 4 \\\\ 5 \\\\ -1 \\end{pmatrix}.$
                """;
        String response = model.generate(request);
        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_WIZARD_MATH_7B);
        AiBeans.printResult(request, response);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Create Chat Language Model - Microsoft Wizard Math
        ChatLanguageModel model = AiBeans.getChatLanguageModelLlama(AiConstants.OLLAMA_WIZARD_MATH_7B);
        // DownloadAllData Pre Calculus
        testPreCalculus(model);
    }
}
