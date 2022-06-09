package com.bingo.userservice.handler;

import com.bingo.userservice.dto.StatusDto;
import com.bingo.userservice.dto.UserDto;
import com.bingo.userservice.entity.User;
import com.bingo.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        Long userId = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<User> user = userService.findById(userId);
        Mono<StatusDto> status = userService.getStatus(userId);
        Mono<UserDto> dto = Mono.zip(user, status).map(s -> UserDto.builder()
                .id(userId)
                .title(s.getT1().getTitle())
                .money(s.getT2().getBalance())
                .lastOperation(s.getT2().getLastOperation())
                .build());

        return dto.log().flatMap(s -> ServerResponse.ok().body(fromValue(s)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
