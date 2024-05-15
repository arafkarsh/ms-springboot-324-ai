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

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import static dev.langchain4j.data.message.UserMessage.userMessage;

import io.fusion.air.microservice.ai.utils.AiConstants;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _01_HelloWorld {

    public static void main(String[] args) {
        helloWorld();
        conversationChat();
        conversationChatWithMemory(AiConstants.GPT_3_5_TURBO);
    }

    public static void helloWorld() {
        // Create an instance of a model
        ChatLanguageModel model = OpenAiChatModel.withApiKey(AiConstants.OPENAI_API_KEY);
        // Start interacting
        String request = "Hello My Space...";
        System.out.println("Request: "+request);
        String response = model.generate(request);
        System.out.println("Response: "+response);
        System.out.println("--------------------------------------------------------------");
    }

    public static void conversationChat() {
        ConversationalChain chain = ConversationalChain.builder()
                .chatLanguageModel(OpenAiChatModel.withApiKey(AiConstants.OPENAI_API_KEY))
                // .chatMemory() // you can override default chat memory
                .build();
        String request1 = "Hello, my name is karsh";
        System.out.println("Request  1: "+request1);
        String response1 = chain.execute(request1);
        System.out.println("Response 1: "+response1);

        String request2 = "What is my name?";
        System.out.println("Request  2: "+request2);
        String response2 = chain.execute(request2);
        System.out.println("Response 2: "+response2);
        System.out.println("--------------------------------------------------------------");
    }

    public static void conversationChatWithMemory(String _model) {
        ChatLanguageModel model = OpenAiChatModel.withApiKey(AiConstants.OPENAI_API_KEY);
        ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(300,
                new OpenAiTokenizer(_model));

        // You have full control over the chat memory.
        // You can decide if you want to add a particular message to the memory
        // (e.g. you might not want to store few-shot examples to save on tokens).
        // You can process/modify the message before saving if required.

        String request1 = "Hello, my name is karsh";
        System.out.println("Request  1: "+request1);
        chatMemory.add(userMessage(request1));
        AiMessage answer1 = model.generate(chatMemory.messages()).content();
        chatMemory.add(answer1);
        System.out.println("Response 1: "+answer1.text());

        String request2 = "What is my name?";
        System.out.println("Request  2: "+request2);
        chatMemory.add(userMessage(request2));
        AiMessage answer2 = model.generate(chatMemory.messages()).content();
        chatMemory.add(answer2);
        System.out.println("Response 2: "+answer2.text());
        System.out.println("--------------------------------------------------------------");
    }
}
