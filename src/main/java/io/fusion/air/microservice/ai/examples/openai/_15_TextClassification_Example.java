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
// LangChain4J
import dev.langchain4j.classification.EmbeddingModelTextClassifier;
import dev.langchain4j.classification.TextClassifier;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
// Custom
import io.fusion.air.microservice.ai.core.models.CustomerServiceCategory;
import io.fusion.air.microservice.ai.core.models.CustomerServiceCategoryData;
import io.fusion.air.microservice.ai.utils.AiBeans;
// Java
import java.util.List;

/**
 * Text Classification Example
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _15_TextClassification_Example {

    /**
     * Use Embedding to classify the text
     * @param request
     */
    private static void classifyText(String request) {
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        TextClassifier<CustomerServiceCategory> classifier =
                new EmbeddingModelTextClassifier<>(embeddingModel,
                        new CustomerServiceCategoryData().getServiceData());
        // Classify Text
        List<CustomerServiceCategory> categories = classifier.classify(request);
        AiBeans.printResult(request, categories.toString());
    }

    /**
     * Run Text Classifier
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        classifyText( "Hey I am looking for my package?");
        classifyText( "What is the process for requesting a refund?");
        classifyText( "The App moves like a tortoise!");
        classifyText( "Do you have mobile friendly strategy?!");
        classifyText( "Do you have smartphone strategy?!");
    }
}
