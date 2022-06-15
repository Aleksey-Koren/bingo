package com.bingo.userservice.service;

import com.bingo.userservice.dto.BetDto;
import com.bingo.userservice.dto.StatusDto;
import com.bingo.userservice.dto.UserDto;
import com.bingo.userservice.entity.User;
import com.bingo.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WebClient webClient;

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<StatusDto> getStatus(Long userId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("gateway")
                        .path("/invoice-service/api/status/{id}")
                        .build(userId))
                .retrieve()
                .bodyToMono(StatusDto.class);
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<BetDto> createRegistrationBet(User user) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("gateway")
                        .path("/gambling-service/api/bet/registration")
                        .build())
                .body(BodyInserters.fromValue(BetDto.builder()
                                                    .userId(user.getId())
                                                    .build())
                )
                .retrieve()
                .bodyToMono(BetDto.class);
    }
}