/**
 * (C) Copyright 2022 Araf Karsh Hamid
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
package io.fusion.air.microservice.adapters.aop;

// Custom

import io.fusion.air.microservice.domain.models.core.StandardResponse;
import io.fusion.air.microservice.server.config.ServiceConfig;
import io.fusion.air.microservice.utils.Utils;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Input Validator Advice
 *
 * This (AOP) Advice will validate all the inputs at a central location.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@ControllerAdvice
@Order(1) // Don't Change this Order Precedences. Changing this affect the Error Reporting.
public class InputValidatorAdvice {

    // Set Logger -> Lookup will automatically determine the class name.
    private static final Logger log = getLogger(lookup().lookupClass());

    // Autowired using Constructor
    private ServiceConfig serviceConfig;

    /**
     * Autowired using Constructor
     * @param serviceCfg
     */
    public InputValidatorAdvice(ServiceConfig serviceCfg) {
        serviceConfig = serviceCfg;
    }

    /**
     * Validating Complex Object Rules
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors =  ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " | " + error.getDefaultMessage())
                .collect(Collectors.toCollection(ArrayList::new));
        log.debug("462: List Errors = {} ", errors);
        return createErrorResponse( "462",  "Errors: Invalid Method Arguments",  errors );
    }

    /**
     * Validating Method Parameters
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,  WebRequest request) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toCollection(ArrayList::new));
        log.debug("463: List Errors = {} ", errors);
        return createErrorResponse( "463",  "Errors: Input Constraint Violations",  errors );
    }

    /**
     * Create the Error Response
     * @param errorCode
     * @param errorMsg
     * @param errors
     * @return
     */
    private ResponseEntity<Object> createErrorResponse(String errorCode, String errorMsg, List<String> errors ) {
        String errorPrefix = (serviceConfig != null) ? serviceConfig.getServiceApiErrorPrefix() : "AKH";
        long startTime = System.currentTimeMillis();
        String status = "STATUS=ERROR: "+errorMsg;
        Collections.sort(errors);
        StandardResponse stdResponse = Utils.createErrorResponse(errors, errorPrefix, errorCode, HttpStatus.BAD_REQUEST, errorMsg);
        logTime(startTime, status);
        return new ResponseEntity<>(stdResponse, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Log Time with Input Validation Errors
     * @param startTime
     * @param status
     */
    private void logTime(long startTime, String status) {
        long timeTaken=System.currentTimeMillis() - startTime;
        log.info("2|IV|TIME={} ms|{}|CLASS=|", timeTaken, status);
    }
}
