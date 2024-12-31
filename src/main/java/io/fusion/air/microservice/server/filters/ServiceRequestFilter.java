/**
 * (C) Copyright 2021 Araf Karsh Hamid 
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
package io.fusion.air.microservice.server.filters;
// Java

import io.fusion.air.microservice.server.config.ServiceConfig;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * A ServletRequest is defined as coming into scope of a web application 
 * when it is about to enter the first servlet or filter of the web 
 * application, and as going out of scope as it exits the last servlet or 
 * the first filter in the chain
 * 
 * Source: https://docs.oracle.com/javaee/7/api/javax/servlet/ServletRequestListener.html
 *
 * MDC = Mapped Diagnostic Contexts
 * A Mapped Diagnostic Context, or MDC in short, is an instrument for distinguishing interleaved
 * log output from different sources. Log output is typically interleaved when a server handles
 * multiple clients near-simultaneously.
 *
 * MDC is used to stamp each request. It is done by putting the contextual information about the
 * request into the MDC
 *
 * MDC.put("sessionId", "abcd");
 * MDC.put("userId", "1234");
 *
 * Printing the values from MDC in the logs
 *
 * <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
 *   <layout>
 *     <Pattern>%d{DATE} %p %X{sessionId} %X{userId} %c - %m%n</Pattern>
 *   </layout>
 * </appender>
 * 
 * @author arafkarsh
 *
 */
@WebListener
public class ServiceRequestFilter implements ServletRequestListener {

	// WARNING: DON'T USE CONSTRUCTOR for AUTOWIRING
	@Autowired
	private ServiceConfig serviceConfig;

	/**
	 * Add the following values into the log for the request
	 * Unique Request ID
	 * Client IP Address
	 * Client Port Number
	 *
	 * @param sre
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest httpRequest = (HttpServletRequest) sre.getServletRequest();
		String reqId =UUID.randomUUID().toString();
		MDC.put("ReqId", reqId);
		MDC.put("IP", httpRequest.getRemoteHost());
		MDC.put("Port", String.valueOf(httpRequest.getRemotePort()));
		MDC.put("URI", httpRequest.getRequestURI());
		MDC.put("Protocol", httpRequest.getMethod());
		MDC.put("user", "john.doe");

		String name= (serviceConfig != null) ? serviceConfig.getServiceName(): "NotDefined";
		MDC.put("Service", name);
	}

	/**
	 * Clear all the values When Request is done.
	 * @param sre
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		MDC.clear();
	}
}