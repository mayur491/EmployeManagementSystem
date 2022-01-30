package com.codemayur.employee_management_system.configurations;

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
public class SwaggerConfig {

    public static final String WEBSITE_URL = "https://codemayur.yolasite.com/en/";

    /**
     * @return Docket
     */
    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
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
                WEBSITE_URL,
                new Contact("Mayur Somani", WEBSITE_URL, "mayur491@gmail.com"),
                "API License",
                WEBSITE_URL,
                Collections.emptyList());
    }

}
