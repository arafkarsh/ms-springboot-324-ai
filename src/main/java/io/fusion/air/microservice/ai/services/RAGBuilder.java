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
package io.fusion.air.microservice.ai.services;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.bge.small.en.v15.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.rag.query.transformer.QueryTransformer;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import io.fusion.air.microservice.ai.examples.assistants.Assistant;
import io.fusion.air.microservice.ai.examples.assistants.CarRentalAssistant;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.utils.Utils;

import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;
import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocuments;

/**
 * Retrieval-Augmented Generation (RAG) Builder
 * Retrieval-Augmented Generation (RAG) is a natural language processing (NLP) technique that
 * combines generative-based AI models with retrieval-based techniques to improve the output of
 * large language models (LLMs). RAG AI can provide accurate results by using pre-existing
 * knowledge to create unique, context-aware answers, instructions, or explanations in human-like
 * language.
 *
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class RAGBuilder {

    /**
     * Read and Analyze the Data
     * @param _fileName
     * @return
     */
    public static ConversationalRetrievalChain createConversationalRetrievalChain(String _fileName) {
        ChatLanguageModel chatLanguageModel = new AiBeans().createChatLanguageModel();

        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(5000, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        Document document = loadDocument(Utils.toPath("static/data/p/"+_fileName), new TextDocumentParser());
        ingestor.ingest(document);

        return ConversationalRetrievalChain.builder()
                .chatLanguageModel(chatLanguageModel)
                .retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20)) // you can override default chat memory
                // .promptTemplate() // you can override default prompt template
                .build();
    }

    /**
     * Multi Document
     * @return
     */
    public static ConversationalRetrievalChain createMovieDatabaseChain() {
        ChatLanguageModel chatLanguageModel = new AiBeans().createChatLanguageModel();
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(5000, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        Document document1 = loadDocument(Utils.toPath("static/data/p/bramayugam.txt"), new TextDocumentParser());
        ingestor.ingest(document1);
        Document document2 = loadDocument(Utils.toPath("static/data/p/vaaliban.txt"), new TextDocumentParser());
        ingestor.ingest(document2);

        return ConversationalRetrievalChain.builder()
                .chatLanguageModel(chatLanguageModel)
                .retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
                // .promptTemplate(TemplateManager.createMoviePrompt()) // you can override default prompt template
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20)) // you can override default chat memory
                .build();
    }

    /**
     * Create a Simple RAG for the Car Rental Service
     *
     * @return
     */
    public static Assistant createCarRentalAssistantSimple() {
        // Setup the Language Model
        ChatLanguageModel model = new AiBeans().createChatLanguageModel();
        // Documents for processing
        List<Document> documents = loadDocuments(Utils.toPath("static/data/e/"), Utils.getPathMatcher("*.txt"));
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
        return  AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .contentRetriever(retriever)
                .build();
    }

    /**
     * Create Car Rental Assistant using RAG to process Custom Data
     *
     * @return
     */
    public static CarRentalAssistant createCarRentalAssistantWithSegments() {
        ChatLanguageModel chatLanguageModel = new AiBeans().createChatLanguageModel();

        // Now, let's load a document that we want to use for RAG.
        // We will use the terms of use from an imaginary car rental company, "Miles of Smiles".
        // For this example, we'll import only a single document, but you can load as many as you need.
        // LangChain4j offers built-in support for loading documents from various sources:
        // File System, URL, Amazon S3, Azure Blob Storage, GitHub, Tencent COS.
        // Additionally, LangChain4j supports parsing multiple document types:
        // text, pdf, doc, xls, ppt.
        // However, you can also manually import your data from other sources.
        DocumentParser documentParser = new TextDocumentParser();
        Document document = loadDocument(Utils.toPath("static/data/e/ozazo-car-rental-services.txt"), documentParser);

        // Now, we need to split this document into smaller segments, also known as "chunks."
        // This approach allows us to send only relevant segments to the LLM in response to a user query,
        // rather than the entire document. For instance, if a user asks about cancellation policies,
        // we will identify and send only those segments related to cancellation.
        // A good starting point is to use a recursive document splitter that initially attempts
        // to split by paragraphs. If a paragraph is too large to fit into a single segment,
        // the splitter will recursively divide it by newlines, then by sentences, and finally by words,
        // if necessary, to ensure each piece of text fits into a single segment.
        DocumentSplitter splitter = DocumentSplitters.recursive(300, 0);
        List<TextSegment> segments = splitter.split(document);

        // Now, we need to embed (also known as "vectorize") these segments.
        // Embedding is needed for performing similarity searches.
        // For this example, we'll use a local in-process embedding model, but you can choose any supported model.
        // Langchain4j currently supports more than 10 popular embedding model providers.
        BgeSmallEnV15QuantizedEmbeddingModel embeddingModel = new BgeSmallEnV15QuantizedEmbeddingModel();
        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        // Next, we will store these embeddings in an embedding store (also known as a "vector database").
        // This store will be used to search for relevant segments during each interaction with the LLM.
        // For simplicity, this example uses an in-memory embedding store, but you can choose from any supported store.
        // Langchain4j currently supports more than 15 popular embedding stores.
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        embeddingStore.addAll(embeddings, segments);

        // We could also use EmbeddingStoreIngestor to hide manual steps above behind a simpler API.
        // See an example of using EmbeddingStoreIngestor in _01_Advanced_RAG_with_Query_Compression_Example.

        // The content retriever is responsible for retrieving relevant content based on a user query.
        // Currently, it is capable of retrieving text segments, but future enhancements will include support for
        // additional modalities like images, audio, and more.
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(2) // on each interaction we will retrieve the 2 most relevant segments
                .minScore(0.5) // we want to retrieve segments at least somewhat similar to user query
                .build();

        // Optionally, we can use a chat memory, enabling back-and-forth conversation with the LLM
        // and allowing it to remember previous interactions.
        // Currently, LangChain4j offers two chat memory implementations:
        // MessageWindowChatMemory and TokenWindowChatMemory.
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

        // The final step is to build our AI Service,
        // configuring it to use the components we've created above.
        return AiServices.builder(CarRentalAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever)
                .chatMemory(chatMemory)
                .build();
    }

    /**
     * Create Assistant with Query Transformer
     * @return
     */
    public static Assistant createAssistantWithQueryTransformer() {
        // Load the documents
        Document document = loadDocument(Utils.toPath("static/data/e/akiera-kiera_biography.txt"), new TextDocumentParser());
        EmbeddingModel embeddingModel = new BgeSmallEnV15QuantizedEmbeddingModel();
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(300, 0))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(document);
        // Create LLM
        ChatLanguageModel chatLanguageModel = new AiBeans().createChatLanguageModel();
        // We will create a CompressingQueryTransformer, which is responsible for compressing
        // the user's query and the preceding conversation into a single, stand-alone query.
        // This should significantly improve the quality of the retrieval process.
        QueryTransformer queryTransformer = new CompressingQueryTransformer(chatLanguageModel);
        // Content Retriever
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(2)
                .minScore(0.6)
                .build();

        // The RetrievalAugmentor serves as the entry point into the RAG flow in LangChain4j.
        // It can be configured to customize the RAG behavior according to your requirements.
        // In subsequent examples, we will explore more customizations.
        RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .queryTransformer(queryTransformer)
                .contentRetriever(contentRetriever)
                .build();

        return AiServices.builder(Assistant.class)
                .chatLanguageModel(chatLanguageModel)
                .retrievalAugmentor(retrievalAugmentor)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }
}
