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
package io.fusion.air.microservice.ai.examples.palm2;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.core.assistants.HealthCareAssistant;
import io.fusion.air.microservice.ai.core.models.Patient;
import io.fusion.air.microservice.ai.core.services.RAGHealthCareBuilder;
import io.fusion.air.microservice.ai.core.services.RAGHealthCareService;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;
import io.fusion.air.microservice.ai.utils.ConsoleRunner;

/**
 * RAG Health Care Diagnosis Service Example
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _59_RAG_HealthCare_Example {

    /**
     * This example demonstrates how to implement Health Care Diagnosis Service Example
     */
    public static void main(String[] args) {
        // Test Patient Data Extractor
        // testPatientDataExtractor();

        // Create Chat Language Model Google Vertex AI - PaLM 2
        ChatLanguageModel model = AiBeans.getChatLanguageModelGoogle(AiConstants.GOOGLE_PaLM_CHAT_BISON);
        AiBeans.printModelDetails(AiConstants.LLM_VERTEX, AiConstants.GOOGLE_PaLM_CHAT_BISON);
        // Create the Assistant
        // Setting up the Gen AI Context with Open AI LLM, and RAG
        // HealthCareAssistant assistant = RAGHealthCareBuilder.createHealthCareAssistant(model);
        HealthCareAssistant assistant = new RAGHealthCareService(AiConstants.LLM_OPENAI, AiConstants.GPT_4o);
        // Start the Conversation with iCare Health Care Diagnosis Service ChatBot
        // - Hi
        // - I need the diagnosis history of Akiera Kiera for the past 3 years
        // - I need the diagnosis history of Patient Id 300100202
        // - I need the diagnosis history of Jane Susan Wood for the past 4 years
        // - I need the diagnosis history of Patient Id 400100201
        // - No, thank you.
        ConsoleRunner.startConversationWithPrompts(assistant, "iCare - Health Care Hospitals");
    }

    public static void testPatientDataExtractor() {
        ChatLanguageModel model = AiBeans.getChatLanguageModelOpenAi(AiConstants.GPT_3_5_TURBO);
        AiBeans.printModelDetails(AiConstants.LLM_OPENAI, AiConstants.GPT_3_5_TURBO);
        // Create the Assistant
        // Setting up the Gen AI Context with Open AI LLM, and RAG
        Patient patient0 = RAGHealthCareBuilder.patientNameExtractor("Hi", model);
        Patient patient1 = RAGHealthCareBuilder.patientNameExtractor(
                "I need the diagnosis history of Akiera Kiera for the past 3 years", model);
        Patient patient2 = RAGHealthCareBuilder.patientNameExtractor(
                "I need the diagnosis history of  Jane Susan Wood for the past 4 years", model);

        long patientId1 = RAGHealthCareBuilder.patientIdExtractor(
                "I need the diagnosis history of Patient Id 300100202",  model);
        long patientId2 = RAGHealthCareBuilder.patientIdExtractor(
                "I need the diagnosis history of Patient 400100201",  model);

        Patient patient3 = RAGHealthCareBuilder.patientNameExtractor(
                "I need the diagnosis history for the past 4 years", model);

        long patientId3 = RAGHealthCareBuilder.patientIdExtractor(
                "I need the diagnosis history of Sam",  model);
    }
}
