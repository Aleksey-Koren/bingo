package com.bingo.gateway.router;

import com.bingo.gateway.handler.BetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BetRouter {

    @Bean(name = "BetRouter")
    public RouterFunction<ServerResponse> route(BetHandler betHandler) {
        return RouterFunctions
                .route(POST("/bet").and(accept(APPLICATION_JSON)), betHandler::sendBet);
    }
}