package com.bingo.userservice.config;

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

    @Bean(name = "invoiceService")
    public WebClient invoiceClient() {
       return builder()
               .baseUrl("http://INVOICE-SERVICE")
               .build();
    }
}
