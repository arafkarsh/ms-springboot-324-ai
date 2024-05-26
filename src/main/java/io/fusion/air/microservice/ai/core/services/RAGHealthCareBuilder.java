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
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import io.fusion.air.microservice.ai.core.assistants.HealthCareAssistant;
import io.fusion.air.microservice.ai.core.assistants.PatientDataExtractorAssistant;
import io.fusion.air.microservice.ai.core.models.Patient;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;
import io.fusion.air.microservice.utils.Utils;

import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;
import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocuments;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 * RAG Health Care Diagnosis Service - RAG Builder
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class RAGHealthCareBuilder {

    /**
     * Return the Diagnosis of the Patient
     *
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
        EmbeddingStoreIngestor ingestor = createEmbeddingStoreIngestor(embeddingModel, embeddingStore);
        ingestor.ingest(documents);

        // Lastly, let's create a content retriever from an embedding store.
        ContentRetriever retriever = createContentRetriever(embeddingModel, embeddingStore);
        // The final step is to build our AI Service,
        // Return the Assistant
        return AiServices.builder(HealthCareAssistant.class)
                .chatLanguageModel(_model)
                // .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .contentRetriever(retriever)
                .build();
    }


    /**
     * Create EmbeddingStore<TextSegment>
     *
     * @param _documentName
     * @param _embeddingModel
     * @return
     */
    private static EmbeddingStore<TextSegment> createEmbeddingStore(String _documentName, EmbeddingModel _embeddingModel) {
        DocumentParser documentParser = new TextDocumentParser();
        Document document = loadDocument(Utils.toPath("static/data/health/"+ _documentName), documentParser);

        DocumentSplitter splitter = DocumentSplitters.recursive(300, 0);
        List<TextSegment> segments = splitter.split(document);

        List<Embedding> embeddings = _embeddingModel.embedAll(segments).content();
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        embeddingStore.addAll(embeddings, segments);
        return embeddingStore;
    }

    /**
     * Create EmbeddingStoreIngestor
     * @param _embeddingModel
     * @param _embeddingStore
     * @return
     */
    private static EmbeddingStoreIngestor createEmbeddingStoreIngestor(
            EmbeddingModel _embeddingModel, EmbeddingStore<TextSegment> _embeddingStore) {
        return EmbeddingStoreIngestor.builder()
                .embeddingModel(_embeddingModel)
                .embeddingStore(_embeddingStore)
                .build();
    }

    /**
     * Create Content Retriever
     *
     * @param _embeddingStore
     * @param _embeddingModel
     * @return
     */
    private static ContentRetriever createContentRetriever(
            EmbeddingModel _embeddingModel, EmbeddingStore<TextSegment> _embeddingStore) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingModel(_embeddingModel)
                .embeddingStore(_embeddingStore)
                // .maxResults(2)
                // .minScore(0.3)
                // .dynamicFilter(query -> metadataKey("Patient-Name").isEqualTo(query.metadata().chatMemoryId().toString()))
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
}
