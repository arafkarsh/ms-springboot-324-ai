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
import io.fusion.air.microservice.ai.genai.core.services.CustomDataAnalyzer;
import io.fusion.air.microservice.ai.genai.core.services.TemplateManager;
import io.fusion.air.microservice.ai.genai.utils.AiConstants;
import io.fusion.air.microservice.domain.exceptions.DataNotFoundException;
import io.fusion.air.microservice.server.controllers.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

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
@Configuration
@RestController
// "/ms-ai/api/v1"
@RequestMapping("${service.api.path}/ai/openai/string")
@Tag(name = "AI - OpenAi", description = "GPT 3.5, GPT 3.5 Turbo, GPT 4, GPT 4o, Dall-E 3")
public class AiOpenAiStringControllerImpl extends AbstractController {

	// Set Logger -> Lookup will automatically determine the class name.
	private static final Logger log = getLogger(lookup().lookupClass());


	// Chat Language Model is automatically injected by the constructor
	// based on the Qualifier "ChatLanguageModelGPT"
	private final ChatLanguageModel chatLanguageModel;

	private final String defaultMode;

	/**
	 * Auto Wire the Language Model
	 * Loading the Bean with the name ChatLanguageModelGPT (defined in AiBeans).
	 * The Qualifier is to ensure that the right Bean is Autowired.
	 *
	 * @param chatLanguageModel
	 */
	public AiOpenAiStringControllerImpl(@Qualifier("ChatLanguageModelGPT")
							ChatLanguageModel chatLanguageModel) {
		this.chatLanguageModel = chatLanguageModel;
		this.defaultMode = AiConstants.getOpenAIDefaultModel();

	}

	/**
	 * Create the AI Chat Conversation
	 */
	@Operation(summary = "AI Chat - Generic ChatGPT 3.5 or 4o ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "AI Conversations",
					content = {@Content(mediaType = "application/text")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Create the Chat Response",
					content = @Content)
	})
	@PostMapping("/chat")
	public String chat( @RequestBody String msg) {
		msg = HtmlUtils.htmlEscape(msg);
		log.info("|Chat Request to AI...  {} ... {} ", defaultMode, msg);
		String response = chatLanguageModel.generate(msg);
		if(response != null) {
			return createResponse(response, msg);
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
					content = {@Content(mediaType = "application/text")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Create the Chat Response",
					content = @Content)
	})
	@PostMapping("/chat/custom")
	public String chatCustomData(@RequestBody String msg) {
		msg = HtmlUtils.htmlEscape(msg);
		log.info("|Custom Chat Request to AI Engine {} ... {} ", defaultMode, msg);
		String response = CustomDataAnalyzer.processFile(msg);
		if(response != null) {
			return createResponse(response, msg);
		}
		throw new DataNotFoundException("Unable to retrieve data... !");
	}

	@Operation(summary = "AI Chat - Structured Data - Recipe")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Recipe: oven dish, cucumber, potato, tomato, salmon, olives, olive oil",
					content = {@Content(mediaType = "application/text")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Create the Chat Response",
					content = @Content)
	})
	@PostMapping("/chat/structured")
	public String chatStructuredData(@RequestBody String msg) {
		msg = HtmlUtils.htmlEscape(msg);
		log.info("|Structured Chat Request to AI Engine {} ... {} ", defaultMode, msg);
		String response = TemplateManager.structuredTemplate("[P1: "+msg);
		if(response != null) {
			return createResponse(response, msg);
		}
		throw new DataNotFoundException("Unable to Fetch data... !");
	}

	/**
     * Create Response as Standard Response
	 *
	 * @param response
     * @param msg
     * @return
	 */
	private String createResponse(String response, String msg) {
		response = HtmlUtils.htmlEscape(response);
		msg = HtmlUtils.htmlEscape(msg);
		StringBuilder sb = new StringBuilder();
		String request = msg.replace("\n", " ").trim();
		sb.append("Algorithm = ").append(AiConstants.getOpenAIDefaultModel()).append("\n");
		sb.append("Request   = ").append(request).append("\n");
		sb.append("Response  = ").append("\n").append(response);
		return sb.toString();
	}
 }