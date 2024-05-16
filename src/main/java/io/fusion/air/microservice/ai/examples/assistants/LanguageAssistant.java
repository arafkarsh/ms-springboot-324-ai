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
package io.fusion.air.microservice.ai.examples.assistants;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.util.List;

/**
 * Text Utils
 * 1. Translator
 * 2. Summarizer
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public interface TextUtils {

    /**
     * Translate the message into the target language
     *
     * @param text
     * @param language
     * @return
     */
    @SystemMessage("You are a professional translator. Translate into {{language}}")
    @UserMessage("Translate the following text: {{text}}")
    public String translate(@V("text") String text, @V("language") String language);

    /**
     * Summarize the message into specified set of bullet points.
     *
     * @param text
     * @param n
     * @return
     */
    @SystemMessage("Summarize every message from the user in {{n}} bullet points. Provide only bullet points.")
    public List<String> summarize(@UserMessage String text, @V("n") int n);
}
