package com.bingo.userservice.router;

import com.bingo.userservice.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler userHandler) {
        return RouterFunctions
                .route(GET("/api/users/{id}").and(accept(APPLICATION_JSON)), userHandler::findById)
                .andRoute(POST("/api/register").and(accept(APPLICATION_JSON)), userHandler::register)
                .andRoute(GET("/api/users/{id}/title").and(accept(APPLICATION_JSON)), userHandler::findTitleById);
    }

}