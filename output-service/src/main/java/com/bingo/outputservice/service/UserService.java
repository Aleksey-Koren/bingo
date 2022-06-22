package com.bingo.outputservice.service;

import com.bingo.outputservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final WebClient webClient;

    public Mono<UserDto> getById(Long userId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("gateway")
                        .path("/user-service/api/users/{id}/title")
                        .build(userId))
                .retrieve()
                .bodyToMono(UserDto.class);
    }
 }
