package io.fusion.air.microservice.ai.examples.demo;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import io.fusion.air.microservice.ai.examples.models.Sentiment;
import io.fusion.air.microservice.ai.examples.utils.SentimentAnalyzer;
import io.fusion.air.microservice.ai.utils.AiBeans;

/**
 * Sentiment Analyzer Example
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _10_SentimentAnalyzerExample {

    public static void main(String[] args) {

        ChatLanguageModel model = new AiBeans().createChatLanguageModel();
        SentimentAnalyzer sentimentAnalyzer = AiServices.create(SentimentAnalyzer.class, model);
        String request = """
                    It was an interesting movie. The songs were not that great though. 
                    However, the background score and the song choreography made for 
                    the song. Overall the experience was not that bad.               
                 """;
        Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf(request);
        AiBeans.printResult(request, sentiment.name());

        request = """
                    The movie storyline was good. However, the direction, acting, and 
                    cinematography pushed the audience into a state of forced sleep.
                """;
        boolean positive = sentimentAnalyzer.isPositive(request);
        AiBeans.printResult(request, "is Positive Sentiment? = "+positive);

        request = """
                    In essence, “Bramayugam” delves into the intricate dynamics between 
                    an autocratic ruler, oppressed citizens, and those yearning to rebel, 
                    portraying the timeless struggle between the privileged and the 
                    disenfranchised across various epochs and landscapes. Through a finely 
                    crafted screenplay, stunning artistry, captivating cinematography, 
                    evocative background score, and stellar performances by the central 
                    characters, the film transcends mere entertainment, offering an immersive 
                    cinematic journey that resonates deeply with its audience.
                """;
        sentiment = sentimentAnalyzer.analyzeSentimentOf(request);
        AiBeans.printResult(request, sentiment.name());

        positive = sentimentAnalyzer.isPositive(request);
        AiBeans.printResult(request, "is Positive Sentiment? = "+positive);
    }
}
