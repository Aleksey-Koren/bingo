package com.bingo.outputservice.router;

import com.bingo.outputservice.handler.PDFHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(PDFHandler pdfHandler) {
        return RouterFunctions
                .route(GET("/api/invoice/{userId}").and(accept(APPLICATION_JSON)), pdfHandler::findById);
    }
}
