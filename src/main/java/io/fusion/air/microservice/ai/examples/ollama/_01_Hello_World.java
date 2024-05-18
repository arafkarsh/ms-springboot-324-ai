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
package io.fusion.air.microservice.ai.examples.ollama;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.utils.AiBeans;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _01_Hello_World {

    public static void main(String[] args) {

        ChatLanguageModel model = new AiBeans().createChatLanguageModelLlama();
        String request = "Explain French Revolution";
        String response = model.generate(request);
        AiBeans.printResult(request, response);
    }
}