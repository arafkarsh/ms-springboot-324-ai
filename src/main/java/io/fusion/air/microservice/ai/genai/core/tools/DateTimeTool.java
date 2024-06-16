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
package io.fusion.air.microservice.ai.genai.core.tools;

import dev.langchain4j.agent.tool.Tool;
import io.fusion.air.microservice.ai.genai.core.assistants.HAL9000Assistant;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Date & Time Tool Implementation
 *  @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Component
public class DateTimeTool {

    /**
     * This tool is available to {@link HAL9000Assistant}
     */
    @Tool
    public String currentTime() {
        return LocalTime.now().toString();
    }

}
