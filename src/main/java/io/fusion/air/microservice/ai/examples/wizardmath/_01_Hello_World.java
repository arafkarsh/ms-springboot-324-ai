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
package io.fusion.air.microservice.ai.examples.wizardmath;

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;

/**
 * Microsoft - Wizard Math
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _01_Hello_World {

    /**
     * Simple Hello World
     * @param model
     */
    public static void helloWorld(ChatLanguageModel model) {
        String request = "Explain French Revolution";
        String response = model.generate(request);
        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_WIZARD_MATH_7B);
        AiBeans.printResult(request, response);
    }

    /**
     * Conversation Chain
     * @param model
     */
    public static void conversationChat(ChatLanguageModel model) {
        ConversationalChain chain = ConversationalChain.builder()
                .chatLanguageModel(model)
                .build();
        String request1 = "Hello, my name is karsh";
        String response1 = chain.execute(request1);
        AiBeans.printResult(request1, response1);

        String request2 = "What is my name?";
        String response2 = chain.execute(request2);
        AiBeans.printResult(request2, response2);
    }

    /**
     * Word Problem - Generic
     * @param model
     */
    public static void wordProblemGeneral(ChatLanguageModel model) {
        String request = """
                3 killers are in a room. Another killer entered the room and killed another 
                in the room. How many killers are left?
                """;
        String response = model.generate(request);
        AiBeans.printResult(request, response);
    }

    /**
     * Word Problem - Math
         Perimeter of the field: 2 × (200+150)=700 meters
         Dividing the length into 4 parts (3 divisions): 3 × 150 = 450 meters
         Dividing the width into 5 parts (4 divisions): 4 × 200 = 800 meters
         Total fencing required: 700 + 450 + 800 = 1950 meters
         Total cost of fencing: 1950 × 5= $9750
     * @param model
     */
    public static void wordProblemMath(ChatLanguageModel model) {
        String request = """
                    A farmer has a rectangular field that is 200 meters long and 150 meters wide. 
                    He plans to divide the field into smaller rectangular plots of land by fencing 
                    parallel to the sides of the field. If the cost of fencing is $5 per meter, and he 
                    wants to fence the entire perimeter and three equally spaced divisions along the 
                    length and four equally spaced divisions along the width, what will be the total 
                    cost of the fencing?
                """;
        String response = model.generate(request);
        AiBeans.printResult(request, response);
    }

    /**
     * Testing Algebra
     * @param model
     */
    public static void testAlgebra(ChatLanguageModel model) {
        String request = "Expand the following expression: 7(3y+2)";
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
        // History Test
        helloWorld(model);
        // Conversation Test
        conversationChat(model);
        // 7B Gives wrong result. Try out 70B (43 GB disk space required) - Answer = 3
        wordProblemGeneral(model);
        // Math Problem - Answer = Total Cost of Fencing = 1950 * $5 = $9750/-
        wordProblemMath(model);
        // Algebra - Answer = 21y + 14
        testAlgebra(model);
    }
}
