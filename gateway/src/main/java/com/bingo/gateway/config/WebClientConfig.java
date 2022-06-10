package com.bingo.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder builder() {
        return WebClient.builder();
    }

    @Bean(name = "userService")
    public WebClient userService() {
        return builder()
                .baseUrl("http://USER-SERVICE/api")
                .build();
    }

    @Bean(name = "gamblingService")
    public WebClient gamblingService() {
        return builder()
                .baseUrl("http://GAMBLING-SERVICE/api")
                .build();
    }
}