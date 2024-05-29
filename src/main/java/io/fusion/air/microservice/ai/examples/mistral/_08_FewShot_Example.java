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
package io.fusion.air.microservice.ai.examples.mistral;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _08_FewShot_Example {

    private static List<ChatMessage> fewShotHistory = new ArrayList<>();

    /**
     * Build the Context - Few Shot Message Context with Custom Action
     */
    public static void buildContext() {
        // Adding positive feedback example to history
        fewShotHistory.add(UserMessage.from(
                "I love the new update! The interface is very user-friendly and the new features are amazing!"));
        fewShotHistory.add(AiMessage.from(
                "Action: forward input to positive feedback storage\nReply: Thank you very much for this great feedback! We have transmitted your message to our product development team who will surely be very happy to hear this. We hope you continue enjoying using our product."));

        // Adding negative feedback example to history
        fewShotHistory.add(UserMessage
                .from("I am facing frequent crashes after the new update on my Android device."));
        fewShotHistory.add(AiMessage.from(
                "Action: open new ticket - crash after update Android\nReply: We are so sorry to hear about the issues you are facing. We have reported the problem to our development team and will make sure this issue is addressed as fast as possible. We will send you an email when the fix is done, and we are always at your service for any further assistance you may need."));

        // Adding another positive feedback example to history
        fewShotHistory.add(UserMessage
                .from("Your app has made my daily tasks so much easier! Kudos to the team!"));
        fewShotHistory.add(AiMessage.from(
                "Action: forward input to positive feedback storage\nReply: Thank you so much for your kind words! We are thrilled to hear that our app is making your daily tasks easier. Your feedback has been shared with our team. We hope you continue to enjoy using our app!"));

        // Adding another negative feedback example to history
        fewShotHistory.add(UserMessage
                .from("The new feature is not working as expected. It’s causing data loss."));
        fewShotHistory.add(AiMessage.from(
                "Action: open new ticket - data loss by new feature\nReply:We apologize for the inconvenience caused. Your feedback is crucial to us, and we have reported this issue to our technical team. They are working on it on priority. We will keep you updated on the progress and notify you once the issue is resolved. Thank you for your patience and support."));
    }

    /**
     * Send the Message
     * @param _request
     */
    public static String sendChatMessage(ChatLanguageModel model, String _request) {
        // Adding user message
        ChatMessage request = UserMessage.from(_request);
        fewShotHistory.add(request);
        // Response from Ai
        Response<AiMessage> response = model.generate(fewShotHistory);
        // Print Result
        AiBeans.printResult(request.text(), response.content().text());
        return response.content().text();
    }

    public static void main(String[] args) {
        // Create Chat Language Model Mistral
        ChatLanguageModel model = AiBeans.getChatLanguageModelLlama(AiConstants.OLLAMA_MISTRAL);
        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_MISTRAL);
      // Build Chat Context
        buildContext();
        // Message 1
        sendChatMessage(model, "How can your app be so slow? Please do something about it!");
        // Message 2
        sendChatMessage(model, "The app is fantastic!");
        // Message 3
        sendChatMessage(model, "Simplified my daily tasks! Good work team.");
        // Message 4
        sendChatMessage(model, "App Crashes twice or thrice in a week. Stability is not that good.");
    }
}
