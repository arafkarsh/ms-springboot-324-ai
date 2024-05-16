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
package io.fusion.air.microservice.ai.examples.demo;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import io.fusion.air.microservice.ai.examples.assistants.CarRentalAssistant;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.ConsoleRunner;
import io.fusion.air.microservice.utils.Utils;

import java.util.List;
import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocuments;

/**
 * RAG Example
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _13_RAG_Simple_Example {

    public static ChatLanguageModel model = new AiBeans().createChatLanguageModel();

    private static ContentRetriever createContentRetriever(List<Document> documents) {
        // Here, we create and empty in-memory store for our documents and their embeddings.
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        // Here, we are ingesting our documents into the store.
        // Under the hood, a lot of "magic" is happening, but we can ignore it for now.
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        // Lastly, let's create a content retriever from an embedding store.
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }

    /**
     *  RAG Simple Example Testing
     *
     * @param args
     */
    public static void main(String[] args) {
        // Loading Docs to be used with RAG
        List<Document> documents = loadDocuments(Utils.toPath("static/data/e/"), Utils.getPathMatcher("*.txt"));
        // Setting up the Gen AI Context with Open AI LLM, and RAG
        CarRentalAssistant assistant = AiServices.builder(CarRentalAssistant.class)
                .chatLanguageModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .contentRetriever(createContentRetriever(documents))
                .build();
        // Start the Conversation
        // - Hello
        // - I am Sam. Can I cancel my reservation?
        // - Can you elaborate the usage policy?
        // - I had an accident, should I pay extra?
        // - Do you have a refund policy?
        // - No, thank you.
        ConsoleRunner.startConversationWith(assistant);
    }
}
