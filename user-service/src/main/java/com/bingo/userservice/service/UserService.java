package com.bingo.userservice.service;

import com.bingo.userservice.dto.StatusDto;
import com.bingo.userservice.entity.User;
import com.bingo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WebClient invoiceServiceClient;

    @Autowired
    public UserService(UserRepository userRepository, @Qualifier("invoiceService") WebClient webClientPool) {
        this.userRepository = userRepository;
        this.invoiceServiceClient = webClientPool;
    }

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<StatusDto> getStatus(Long userId) {
        return invoiceServiceClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/status/{id}")
                        .build(userId))
                .retrieve()
                .bodyToMono(StatusDto.class);
    }

    public User save(User user) {
        return null;
    }

    public void deleteById(Long id) {

    }
}