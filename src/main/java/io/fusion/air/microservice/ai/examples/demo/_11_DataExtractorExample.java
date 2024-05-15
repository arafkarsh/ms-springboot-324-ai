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
import io.fusion.air.microservice.ai.examples.models.Person;
import io.fusion.air.microservice.ai.examples.models.Recipe;
import io.fusion.air.microservice.ai.examples.models.StructuredRecipePrompt;
import io.fusion.air.microservice.ai.examples.utils.Chef;
import io.fusion.air.microservice.ai.examples.utils.DataExtractor;
import io.fusion.air.microservice.ai.utils.AiBeans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.util.Arrays.asList;

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

    public static ChatLanguageModel model = new AiBeans().createChatLanguageModel();
    public static DataExtractor extractor = AiServices.create(DataExtractor.class, model);

    public static void numberExtractor() {
        // Extract Numbers
        String request = """
                    After countless millennia of computation, the supercomputer Deep Thought 
                    finally announced that the answer to the ultimate question of life, the 
                    universe, and everything was forty two.""";

        int intNumber = extractor.extractInt(request);
        AiBeans.printResult(request, "Number = "+intNumber);
    }

    public static void DateTimeExtractor() {
        // Extract Date and Time
        StringBuilder sb = new StringBuilder();
        String request = """
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

    public static void pojoExtractor() {
        // POJO Person Extractor
        String request = """
                In 1968, amidst the fading echoes of Indian Independence Day, 
                a child named John arrived under the calm evening sky. 
                This newborn, bearing the surname Doe, marked the start of a new journey.""";

        Person person = extractor.extractPersonFrom(request);
        AiBeans.printResult(request, person.toString());
    }

    public static void complexPojoExtractor() {
        Chef chef = AiServices.create(Chef.class, model);
        Recipe recipe = chef.createRecipeFrom("cucumber", "tomato", "feta", "onion", "olives", "lemon");
        System.out.println(recipe);

        StructuredRecipePrompt recipe2 = new StructuredRecipePrompt("oven dish",
                asList("cucumber", "tomato", "feta", "onion", "olives", "potatoes") );

        Recipe anotherRecipe = chef.createRecipe(recipe2);
        System.out.println(anotherRecipe);
    }

    public static void main(String[] args) {
        // Extract Numbers
        numberExtractor();
        // Extract Date and Time
        DateTimeExtractor();
        // POJO Person Extractor
         pojoExtractor();;
        // Complex Pojo Extractor with Descriptions (rules)
        complexPojoExtractor();
    }
}
