package com.codemayur.employee_management_system.configurations;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author mayur.somani
 */
@Configuration
public class ConfigurationClass {

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

    /**
     * @return Docket
     */
    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.codemayur.employee_management_system"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Employee Management System",
                "APIs for Employee Management System",
                "1.0",
                "https://codemayur.yolasite.com/en/",
                new Contact("Mayur Somani", "https://codemayur.yolasite.com/en/", "mayur491@gmail.com"),
                "API License",
                "https://codemayur.yolasite.com/en/",
                Collections.emptyList());
    }

}
