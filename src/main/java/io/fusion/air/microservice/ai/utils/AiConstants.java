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

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Configuration
@PropertySource(
        name = "aiConfig",
        // Expects file in the directory the jar is executed
        value = "file:./application.properties")
// Expects the file in src/main/resources folder
// value = "classpath:application.properties")
// value = "classpath:application2.properties,file:./application.properties")
public class AiConstants {

    public static final String LLM_OPENAI         = "OpenAI";
    public static final String LLM_OLLAMA        = "Ollama";
    public static final String LLM_ANTHROPIC    = "Anthropic";

    // ChatGPT ----------------------------------------------------------------------
    // INPUT = $0.005 / 1K tokens	   OUTPUT = $0.015 / 1K tokens
    public static final String GPT_4o               = "gpt-4o-2024-05-13";
    // INPUT = $0.01 / 1K tokens	   OUTPUT = $0.03 / 1K tokens
    public static final String GPT_4_TURBO      = "gpt-4-turbo-2024-04-09";
    // INPUT = $0.03 / 1K tokens	    OUTPUT = $0.06 / 1K tokens
    public static final String GPT_4                = "gpt-4";
    // INPUT = $0.06 / 1K tokens	    OUTPUT = $0.12 / 1K tokens
    public static final String GPT_4_32K          = "gpt-4-32k";
    // INPUT = 	$0.0005 / 1K tokens   OUTPUT = $0.0015 / 1K tokens
    public static final String GPT_3_5_TURBO   = "gpt-3.5-turbo-0125";

    // Dall-e ---------------------------------------------------------------------------
    // $0.040 / image, Standard 1024×1024
    public static final String DALL_E_3            = "dall-e-3";
    // $0.020 / image, 1024×1024
    public static final String DALL_E_2            = "dall-e-2";

    // API Keys -----------------------------------------------------------------------
    public static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    public static final String COHERE_API_KEY = System.getenv("COHERE_API_KEY");
    public static final String ANTHROPIC_API_KEY = System.getenv("ANTHROPIC_API_KEY");

    // Ollama Config --------------------------------------------------------------------
    @Value("${langchain4j.llama.url:http://localhost:11434/api/generate}")
    private  String llamaBaseUrl;

    @Value("${langchain4j.llama.model:llama3}")
    private  String llamaModel;

    public static String LLAMA_URL;
    public static String OLLAMA_LLAMA3        = "llama3";
    public static String OLLAMA_MISTRAL       = "mistral";
    public static String OLLAMA_PHI_3           = "phi3";
    public static String OLLAMA_GEMMA         = "gemma";
    public static String OLLAMA_FALCON_2      = "falcon2";

    // Anthropic Config ----------------------------------------------------------------
    public static final String ANTHROPIC_CLAUDE_3_HAIKU = "claude-3-haiku-20240307";



    // Algo Config ----------------------------------------------------------------------
    @Value("${langchain4j.default.algo:gpt-4o-2024-05-13}")
    private  String defaultAlgo;
    private static String DEFAULT_ALGO;

    @PostConstruct
    public void init() {
        DEFAULT_ALGO  = this.defaultAlgo;
        LLAMA_URL       = this.llamaBaseUrl;
        OLLAMA_LLAMA3 = this.llamaModel;
    }

    /**
     * Get the default Algo
     * @return
     */
    public static String getAlgo() {
        // System.out.println("<><><>---> ALGO = ["+DEFAULT_ALGO+"]>--------------------");
        return (DEFAULT_ALGO == null) ? GPT_3_5_TURBO  : DEFAULT_ALGO;
    }
}
