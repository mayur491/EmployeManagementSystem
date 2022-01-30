package com.codemayur.employee_management_system.configurations;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mayur.somani
 */
@Configuration
public class HttpTraceConfig {

    /**
     * Depends on Spring Boot Actuator Dependency.<br>
     * Added property management.endpoints.web.exposure.include in application
     * properties.<br>
     * URL example: GET http://localhost:8080/actuator/httptrace<br>
     *
     * @return InMemoryHttpTraceRepository
     */
    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

}
