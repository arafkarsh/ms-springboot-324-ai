package io.fusion.air.microservice.ai;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Component
public class AssistantTools {

    /**
     * This tool is available to {@link AiAssistant}
     */
    @Tool
    String currentTime() {
        return LocalTime.now().toString();
    }

}
