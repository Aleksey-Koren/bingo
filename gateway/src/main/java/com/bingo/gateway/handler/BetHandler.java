package com.bingo.gateway.handler;

import com.bingo.gateway.dto.BetDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.*;

@Service
public class BetHandler {

    private final WebClient gamblingWebClient;

    public BetHandler(@Qualifier("gamblingService") WebClient gamblingWebClient) {
        this.gamblingWebClient = gamblingWebClient;
    }

    public Mono<ServerResponse> sendBet(ServerRequest serverRequest) {
        Mono<BetDto> betDtoMono = serverRequest.bodyToMono(BetDto.class);

        return gamblingWebClient.post()
                .uri("/bet")
                .body(betDtoMono, BetDto.class)
                .retrieve()
                .bodyToMono(BetDto.class)
                .flatMap(s -> ServerResponse.ok().body(fromValue(s)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }
}
