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
package io.fusion.air.microservice.ai.genai.controllers;

import dev.langchain4j.model.chat.ChatLanguageModel;
// Custom
import io.fusion.air.microservice.domain.entities.example.ChatMessageEntity;
import io.fusion.air.microservice.ai.genai.core.services.CustomDataAnalyzer;
import io.fusion.air.microservice.ai.genai.core.services.TemplateManager;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;
import io.fusion.air.microservice.domain.exceptions.DataNotFoundException;
import io.fusion.air.microservice.domain.models.core.StandardResponse;
import io.fusion.air.microservice.domain.ports.services.ChatMessageService;
import io.fusion.air.microservice.server.controllers.AbstractController;
// Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
// Java
import java.util.LinkedHashMap;
import java.util.List;

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
@RequestMapping("${service.api.path}/ai/openai")
@RequestScope
@Tag(name = "AI - OpenAi", description = "GPT 3.5, GPT 3.5 Turbo, GPT 4, GPT 4o, Dall-E 3")
public class AiOpenAiControllerImpl extends AbstractController {

	// Set Logger -> Lookup will automatically determine the class name.
	private static final Logger log = getLogger(lookup().lookupClass());

	@Value("${openai.api.key}")
	private String OPENAI_API_KEY;

	@Autowired
	private ChatMessageService chatMessageService;

	// Chat Language Model is automatically injected by the constructor
	// based on the Qualifier "ChatLanguageModelGPT"
	private final ChatLanguageModel chatLanguageModel;

	/**
	 * Auto Wire the Language Model
	 * Loading the Bean with the name ChatLanguageModelGPT (defined in AiBeans).
	 * The Qualifier is to ensure that the right Bean is Autowired.
	 *
	 * @param _chatLanguageModel
	 */
	public AiOpenAiControllerImpl(@Qualifier("ChatLanguageModelGPT")
							ChatLanguageModel _chatLanguageModel) {
		this.chatLanguageModel = _chatLanguageModel;
	}

	/**
	 * Create the AI Chat Conversation
	 */
	@Operation(summary = "AI Chat - Generic ChatGPT 3.5 or 4o ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "AI Conversations",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Create the Chat Response",
					content = @Content)
	})
	@PostMapping("/chat")
	public ResponseEntity<StandardResponse> chat( @RequestBody String _msg) {
		log.info("|"+name()+"|Chat Request to AI...  "+AiConstants.getOpenAIDefaultModel()+" .. "+_msg);
		// log.info("Open_API_KEY = "+OPENAI_API_KEY);
		String response = chatLanguageModel.generate(_msg);
		if(response != null) {
			return ResponseEntity.ok(createResponse(response, _msg));
		}
		throw new DataNotFoundException("Unable to retrieve data... !");
	}

	@Operation(summary = "AI Chat - Custom Data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = """
							Who were the Key Characters in the movie Bramayugam?
            				What was the rating?
            				Elaborate the Characters in the movie.
							""",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Create the Chat Response",
					content = @Content)
	})
	@PostMapping("/chat/custom")
	public ResponseEntity<StandardResponse> chatCustomData(@RequestBody String _msg) {
		log.info("|" + name() + "|Custom Chat Request to AI Engine "+AiConstants.getOpenAIDefaultModel()+"... " + _msg);
		// log.info("Open_API_KEY = "+OPENAI_API_KEY);
		String response = CustomDataAnalyzer.processFile(_msg);
		if(response != null) {
			return ResponseEntity.ok(createResponse(response, _msg));
		}
		throw new DataNotFoundException("Unable to retrieve data... !");
	}

	@Operation(summary = "AI Chat - Structured Data - Recipe")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Recipe: oven dish, cucumber, potato, tomato, salmon, olives, olive oil",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Create the Chat Response",
					content = @Content)
	})
	@PostMapping("/chat/structured")
	public ResponseEntity<StandardResponse> chatStructuredData(@RequestBody String _msg) {
		log.info("|" + name() + "|Structured Chat Request to AI Engine "+AiConstants.getOpenAIDefaultModel()+"... " + _msg);
		// log.info("Open_API_KEY = "+OPENAI_API_KEY);
		String response = TemplateManager.structuredTemplate("[P1: "+_msg);
		if(response != null) {
			return ResponseEntity.ok(createResponse(response, _msg));
		}
		throw new DataNotFoundException("Unable to retrieve data... !");
	}

	/**
	 * GET Method Call to ChatMessages by User Id
	 *
	 * @return
	 */
	@Operation(summary = "Get the ChatMessage By User ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "ChatMessages Retrieved for User ID",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
					description = "Invalid User ID.",
					content = @Content)
	})
	@GetMapping("/chat/userid/{userId}")
	@ResponseBody
	public ResponseEntity<StandardResponse> getProductStatus(@PathVariable("userId") String _userId) throws Exception {
		log.info("|"+name()+"|Request to Get ChatMessages by User ID.. "+ _userId);
		List<ChatMessageEntity> chats = chatMessageService.fetchByUserId(_userId);
		if(chats.size() > 0) {
			StandardResponse stdResponse = createSuccessResponse("Chats Fetch Success!");
			stdResponse.setPayload(chats);
			return ResponseEntity.ok(stdResponse);
		}
		throw new DataNotFoundException("Chats not found for User Id "+ _userId);
	}

	/**
	 * Create Response as Standard Response
	 *
	 * @param _response
	 * @param _msg
	 * @return
	 */
	private StandardResponse createResponse(String _response, String _msg) {
		String[] rows = _response.split("\n");
		StandardResponse stdResponse = createSuccessResponse("AI Response");
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("Algo", AiConstants.getOpenAIDefaultModel());
		data.put("Request", _msg);
		data.put("Response", rows);
		stdResponse.setPayload(data);
		return stdResponse;
	}
 }