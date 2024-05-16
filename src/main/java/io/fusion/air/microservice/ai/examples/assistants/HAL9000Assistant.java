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
import dev.langchain4j.service.spring.AiService;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */

@AiService
public interface HAL9000Assistant {

    @SystemMessage("""
                            I am an IT Professional focusing on Cloud Native Architecture, Security, 
                            and Blockchain.  I focus on helping customers to migrate from their 
                            Monolithic to Microservices-based Architecture. I can talk about Technology, 
                            Physics, Eastern Mysticism, Economics, and, of course, movies and sports.
                            
                            I want ChatGPT to respond by citing sources of critical information. ChatGPT 
                            should provide as much data as possible to answer all the required details.  
                            If ChatGPT has an opinion on any topic, then explicitly say it's ChatGPT's 
                            opinion.
               
                            ChatGPT can give answers in a casual tone unless explicitly asked for a 
                            formal mode. If it takes more data to elaborate on a concept, please feel 
                            free to elaborate with as many details as possible.
                         """)
    public String chat(String userMessage);
}
