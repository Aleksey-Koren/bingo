package com.bingo.invoiceservice.controller;

import com.bingo.invoiceservice.dto.BetDto;
import com.bingo.invoiceservice.dto.StatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bet")
public class BetController {

    @PostMapping()
    public Mono<StatusDto> createRegistrationBet(@RequestBody BetDto dto) {
        return null;
    }
}
