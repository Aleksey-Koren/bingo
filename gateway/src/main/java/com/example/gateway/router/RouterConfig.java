package com.example.gateway.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service_route",
                        route -> route.path("/user-service/**")
                                .and()
                                .method(HttpMethod.values())
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://user-service"))
                .route("invoice_service_route",
                        route -> route.path("/invoice-service/**")
                                .and()
                                .method(HttpMethod.values())
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://invoice-service"))
                .route("gambling_service_route",
                        route -> route.path("/gambling-service/**")
                                .and()
                                .method(HttpMethod.values())
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://gambling-service"))
                .route("output_service_route",
                        route -> route.path("/output-service/**")
                                .and()
                                .method(HttpMethod.values())
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://output-service"))
                .build();
    }
}
