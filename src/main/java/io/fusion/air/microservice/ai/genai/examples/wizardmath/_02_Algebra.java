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
public class _02_Algebra {

    /**
     * Math DownloadAllData - Algebra
     * @param model
     */
    public static void testAlgebra(ChatLanguageModel model) {
        String request = "If the quadratic x^2+6mx+m has exactly one real root, find the positive value of m.";
        String response = model.generate(request);
        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_WIZARD_MATH_7B);
        AiBeans.printResult(request, response);
    }


    /**
     * Math DownloadAllData - Pre Algebra
     * @param model
     */
    public static void preAlgebra(ChatLanguageModel model) {
        String request = "How many degrees are in the acute angle formed by the hands of a clock at 3:30?";
        String response = model.generate(request);
        AiBeans.printResult(request, response);
    }

    /**
     * Math DownloadAllData - Intermediate Algebra
     * @param model
     */
    public static void intermediateAlgebra(ChatLanguageModel model) {
        String request = "Find all the integer roots of 2x^4 + 4x^3 - 5x^2 + 2x - 3 = 0. Enter all the integer roots, separated by commas";
        String response = model.generate(request);
        AiBeans.printResult(request, response);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Create Chat Language Model - Microsoft Wizard Math
        ChatLanguageModel model = AiBeans.getChatLanguageModelLlama(AiConstants.OLLAMA_WIZARD_MATH_7B);
        // DownloadAllData Algebra
        testAlgebra(model);
        // Pre Algebra
        preAlgebra(model);
        // Intermediate Algebra
        intermediateAlgebra(model);
    }
}
