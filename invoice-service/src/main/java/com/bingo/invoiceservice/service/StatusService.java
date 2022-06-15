package com.bingo.invoiceservice.service;

import com.bingo.invoiceservice.dto.StatusDto;
import com.bingo.invoiceservice.entity.Bet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final BetService betService;

    public Mono<StatusDto> getStatus(Long userId) {
        Mono<Long> balance = betService.retrieveUserBalance(userId);
        Mono<Bet> lastBet = betService.retrieveLastBet(userId);

        return Mono.zip(balance, lastBet)
                .map(s -> StatusDto.builder()
                        .userId(userId)
                        .balance(s.getT1())
                        .lastOperation(s.getT2().getBetId())
                        .build()
                ).onErrorReturn(StatusDto.builder()
                        .userId(userId)
                        .balance(null)
                        .lastOperation(null)
                        .build());
    }
}
