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
package io.fusion.air.microservice.ai.core.services;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import io.fusion.air.microservice.ai.core.assistants.HealthCareAssistant;
import io.fusion.air.microservice.ai.core.assistants.PatientDataExtractorAssistant;
import io.fusion.air.microservice.ai.core.models.Patient;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;
import io.fusion.air.microservice.utils.Utils;

import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocuments;

/**
 * RAG Health Care Diagnosis Service - RAG Builder
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class RAGHealthCareBuilder {

    /**
     * Return the Diagnosis of the Patient
     * @param _model
     * @return
     */
    public static HealthCareAssistant createHealthCareAssistant(ChatLanguageModel _model) {
        // Documents for processing
        List<Document> documents = loadDocuments(Utils.toPath("static/data/health/"), Utils.getPathMatcher("*.txt"));
        // Embedding Model
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        // Here, we create and empty in-memory store for our documents and their embeddings.
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        // Here, we are ingesting our documents into the store.
        // Under the hood, a lot of "magic" is happening, but we can ignore it for now.
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        ingestor.ingest(documents);
        // Lastly, let's create a content retriever from an embedding store.
        ContentRetriever retriever =  EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        // The final step is to build our AI Service,
        // Return the Assistant
        return  AiServices.builder(HealthCareAssistant.class)
                .chatLanguageModel(_model)
                // .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .contentRetriever(retriever)
                .build();
    }

    /**
     * Extract Patient Info from the message
     * @param _request
     * @param _model
     * @return
     */
    public static Patient patientNameExtractor(String _request, ChatLanguageModel _model) {
        PatientDataExtractorAssistant extractor = AiServices.create(PatientDataExtractorAssistant.class, _model);
        Patient patient = extractor.extractPatientNameFrom(_request);
        AiBeans.printResult(_request, patient.toString());
        return patient;
    }

    /**
     * Extract Patient Id from the message
     * @param _request
     * @param _model
     * @return
     */
    public static long patientIdExtractor(String _request, ChatLanguageModel _model) {
        PatientDataExtractorAssistant extractor = AiServices.create(PatientDataExtractorAssistant.class, _model);
        long patientId = -1;
        try { patientId = extractor.extractPatientId(_request); } catch (NumberFormatException e) {}
        AiBeans.printResult(_request, ""+patientId);
        return patientId;
    }

    /**
     *
     * @param _groupName
     * @param _modelName
     * @return
     */
    public static String queryDiagnosisData(String _request, String _groupName, String _modelName) {
        String response = "No Data!";
        ChatLanguageModel model = createLanguageModel( _groupName,  _modelName);
        HealthCareAssistant assistant = createHealthCareAssistant(model);
        return response;
    }

    /**
     * Return ChatLanguageModel
     * @param _groupName
     * @param _modelName
     * @return
     */
    private static ChatLanguageModel createLanguageModel(String _groupName, String _modelName) {
        ChatLanguageModel model = null;
        if(!isValidModel(_groupName ,_modelName)) {
            model = AiBeans.getChatLanguageModelOpenAi(AiConstants.GPT_3_5_TURBO);
        } else {
            if(_groupName.equalsIgnoreCase(AiConstants.LLM_OPENAI)) {
                model = AiBeans.getChatLanguageModelOpenAi(AiConstants.GPT_3_5_TURBO);
            } else {
                model = getChatLanguageModel( _groupName,  _modelName);
            }
        }
        return model;
    }

    /**
     * Returns ChatLanguageModel based on the LLM Group
     * @param _groupName
     * @param _modelName
     * @return
     */
    private static ChatLanguageModel getChatLanguageModel(String _groupName, String _modelName) {
        switch(_groupName) {
            case AiConstants.LLM_ANTHROPIC -> AiBeans.getChatLanguageModelAnthropic(_modelName);
            case AiConstants.LLM_OLLAMA -> AiBeans.getChatLanguageModelLlama(_modelName);
            case AiConstants.LLM_VERTEX -> AiBeans.getChatLanguageModelGoogle(_modelName);
        }
        return AiBeans.getChatLanguageModelOpenAi(_modelName);
    }

    /**
     * Checks if the Group and Model Name are valid
     * @param _groupName
     * @param _modelName
     * @return
     */
    private static boolean isValidModel(String _groupName, String _modelName) {
        if(_groupName == null) { return false; }
        if(_modelName == null) { return false; }
        return true;
    }
}
