package com.bingo.gateway.handler;

import com.bingo.gateway.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.*;

@Service
public class UserHandler {

    private final WebClient userWebClient;

    public UserHandler(@Qualifier("userService") WebClient userWebClient) {
        this.userWebClient = userWebClient;
    }

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        Long userId = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<UserDto> dto = userWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{id}").build(userId)).retrieve()
                .bodyToMono(UserDto.class);
        return dto.flatMap(s -> ServerResponse.ok().body(fromValue(s)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}