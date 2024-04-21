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

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class AiConstants {

    // INPUT = $0.01 / 1K tokens	OUTPUT = $0.03 / 1K tokens
    public static final String GPT_4_TURBO      = "gpt-4-turbo-2024-04-09";
    // INPUT = $0.03 / 1K tokens	OUTPUT = $0.06 / 1K tokens
    public static final String GPT_4                = "gpt-4";
    // INPUT = $0.06 / 1K tokens	OUTPUT = $0.12 / 1K tokens
    public static final String GPT_4_32K          = "gpt-4-32k";
    // INPUT = 	$0.0005 / 1K tokens    OUTPUT = $0.0015 / 1K tokens
    public static final String GPT_3_5_TURBO   = "gpt-3.5-turbo-0125";

    // $0.040 / image, Standard 1024×1024
    public static final String DALL_E_3            = "dall-e-3";
    // $0.020 / image, 1024×1024
    public static final String DALL_E_2            = "dall-e-2";

    public static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
}
