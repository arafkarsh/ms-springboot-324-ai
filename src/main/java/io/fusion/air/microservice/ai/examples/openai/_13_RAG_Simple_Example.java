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
package io.fusion.air.microservice.ai.examples.openai;

import io.fusion.air.microservice.ai.examples.core.assistants.Assistant;
import io.fusion.air.microservice.ai.services.RAGBuilder;
import io.fusion.air.microservice.ai.utils.ConsoleRunner;

/**
 * RAG Example
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _13_RAG_Simple_Example {

    /**
     * This example demonstrates how to implement an "Easy RAG" (Retrieval-Augmented Generation) application.
     * By "easy" we mean that we won't dive into all the details about parsing, splitting, embedding, etc.
     * <p>
     */
    public static void main(String[] args) {
        // Setting up the Gen AI Context with Open AI LLM, and RAG
        Assistant assistant = RAGBuilder.createCarRentalAssistantSimple();
        // Start the Conversation with Ozazo Rental Service ChatBot
        // - Hello
        // - I am Sam. Can I cancel my reservation?
        // - Can you elaborate the usage policy?
        // - I had an accident, should I pay extra?
        // - Do you have a refund policy?
        // - No, thank you.
        ConsoleRunner.startConversationWith(assistant, "OZAZO Car Rental Service ChatBot");
    }
}