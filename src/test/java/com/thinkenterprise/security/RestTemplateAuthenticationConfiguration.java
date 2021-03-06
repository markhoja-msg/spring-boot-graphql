package com.thinkenterprise.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateAuthenticationConfiguration {
	 
    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder().basicAuthentication("user", "password");
    }
    
}

