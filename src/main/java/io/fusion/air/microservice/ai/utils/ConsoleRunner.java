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
// Custom
import io.fusion.air.microservice.ai.examples.assistants.CarRentalAssistant;
import io.fusion.air.microservice.ai.services.CustomDataAnalyzer;
import io.fusion.air.microservice.ai.services.ImageBuilder;
import io.fusion.air.microservice.ai.services.TemplateManager;
// Spring
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Console Runner
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Component
public class ConsoleRunner implements CommandLineRunner {
    // Set Logger -> Lookup will automatically determine the class name.
    private static final Logger log = getLogger(lookup().lookupClass());

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==========================================================================================");
            System.out.println("Ask your questions to HAL 9000. Algo >> "+AiConstants.getAlgo());
            System.out.println("To create an image, prefix the text with IMAGE:");
            System.out.println("To Analyze & Search Custom Data, prefix the text with CUSTOM:");
            System.out.println("To Get Structured data use [P1 for Recipe, [P2 for Movies etc.. ");
            System.out.println("Prompt Examples:");
            System.out.println("Recipe = [P1: oven dish, cucumber, potato, tomato, red meat, olives, olive oil");
            System.out.println("Movies = [P2: Bramayugam OR Malaikotai Vaaliban");
            System.out.println("Type exit or quit, to quit the Prompt.");
            System.out.println("------------------------------------------------------------------------------------------");
            while (true) {
                System.out.print("User: >>> ");
                String userQuery = scanner.nextLine();
                if(userQuery == null || userQuery.length() == 0) {
                    continue;
                }
                System.out.println("==========================================================================================");
                if ("exit".equalsIgnoreCase(userQuery) || "quit".equalsIgnoreCase(userQuery)
                        || "q".equalsIgnoreCase(userQuery)) {
                    break;
                }
                if(userQuery.startsWith("IMAGE: ")) {
                    String query = userQuery.replaceAll("IMAGE:", "");
                    ImageBuilder.downloadImage(ImageBuilder.createImage(query));
                    continue;
                } else if(userQuery.startsWith("CUSTOM: ")) {
                    String query = userQuery.replaceAll("CUSTOM:", "");
                    CustomDataAnalyzer.processFile(query);
                    continue;
                } else if(userQuery.startsWith("[P1")) {
                    TemplateManager.structuredTemplate(userQuery);
                    continue;
                } else if(userQuery.startsWith("[P2")) {
                    String[] dArray = userQuery.split(":");
                    CustomDataAnalyzer.processMultiFiles(dArray[1]);                    continue;
                } else {
                    String response = CustomDataAnalyzer.processUserQuery(userQuery);
                    System.out.println("--[HAL9000]---------------------------------------------------------------------------");
                    System.out.println(response);
                }
                System.out.println("------------------------------------------------------------------------------------------");
            }
        }
    }

    /**
     * Start a Command Line Conversation with an Gen AI Assistant
     *
     * @param assistant
     */
    public static void startConversationWith(CarRentalAssistant assistant) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==========================================================================================");
            System.out.println(" OZAZO Car Rental Service ChatBot.... ");
            System.out.println("Type exit or [q]uit, to quit the Prompt.");
            System.out.println("------------------------------------------------------------------------------------------");
            while (true) {
                System.out.print("User: >>> ");
                String userQuery = scanner.nextLine();
                if(userQuery == null || userQuery.length() == 0) {
                    continue;
                }
                System.out.println("------------------------------------------------------------------------------------------");
                if ("exit".equalsIgnoreCase(userQuery) || "quit".equalsIgnoreCase(userQuery)
                        || "q".equalsIgnoreCase(userQuery)) {
                    break;
                }
                String response = assistant.chat(userQuery);
                System.out.println("--[HAL9000]---------------------------------------------------------------------------");
                System.out.println(response);
                System.out.println("------------------------------------------------------------------------------------------");
            }
        }
    }
}
