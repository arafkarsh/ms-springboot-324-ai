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
package io.fusion.air.microservice.ai.utils;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
// Custom
import io.fusion.air.microservice.ai.core.assistants.HAL9000Assistant;
// Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

/**
 * Ai Beans
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Configuration
public class AiBeans {

    /**
     * Get the default Language Model (OpenAPI ChatGPT 4o)
     * @return
     */
    public static ChatLanguageModel getDefaultLanguageModel() {
        return new AiBeans().createChatLanguageModelOpenAi();
    }

    /**
     * Get the Open AI Chat Language Model (ChatGPT 4o)
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelOpenAi() {
        return getChatLanguageModelOpenAi(AiConstants.GPT_4o);
    }

    /**
     * Get the OpenAI Language Model
     * 1. GPT 3.5 Turbo
     * 2. GPT 4
     * 3. GPT 4o
     *
     * @param _model
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelOpenAi(String _model) {
        return new AiBeans().createChatLanguageModelOpenAi(_model);
    }

    /**
     * Get the Ollama Chat Language Model (llama3)
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelLlama() {
        return getChatLanguageModelLlama(AiConstants.OLLAMA_LLAMA3);
    }

    /**
     * Get the Ollama Chat Language Model
     * 1. Llama 3 (llama3)
     * 2. Misrtal (mistral)
     * 3. Phi-3 (phi-3)
     * 4. Gemma (gemma)
     *
     * @param _model
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelLlama(String _model) {
        return new AiBeans().createChatLanguageModelLlama(_model);
    }

    /**
     * Get the Anthropic Chat Language Models
     * 1. Claude 3 Haiku
     *
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelAnthropic() {
        return getChatLanguageModelAnthropic(AiConstants.ANTHROPIC_CLAUDE_3_HAIKU);
    }

    /**
     * Get the Anthropic Chat Language Models
     * 1. Claude 3 Haiku
     *
     * @param _model
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelAnthropic(String _model) {
        return new AiBeans().createChatLanguageModelAnthropic(_model);
    }

    /**
     * Get the Google (Vertex AI) Chat Language Model
     * 1. Gemini Pro
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelGoogle() {
        return getChatLanguageModelGoogle(AiConstants.GOOGLE_GEMINI_PRO);
    }

    /**
     * Get the Google (Vertex AI) Chat Language Model
     * 1. Gemini Pro
     * 2. Gemini Flash
     * 3. Gemini Nano
     *
     * @param _model
     * @return
     */
    public static ChatLanguageModel getChatLanguageModelGoogle(String _model) {
        return new AiBeans().createChatLanguageModelGoogle(_model);
    }

    // ------------------------------------------------------------------------------------------------

    /**
     * Returns Chat Language Model based on ChatGPT 3.5, 4.0, 4o (Omni)
     * @return
     */
    @Bean(name = "ChatLanguageModelGPT")
    public ChatLanguageModel createChatLanguageModelOpenAi() {
        return createChatLanguageModelOpenAi(AiConstants.getAlgo(), false, false);
    }

    /**
     * Returns Chat Language Model based on ChatGPT 3.5, 4.0, 4o (Omni)
     * @param _model
     * @return
     */
    public ChatLanguageModel createChatLanguageModelOpenAi(String _model) {
        return createChatLanguageModelOpenAi(_model, false, false);
    }

    /**
     * Returns Chat Language Model based on ChatGPT 3.5, 4.0, 4o (Omni)
     * @param _req
     * @param _res
     * @return
     */
    public ChatLanguageModel createChatLanguageModelOpenAi(boolean _req, boolean _res) {
        return createChatLanguageModelOpenAi(AiConstants.getAlgo(), _req, _res);
    }

    /**
     * Returns Chat Language Model based on ChatGPT 3.5, 4.0, 4o (Omni)
     * @param _model
     * @return
     */
    public ChatLanguageModel createChatLanguageModelOpenAi(String _model, boolean _req, boolean _res) {
       return OpenAiChatModel.builder()
                .apiKey(AiConstants.OPENAI_API_KEY)
                // Higher the Temperature, Higher the Randomness.
                // For Accurate deterministic results keep the temperature low
                .temperature(0.0)
                .timeout(ofSeconds(60))
                 // AI Models are defined in AiConstants -  GPT_4_TURBO, GPT_3_5_TURBO
                .modelName(_model)
                .logRequests(_req)
                .logResponses(_res)
                .build();
    }

    /**
     * Returns Chat Language Model based on Llama 3
     * 1. Llama 3
     * @return
     */
    @Bean(name = "ChatLanguageModelOllama")
    public ChatLanguageModel createChatLanguageModelLlama() {
        return createChatLanguageModelLlama( AiConstants.OLLAMA_LLAMA3);
    }

    /**
     * Returns Chat Language Model based on
     * 1. Llama 3 (llama3)
     * 2. Misrtal (mistral)
     * 3. Phi-3 (phi-3)
     * 4. Gemma (gemma)
     *
     * @param _model
     * @return
     */
    public ChatLanguageModel createChatLanguageModelLlama(String _model) {
            return OllamaChatModel.builder()
                    .baseUrl("http://localhost:11434/api/generate/")
                    .modelName(_model)
                    // Higher the Temperature, Higher the Randomness.
                    // For Accurate deterministic results keep the temperature low
                    .temperature(0.0)
                    .timeout(ofSeconds(120))
                    .build();
    }

    /**
     * Returns Chat Language Model based on
     * 1. Claude 3 Haiku
     *
     * @return
     */
    @Bean(name = "ChatLanguageModelAnthropic")
    public ChatLanguageModel createChatLanguageModelAnthropic() {
        return createChatLanguageModelAnthropic( AiConstants.ANTHROPIC_CLAUDE_3_HAIKU);
    }

    /**
     * Returns Chat Language Model based on
     * 1. Claude 3 Haiku
     *
     * @param _model
     * @return
     */
    public ChatLanguageModel createChatLanguageModelAnthropic(String _model) {
        return  AnthropicChatModel.builder()
                // API key can be created here: https://console.anthropic.com/settings/keys
                .apiKey(AiConstants.ANTHROPIC_API_KEY)
                .modelName(_model) // claude-3-haiku-20240307
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    /**
     * Returns Chat Language Model based on Google
     * 1. Gemini Pro
     * 2. Gemini Flash
     * 3. Gemini Nano
     *
     * @return
     */
    @Bean(name = "ChatLanguageModelGoogle")
    public ChatLanguageModel createChatLanguageModelGoogle() {
        return createChatLanguageModelGoogle( AiConstants.GOOGLE_GEMINI_PRO);
    }

    /**
     * Returns Chat Language Model based on Google
     * 1. Gemini Pro
     * 2. Gemini Flash
     * 3. Gemini Nano
     *
     * @param _model
     * @return
     */
    public ChatLanguageModel createChatLanguageModelGoogle(String _model) {
        // Create Chat Language Model - Google Gemini Pro
        return  VertexAiGeminiChatModel.builder()
                .project(AiConstants.GOOGLE_VERTEX_PROJECT)
                .location(AiConstants.GOOGLE_VERTEX_LOCATION)
                .modelName(_model)
                .maxRetries(1)
                .temperature(0.2F)
                .build();
    }

    /**
     * Returns the Image Model
     * @return
     */
    @Bean
    public ImageModel createImageModel() {
        return createImageModel(AiConstants.DALL_E_3);
    }

    /**
     * Returns the Image Model
     * @param _model
     * @return
     */
    public ImageModel createImageModel(String _model) {
        return OpenAiImageModel.builder()
                .apiKey(AiConstants.OPENAI_API_KEY)
                .timeout(ofSeconds(60))
                // AI Models are defined in AiConstants -  DALL_E_3, DALL_E_2
                .modelName(_model)
                .logRequests(false)
                .logResponses(false)
                .build();
    }

    /**
     * Returns the Ai Assistant with GPT 3.5 Turbo
     * @return
     */
    @Bean
    public HAL9000Assistant createHAL9000() {
        return createHAL9000(AiConstants.getAlgo());
    }

    /**
     * Creates Ai Assistant
     * @param _model
     * @return
     */
    public HAL9000Assistant createHAL9000(String _model) {
        ChatLanguageModel chatLanguageModel = createChatLanguageModelOpenAi(_model);
        return AiServices.builder(HAL9000Assistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                // .tools(tool)
                .build();
    }

    /**
     * Create Ai Assistant
     * @param _chatLanguageModel
     * @return
     */
    public HAL9000Assistant createHAL9000(ChatLanguageModel _chatLanguageModel) {
        return AiServices.builder(HAL9000Assistant.class)
                .chatLanguageModel(_chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                // .tools(tool)
                .build();
    }

    /**
     * This chat memory will be used by an {@link HAL9000Assistant}
     */
    @Bean(name = "SimpleChatMemory")
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(20);
    }

    // ==============================================================================================
    // Utilities ----------------
    // ==============================================================================================

    /**
     * Print Model Details
     * @param _llm
     * @param _model
     */
    public static void printModelDetails(String _llm, String _model) {
        System.out.println("--[Model]----------------------------------------------------------");
        System.out.println(">>> "+_llm+" : "+_model);
    }

    /**
     * Print Request & Response
     *
     * @param _request
     * @param _response
     */
    public static void printResult(String _request, String _response) {
        System.out.println("--[Human]----------------------------------------------------------");
        System.out.println(_request);
        System.out.println("--[HAL9000]-------------------------------------------------------");
        System.out.println(_response);
        System.out.println("-------------------------------------------------------------------");
    }

    /**
     * Sleep
     */
    public static void sleep() {
        sleep(45);
    }

    /**
     * Sleep
     * @param seconds
     */
    public static void sleep(long seconds) {
        try {
            System.out.println("Sleeping for "+seconds+" Seconds to avoid per minute rate limit issues with Claude LLM...");
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {}
    }
}
