package com.co.softworld.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Value("${api.version}")
    private String apiVersion;

    @Bean
    public GroupedOpenApi doc() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/" + apiVersion + "/microservice/**")
                .build();
    }
}
