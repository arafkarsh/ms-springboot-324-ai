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
public class _03_Geometry {

    /**
     * Math DownloadAllData - Geometry
     * @param model
     */
    public static void testGeometry(ChatLanguageModel model) {
        String request = """
                A right square pyramid has a base with edges each measuring 3 cm and a height 
                twice the perimeter of its base. What is the volume of the pyramid?
                """;
        String response = model.generate(request);
        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_WIZARD_MATH_7B);
        AiBeans.printResult(request, response);
    }


    /**
     * Math DownloadAllData - Number Theory
     * @param model
     */
    public static void testNumberTheory(ChatLanguageModel model) {
        String request = """
                A textbook has 1,000 pages. How many of the pages have page numbers whose 
                digits add up to exactly 4?
                """;
        String response = model.generate(request);
        AiBeans.printResult(request, response);
    }

    /**
     * Math DownloadAllData - Counting & Probability
     * @param model
     */
    public static void countingAndProbability(ChatLanguageModel model) {
        String request = """
                How many 4-digit numbers have the last digit equal to the sum 
                of the first two digits?
                """;
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
        // DownloadAllData Geometry
        testGeometry(model);
        // DownloadAllData Number Theory
        testNumberTheory(model);
        // Counting and Probability
        countingAndProbability(model);
    }
}
