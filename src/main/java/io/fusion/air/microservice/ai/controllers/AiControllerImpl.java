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
package io.fusion.air.microservice.ai.controllers;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.fusion.air.microservice.ai.setup.HAL9000;
import io.fusion.air.microservice.domain.exceptions.DataNotFoundException;
import io.fusion.air.microservice.domain.models.core.StandardResponse;
import io.fusion.air.microservice.server.controllers.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.LinkedHashMap;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Ai Controller for the Service
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
@Tag(name = "AI", description = "Ex. io.f.a.m.adapters.controllers.AiControllerImpl")
public class AiControllerImpl extends AbstractController {

	// Set Logger -> Lookup will automatically determine the class name.
	private static final Logger log = getLogger(lookup().lookupClass());

	@Value("${openai.api.key}")
	private String OPENAI_API_KEY;
	// private ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

	private final ChatLanguageModel chatLanguageModel;
	private final HAL9000 aiAssitant;

	/**
	 * Auto Wire the Language Model and Assistant
	 * @param _chatLanguageModel
	 * @param _HAL9000
	 */
	public AiControllerImpl(ChatLanguageModel _chatLanguageModel, HAL9000 _HAL9000) {
		this.chatLanguageModel = _chatLanguageModel;
		this.aiAssitant = _HAL9000;
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
		// log.info("Open_API_KEY = "+OPENAI_API_KEY);
		StandardResponse stdResponse = createSuccessResponse("AI Response");
		String response = aiAssitant.chat(_msg);
		stdResponse.setPayload(response);
		return ResponseEntity.ok(stdResponse);
	}

	@GetMapping("/chat")
	public ResponseEntity<StandardResponse> model(@RequestParam(value = "message", defaultValue = "Hello") String _msg) {
		log.info("|" + name() + "|Chat Request to AI... " + _msg);
		String response = chatLanguageModel.generate(_msg);
		if(response != null) {
			String[] rows = response.split("\n");
			StandardResponse stdResponse = createSuccessResponse("AI Response");
			LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("Request", _msg);
			data.put("Response", rows);
			stdResponse.setPayload(data);
			return ResponseEntity.ok(stdResponse);
			// return chatLanguageModel.generate(_msg);
		}
		throw new DataNotFoundException("Unable to retrieve data... !");
	}
 }