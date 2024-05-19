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

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import io.fusion.air.microservice.ai.examples.core.assistants.SentimentAssistant;
import io.fusion.air.microservice.ai.examples.core.models.Sentiment;
import io.fusion.air.microservice.ai.utils.AiBeans;

/**
 * Sentiment Analyzer
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class SentimentAnalyzer {

    /**
     * Analyze the Sentiment of a text.
     * Rating based on Positive, Neutral and Negative
     * Analyze the feelings of the user based on the content.
     *
     * @param _request
     * @return
     */
    public static String analyzeSentiment(String _request) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModel();
        return analyzeSentiment(_request, model, false);
    }

    /**
     * Analyze the Sentiment of a text.
     * Rating based on Positive, Neutral and Negative
     * Analyze the feelings of the user based on the content.
     *
     * @param _request
     * @param _print
     * @return
     */
    public static String analyzeSentiment(String _request, boolean _print) {
        ChatLanguageModel model = new AiBeans().createChatLanguageModel();
        return analyzeSentiment( _request, model, _print);
    }

    /**
     * Analyze the Sentiment of a text.
     * Rating based on Positive, Neutral and Negative
     * Analyze the feelings of the user based on the content.
     *
     * @param _request
     * @param model
     * @param _print
     * @return
     */
    public static String analyzeSentiment(String _request, ChatLanguageModel model,  boolean _print) {
        if(_request == null || model == null) {
            return "Invalid Inputs";
        }
        SentimentAssistant sentimentAssistant = AiServices.create(SentimentAssistant.class, model);
        Sentiment sentiment = sentimentAssistant.analyzeSentimentOf(_request);
        boolean positive = sentimentAssistant.isPositive(_request);
        String feelings = TemplateManager.structuredPromptFeelings(sentiment.name(), _request);
        String response = "Rating of the content = "+sentiment.name() + "\n"
                        +"Is Sentiment Positive?     = "+((positive) ? "Yes" : "No")
                        +"\n"+feelings;
        if(_print) {
            AiBeans.printResult(_request,response);
        }
        return response;
    }
}
