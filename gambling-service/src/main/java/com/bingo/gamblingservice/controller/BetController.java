package com.bingo.gamblingservice.controller;

import com.bingo.gamblingservice.dto.BetDto;
import com.bingo.gamblingservice.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/bet")
@RequiredArgsConstructor
public class BetController {

    private final BetService betService;

    @PostMapping
    public Mono<ResponseEntity<BetDto>> processBet(@RequestBody Mono<BetDto> dto) {

        return betService.processBet(dto).map(ResponseEntity::ok);
    }

    @PostMapping("/registration")
    public Mono<BetDto> processRegistrationBet(@RequestBody Mono<BetDto> dto) {
        return betService.processRegistrationBet(dto.log());
    }
}