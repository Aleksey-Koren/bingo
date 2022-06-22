package com.bingo.userservice.handler;

import com.bingo.userservice.dto.StatusDto;
import com.bingo.userservice.dto.UserDto;
import com.bingo.userservice.entity.User;
import com.bingo.userservice.mapper.UserMapper;
import com.bingo.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;
    private final UserMapper userMapper;

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        Long userId = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<User> user = userService.findById(userId);
        Mono<StatusDto> status = userService.getStatus(userId);
        return Mono.zip(user, status).map(s -> UserDto.builder()
                .id(userId)
                .title(s.getT1().getTitle())
                .money(s.getT2().getBalance())
                .lastOperation(s.getT2().getLastOperation())
                .build())
                .flatMap(s -> ServerResponse.ok().body(fromValue(s)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findTitleById(ServerRequest serverRequest) {
        Long userId = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<UserDto> userDto = userService.findById(userId).map(userMapper::toDto);
        return  userDto.flatMap(userDtoResp -> ServerResponse.ok().body(fromValue(userDtoResp)))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build());
    }

    public Mono<ServerResponse> register(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(UserDto.class)
                .flatMap(userDto -> userService.save(userMapper.toEntity(userDto)))
                .zipWhen(userService::createRegistrationBet)
                .map(t -> UserDto.builder()
                        .id(t.getT1().getId())
                        .title(t.getT1().getTitle())
                        .money(t.getT2().getMoney())
                        .lastOperation(t.getT2().getBetId())
                        .build())
                .flatMap(response -> ServerResponse.ok().body(fromValue(response)))
                .switchIfEmpty(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
