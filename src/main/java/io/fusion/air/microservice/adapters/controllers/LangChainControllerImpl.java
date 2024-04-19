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
package io.fusion.air.microservice.adapters.controllers;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.fusion.air.microservice.ai.AiAssistant;
import io.fusion.air.microservice.domain.models.core.StandardResponse;
import io.fusion.air.microservice.server.controllers.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * LangChain Controller for the Service
 *
 * Only Selected Methods will be secured in this packaged - which are Annotated with
 * @AuthorizationRequired
 * @Operation(summary = "Cancel Product", security = { @SecurityRequirement(name = "bearer-key") })
 * 
 * @author arafkarsh
 * @version 1.0
 * 
 */
@CrossOrigin
@Configuration
@RestController
// "/ms-ai/api/v1"
@RequestMapping("${service.api.path}/ai")
@RequestScope
@Tag(name = "AI", description = "Ex. io.f.a.m.adapters.controllers.LangChainControllerImpl")
public class LangChainControllerImpl extends AbstractController {

	// Set Logger -> Lookup will automatically determine the class name.
	private static final Logger log = getLogger(lookup().lookupClass());

	@Value("${openai.api.key}")
	private String OPENAI_API_KEY;
	// private ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

	// @Autowired
	// private Assistant assistant;

	private final AiAssistant aiAssitant;

	public LangChainControllerImpl(AiAssistant _aiAssistant) {
		aiAssitant = _aiAssistant;
	}

	/**
	 * Create the AI Chat Conversation
	 */
	@Operation(summary = "AI Chat")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "AI Conversations",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Create the Chat Response",
					content = @Content)
	})
	@PostMapping("/chat")
	public ResponseEntity<StandardResponse> createProduct( @RequestBody String _msg) {
		log.info("|"+name()+"|Chat Request to AI... "+_msg);
		log.info("Open_API_KEY = "+OPENAI_API_KEY);
		StandardResponse stdResponse = createSuccessResponse("AI Response");
		// String response = " Some Response... "; // model.generate(_msg);
		String response = aiAssitant.chat(_msg);
		stdResponse.setPayload(response);
		return ResponseEntity.ok(stdResponse);
	}

 }