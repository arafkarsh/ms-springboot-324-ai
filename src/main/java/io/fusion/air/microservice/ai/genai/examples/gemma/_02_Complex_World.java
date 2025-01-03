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
package io.fusion.air.microservice.ai.genai.examples.gemma;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.genai.core.assistants.HAL9000Assistant;
import io.fusion.air.microservice.ai.genai.utils.AiBeans;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;
import io.fusion.air.microservice.utils.Std;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class _02_Complex_World {

    public static void main(String[] args) {
        // Create Chat Language Model Google Gemma
        ChatLanguageModel model = AiBeans.getChatLanguageModelLlama(AiConstants.OLLAMA_GEMMA);
        // Create the Ai Assistant
        HAL9000Assistant hal9k = new AiBeans().createHAL9000(model);

        AiBeans.printModelDetails(AiConstants.LLM_OLLAMA, AiConstants.OLLAMA_GEMMA);
        // Run the DownloadAllData Cases
        complexWorld1(hal9k);
        complexWorld2(hal9k);
        complexWorld3(hal9k);
    }

    /**
     * Example 1
     * @param hal9K
     */
    public static void complexWorld1(HAL9000Assistant hal9K) {
        interact(hal9K, "What is the square root of 144233377?");
        interact(hal9K, "Capitalize every third letter except the sixth letter: abcdefghjiklmnop");
    }

    /**
     * Example 2
     * @param hal9K
     */
    public static void complexWorld2(HAL9000Assistant hal9K) {
        interact(hal9K, "What are the hours between 06:00 on 7 Feb 1970 and 11:00 on 02 Jun 1980?");
        interact(hal9K, "What is the sum of all the digits in the previous question? Is that a Prime Number?");
        interact(hal9K, "What are the hours between 11:00 on 2 Jun 1980 and 12:00 on 11 Mar 2024?");
        validateCalc();
        Std.println("--------------------------------------------------------------");
    }

    /**
     * Example 3
     * @param hal9K
     */
    public static void complexWorld3(HAL9000Assistant hal9K) {
        interact(hal9K, "Explain French Revolution in details with critical events.");
    }

    /**
     * Interact with HAL9000Assistant Ai Assistant
     * @param hal9K
     * @param userMessage
     */
    private static void interact(HAL9000Assistant hal9K, String userMessage) {
        Std.println("[Human]: " + userMessage);
        String response = hal9K.chat(userMessage);
        Std.println("[HAL9K]: " + response);
        Std.println("--------------------------------------------------------------");
    }

    /**
     * Validate the Hours Calculation
     */
    private static void validateCalc() {
        LocalDateTime startTime = LocalDateTime.of(1970, 2, 7, 6, 0);
        LocalDateTime endTime = LocalDateTime.of(1980, 6, 2, 11, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2024, 3, 11, 12, 0);
        // Calculate the difference in hours
        long hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);
        long hoursBetween2 = ChronoUnit.HOURS.between(endTime, endTime2);
        long sumTotal = sumOfDigits(hoursBetween);
        long sumTotal2 = sumOfDigits(hoursBetween2);

        // Output the result
        Std.println(
                "Actual Hours between: 1970-02-07T06:00 & 1980-06-02T11:00 => " + hoursBetween
                        + "  Sum = "+sumTotal+ " Is prime = "+isPrime(sumTotal) + ", Days = "+hoursBetween / 24);
        Std.println(
                "Actual Hours between: 1980-06-02T11:00 & 2024-03-11T12:00 => " + hoursBetween2
                        + " Sum = "+sumTotal2+ " Is prime = "+isPrime(sumTotal2) + ", Days = "+hoursBetween2 / 24);
    }

    /**
     * Sum Digits
     * @param number
     * @return
     */
    private static long sumOfDigits(long number) {
        long sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    /**
     * Find Prime
     * @param number
     * @return
     */
    private static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
