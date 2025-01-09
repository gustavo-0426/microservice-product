package com.co.softworld.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class Swagger {

    @Bean
    public GroupedOpenApi doc() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/microservices/**")
                .build();
    }
}
