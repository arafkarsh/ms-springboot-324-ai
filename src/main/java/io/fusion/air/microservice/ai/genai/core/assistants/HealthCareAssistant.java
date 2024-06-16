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
package io.fusion.air.microservice.ai.genai.core.assistants;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

/**
 * HealthCare Assistant
 * 1. Diagnosis Details
 * 2. Prescription Details
 * 3. Lab Details
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public interface HealthCareAssistant extends Assistant{

    /**
     * iCare Health Care
     *   SystemMessage is a special type of message, so it is treated differently from other message types:
     *    - Once added, a SystemMessage is always retained.
     *    - Only one SystemMessage can be held at a time.
     *    - If a new SystemMessage with the same content is added, it is ignored.
     *    - If a new SystemMessage with different content is added, it replaces the previous one.
     *
     * @param _userMessage
     * @return
     */
    @SystemMessage("""
        You are iCare Health Care Hospital Support staff.
        Start the (first) conversation with a Greeting and be polite.
        You are having a conversation with a Doctor who is requesting for a patient details.
        Always ask if there is anything else you can assist with.
        When Doctor says no. then end the conversation with a good bye greetings.
        """)
    public String chat(@UserMessage String _userMessage);

    /**
     * Chat Memory with a Memory ID
     *   SystemMessage is a special type of message, so it is treated differently from other message types:
     *    - Once added, a SystemMessage is always retained.
     *    - Only one SystemMessage can be held at a time.
     *    - If a new SystemMessage with the same content is added, it is ignored.
     *    - If a new SystemMessage with different content is added, it replaces the previous one.
     * @param _memoryId
     * @param _userMessage
     * @return
     */
    @SystemMessage("""
        You are iCare Health Care Hospital Support staff.
        You are having a conversation with a Doctor who is requesting for a patient details.
        Extract the diagnosis which matches the  Patient Name or Patient ID.
        Always ask if there is anything else you can assist with.
        When Doctor says no. then end the conversation with a good bye greetings. 
        """)
    public String chat(@MemoryId String _memoryId, @UserMessage String _userMessage);
}
