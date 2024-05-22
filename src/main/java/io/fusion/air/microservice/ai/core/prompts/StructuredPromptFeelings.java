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
package io.fusion.air.microservice.ai.core.prompts;

import dev.langchain4j.model.input.structured.StructuredPrompt;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@StructuredPrompt({
        "Convey the feelings {{feeling}} based on the content {{content}}.",
        "Structure your answer in the following way:",
        "Feeling: ...",
        "Key points: ...",
        "Explanation: ..."
})
public class StructuredPromptFeelings {

    private final String feeling;
    private final String content;

    /**
     * @param _feeling
     * @param _content
     */
    public StructuredPromptFeelings(String _feeling, String _content) {
        this.feeling = _feeling;
        this.content = _content;
    }

    public String getFeeling() {
        return feeling;
    }

    public String getContent() {
        return content;
    }
}
