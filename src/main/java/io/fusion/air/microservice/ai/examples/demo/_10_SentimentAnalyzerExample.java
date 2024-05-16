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

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import io.fusion.air.microservice.ai.examples.models.Sentiment;
import io.fusion.air.microservice.ai.examples.assistants.SentimentAssistant;
import io.fusion.air.microservice.ai.services.SentimentAnalyzer;
import io.fusion.air.microservice.ai.services.TemplateManager;
import io.fusion.air.microservice.ai.utils.AiBeans;

/**
 * Sentiment Analyzer Example
 *
 * Figures out if the Sentiment is Positive, Negative or Neutral for a given text.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _10_SentimentAnalyzerExample {

    public static void main(String[] args) {

        ChatLanguageModel model = new AiBeans().createChatLanguageModel();
        SentimentAssistant sentimentAssistant = AiServices.create(SentimentAssistant.class, model);
        String request = """
                The movie was quite engaging, although the songs were somewhat lackluster. Nevertheless, the background score and choreography significantly 
                enhanced the musical sequences, making up for the shortcomings. Overall, the experience was not that bad.""";

        SentimentAnalyzer.analyzeSentiment(request, true);

        request = """
                    The movie had a promising storyline, but the direction, acting, and cinematography were so over the top that they lulled the audience into 
                    a state of forced sleep. """;
        SentimentAnalyzer.analyzeSentiment(request, true);

        request = """
                    In essence, “Bramayugam” delves into the intricate dynamics between an autocratic ruler, oppressed citizens, and those yearning to rebel, 
                    portraying the timeless struggle between the privileged and the disenfranchised  across various epochs and landscapes. Through a finely 
                    crafted screenplay, stunning artistry, captivating cinematography, evocative background score, and stellar performances by the central  
                    characters, the film transcends mere entertainment, offering an immersive cinematic journey that resonates deeply with its audience. """;
        SentimentAnalyzer.analyzeSentiment(request, true);
    }
}
