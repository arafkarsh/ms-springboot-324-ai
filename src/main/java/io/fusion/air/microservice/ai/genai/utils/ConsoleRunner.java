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
package io.fusion.air.microservice.ai.genai.utils;
// Custom
import io.fusion.air.microservice.ai.genai.core.assistants.Assistant;
import io.fusion.air.microservice.ai.genai.core.services.ConsoleChatService;
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

    /**
     * DownloadAllData the Chat GPT in Console with Custom Data, Image Creation etc.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==========================================================================================");
            System.out.println("Ask your questions to HAL 9000. LLM >> "+AiConstants.getOpenAIDefaultModel());
            System.out.println("To create an image, prefix the text with IMAGE:");
            System.out.println("To Analyze & Search Custom Data, prefix the text with CUSTOM:");
            System.out.println("To Get Structured data use [P1 for Recipe, [P2 for Movies etc.. ");
            System.out.println("Prompt Examples:");
            System.out.println("Recipe = [P1: oven dish, cucumber, potato, tomato, red meat, olives, olive oil");
            System.out.println("Movies = [P2: Bramayugam OR Malaikotai Vaaliban");
            System.out.println("Type exit or [q]uit, to QUIT the Prompt.");
            System.out.println("------------------------------------------------------------------------------------------");
            ConsoleChatService chatAssistant = new ConsoleChatService();
            while (true) {
                if( handleUserRequests( chatAssistant,  scanner) < 0) break;
            }
        }
    }

    /**
     * Start a Command Line Conversation with an Gen AI Assistant
     *
     * @param _assistant
     */
    public static void startConversationWith(Assistant _assistant) {
        startConversationWith(_assistant, "OZAZO Car Rental Service ChatBot....");
    }

    /**
     * Start a Command Line Conversation with an Gen AI Assistant
     *
     * @param _assistant
     * @param _header
     */
    public static void startConversationWith(Assistant _assistant, String _header) {
        if(_assistant == null || _header == null) {
            System.out.println("Invalid Inputs!!");
            return;
        }
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==========================================================================================");
            System.out.println(_header);
            System.out.println("Type exit or [q]uit, to QUIT the Prompt.");
            System.out.println("------------------------------------------------------------------------------------------");
            while (true) {
                if( handleUserRequests( _assistant,  scanner) < 0) break;
            }
        }
    }

    /**
     * Conversation with Structured Prompts
     * @param _assistant
     * @param _header
     */
    public static void startConversationWithPrompts(Assistant _assistant, String _header) {
        if(_assistant == null || _header == null) {
            System.out.println("Invalid Inputs!!");
            return;
        }
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==========================================================================================");
            System.out.println(_header);
            System.out.println("Search Examples:");
            System.out.println("I need the diagnosis history of Akiera Kiera for the past 3 years");
            System.out.println("I need the diagnosis history of Jane Susan Wood for the past 4 years");
            System.out.println("Prompts Examples: ");
            System.out.println("[P: Patient Name");
            System.out.println("[P: Patient Name, Disease");
            System.out.println("Type exit or [q]uit, to QUIT the Prompt.");
            System.out.println("------------------------------------------------------------------------------------------");
            while (true) {
                if( handleUserRequests( _assistant,  scanner) < 0) break;
            }
        }
    }

    /**
     * Handle User Requests
     * @param _assistant
     * @param _scanner
     * @return
     */
    private static byte handleUserRequests(Assistant _assistant, Scanner _scanner) {
        System.out.print("User: >>> ");
        if(_assistant == null || _scanner == null) {
            System.out.println("Invalid Inputs... Quiting the chat..... Have a nice day!");
            return -2;
        }
        String userQuery = _scanner.nextLine();
        if (userQuery == null || userQuery.length() == 0) return 1;
        System.out.println("------------------------------------------------------------------------------------------");
        if ("exit".equalsIgnoreCase(userQuery) || "quit".equalsIgnoreCase(userQuery)
                || "q".equalsIgnoreCase(userQuery)) {
            System.out.println("Quiting the chat..... Have a nice day!");
            return -1;
        }
        try {
            String response = _assistant.chat(userQuery);
            System.out.println("--[HAL9000]---------------------------------------------------------------------------");
            System.out.println(response);
            System.out.println("---------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Chat Error: "+e.getMessage());
        }
        return 0;
    }
}
