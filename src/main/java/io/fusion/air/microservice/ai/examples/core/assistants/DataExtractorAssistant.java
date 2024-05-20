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
package io.fusion.air.microservice.ai.examples.core.assistants;

import dev.langchain4j.service.UserMessage;
import io.fusion.air.microservice.ai.examples.core.models.Person;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Extracts Number, Date, Time from a Text.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public interface DataExtractorAssistant {

    // Number Extractor
    @UserMessage("Extract number from {{it}}")
    public int extractInt(String _text);

    @UserMessage("Extract number from {{it}}")
    public long extractLong(String _text);

    @UserMessage("Extract number from {{it}}")
    public BigInteger extractBigInteger(String _text);

    @UserMessage("Extract number from {{it}}")
    public float extractFloat(String _text);

    @UserMessage("Extract number from {{it}}")
    public double extractDouble(String _text);

    @UserMessage("Extract number from {{it}}")
    public BigDecimal extractBigDecimal(String _text);

    // Date & Time Extractor
    @UserMessage("Extract date from {{it}}")
    public LocalDate extractDateFrom(String _text);

    @UserMessage("Extract time from {{it}}")
    public LocalTime extractTimeFrom(String _text);

    @UserMessage("Extract date and time from {{it}}")
    public LocalDateTime extractDateTimeFrom(String _text);

    // POJO Extractor
    @UserMessage("Extract information about a person from {{it}}")
    public Person extractPersonFrom(String _text);
}
