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

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import io.fusion.air.microservice.ai.examples.utils.DataExtractor;
import io.fusion.air.microservice.ai.utils.AiBeans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Data Extractor
 *
 * 1. Extract Numbers
 * 2. Extract Date
 * 3. Extract Time
 * 4. Extract Date and Time
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _11_DataExtractorExample {

    public static void main(String[] args) {

        ChatLanguageModel model = new AiBeans().createChatLanguageModel();
        DataExtractor extractor = AiServices.create(DataExtractor.class, model);

        // Extract Numbers
        String request = """
                    After countless millennia of computation, the supercomputer Deep Thought 
                    finally announced that the answer to the ultimate question of life, the 
                    universe, and everything was forty two.""";

        int intNumber = extractor.extractInt(request);
        AiBeans.printResult(request, "Number = "+intNumber);

        // Extract Date and Time
        StringBuilder sb = new StringBuilder();
        request = """
                    The tranquility pervaded the evening of 1968, just fifteen minutes 
                    shy of midnight, following the celebrations of Independence Day.""";

        LocalDate date = extractor.extractDateFrom(request);
        sb.append("Date      = ").append(date).append("\n");
        LocalTime time = extractor.extractTimeFrom(request);
        sb.append("Time      = ").append(time).append("\n");
        LocalDateTime dateTime = extractor.extractDateTimeFrom(request);
        sb.append("DateTime = ").append(dateTime);

        AiBeans.printResult(request, sb.toString());

    }
}
