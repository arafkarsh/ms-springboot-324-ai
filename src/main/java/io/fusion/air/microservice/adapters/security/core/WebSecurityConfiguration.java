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
package io.fusion.air.microservice.adapters.security.core;
// Custom

import io.fusion.air.microservice.server.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.Arrays;

/**
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final String apiPath;
    private final String hostName;

    /**
     * Autowired using the constructor
     * @param serviceConfig
     */
    public WebSecurityConfiguration(ServiceConfig serviceConfig) {
        apiPath = serviceConfig.getServiceApiPath();
        hostName = serviceConfig.getServerHost();
    }

    /**
     * Configures the security filter chain for HTTP requests, applying various security measures
     * such as request authorization, CSRF protection, and content security policies.
     *
     * @param http the {@link HttpSecurity} object to configure HTTP security for the application
     * @return the constructed {@link SecurityFilterChain}
     * @throws Exception if there is a problem during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // enableSecureChannel(http);           // Forces All Request to be Secured (HTTPS)
        csrfProtection(http);                   // Step 1: Set CSRF Protection
        authorizeHttpRequests(http);         // Step 2: Set Authorization Policies
        xFrameProtection(http);               // Step 3: Set X-Frame Protection
        contentSecurityPolicy(http);          // Step 4: Set Content Security Policy
        return http.build();                     // Step 5: Build Security Filter Chain
    }

    /**
     * To Run your Web Application on SSL/TLS
     * @param http
     * @throws Exception
     */
    public void enableSecureChannel(HttpSecurity http) throws Exception {
        http.requiresChannel(channel -> channel
                .anyRequest().requiresSecure()
        );
    }

    /**
     * Configures Cross-Site Request Forgery (CSRF) protection for HTTP security. This method typically
     * enables CSRF protection using a cookie-based CSRF token repository, making CSRF tokens accessible
     * to client-side scripting. Note that CSRF protection is disabled for local testing within this method.
     *
     * @param http the {@link HttpSecurity} object to configure HTTP security for the application
     * @throws Exception if there is a problem during configuration
     */
    private void csrfProtection(HttpSecurity http) throws Exception {
        // Enable CSRF Protection
        // http.csrf(csrf -> ...):
	    // - Configures CSRF protection for the application.
	    // - CSRF protection is enabled by default in Spring Security, but this configuration customizes its behavior.
        // Change the API Path As per the Security Requirement
        // apiPath:
        //	- The variable apiPath likely holds a string such as /api/v1. This would exclude all
        // 	endpoints like /api/v1/* or /api/v1/resource/123 from CSRF validation.
        //	- Typically used to exclude REST API endpoints, which are not vulnerable to CSRF in most cases.
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers(apiPath +"/**")
        );
    }

    /**
     * Configures HTTP security to authorize requests based on the API documentation path.
     * It permits all requests matching the API documentation path and redirects unauthorized
     * access attempts to a custom access denied page.
     *
     * @param http the {@link HttpSecurity} object to configure HTTP security for the application
     * @throws Exception if there is a problem during configuration
     */
    private void authorizeHttpRequests(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(apiPath + "/**").permitAll()
                        // Require authentication for any other requests
                        .anyRequest().permitAll()
                )
                // This configures exception handling, specifically specifying that when a user tries to access a page
                // they're not authorized to view, they're redirected to "/403" (typically an "Access Denied" page).
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403"))
                // Make sure to add stateless session management since it's a microservice
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }

    /**
     * Configures HTTP security to protect against "clickjacking" attacks by setting the "X-Frame-Options" header
     * and adding a content security policy.
     *
     * @param http the {@link HttpSecurity} object to configure HTTP security for the application
     * @throws Exception if there is a problem during configuration
     */
    private void xFrameProtection(HttpSecurity http) throws Exception {
        // X-Frame-Options is a security header that is intended to protect your website against "clickjacking" attacks.
        // Clickjacking is a malicious technique of tricking web users into revealing confidential information or taking
        // control of their interaction with the website, by loading your website in an iframe of another website and
        // then overlaying it with additional content.
        http.headers(headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
                .contentSecurityPolicy(csp -> csp
                        .policyDirectives("default-src 'self'; frame-ancestors 'none'")
                )
        );
    }

    /**
     * Configures the Content Security Policy (CSP) for HTTP security.
     * The CSP is a security measure that helps prevent a range of attacks,
     * including Cross-Site Scripting (XSS) and data injection attacks.
     * It specifies which domains the browser should consider to be valid sources of executable scripts
     * and other resources.
     *
     * @param http the {@link HttpSecurity} object to configure HTTP security for the application
     * @throws Exception if there is a problem during configuration
     */
    private void contentSecurityPolicy(HttpSecurity http) throws Exception {
        // Content Security Policy
        // The last part sets the Content Security Policy (CSP). This is a security measure that helps prevent a range
        // of attacks, including Cross-Site Scripting (XSS) and data injection attacks. It does this by specifying which
        // domains the browser should consider to be valid sources of executable scripts. In this case, scripts
        // (script-src) and objects (object-src) are only allowed from the same origin ('self') or from a subdomain of
        // the specified host name.
        http.headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                        .policyDirectives("default-src 'self'; " +
                                "script-src 'self' *." + hostName + "; " +
                                "object-src 'self' *." + hostName + "; " +
                                "img-src 'self'; " +
                                "media-src 'self'; " +
                                "frame-src 'self'; " +
                                "font-src 'self'; " +
                                "connect-src 'self'")
                )
        );
    }

    /**
     * Customizes web security to ignore security checks for specified static resource paths.
     *
     * @return a configured {@link WebSecurityCustomizer} that ignores security for "/images/**",
     * "/js/**", and "/webjars/**" paths.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    /**
     * Handles Malicious URI Path (handles special characters and other things
     * @return
     */
    @Bean
    public StrictHttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowedHttpMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        return firewall;
    }
}

