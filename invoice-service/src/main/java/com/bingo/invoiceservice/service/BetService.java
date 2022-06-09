package com.bingo.invoiceservice.service;

import com.bingo.invoiceservice.entity.Bet;
import com.bingo.invoiceservice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;

    public Flux<Bet> findAllByUserId(Long userId) {
        return betRepository.findAllByUserId(userId);
    }

    public Mono<Long> retrieveUserBalance(Long userId) {
//        Flux<Bet> bets = findAllByUserId(userId);
//        return bets.map(Bet::getMoney).reduce(0L, Long::sum);
        return findAllByUserId(userId).map(Bet::getMoney).reduce(0L, Long::sum);
    }

    public Mono<Bet> retrieveLastBet(Long userId) {
        return betRepository.findAllByUserIdOrderByCreatedDesc(userId).elementAt(0);
    }
}
