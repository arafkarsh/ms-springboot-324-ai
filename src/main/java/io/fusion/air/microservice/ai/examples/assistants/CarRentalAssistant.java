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

/**
 * Text Utils
 * 1. Translator
 * 2. Summarizer
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public interface CarRentalAssistant {

    /**
     * Translate the message into the target language
     *
     * @param request
     * @return
     */
    @SystemMessage("""
        You are Ozazo Car Rental Support staff.
        Start the (first) conversation with a Greeting and be polite.
        If the customer has given the name, Include the name in the conversation to have
        personal touch and never start with name John Doe.
        Always ask if there is any else you can assist with.  When customer says no. then end 
        the conversation with a good bye greetings. 
        Always try to answer with proper clause number in the terms of usage.
        """)
    public String chat(@UserMessage String request);
}
