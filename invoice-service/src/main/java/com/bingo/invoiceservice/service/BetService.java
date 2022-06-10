package com.bingo.invoiceservice.service;

import com.bingo.invoiceservice.dto.BetDto;
import com.bingo.invoiceservice.entity.Bet;
import com.bingo.invoiceservice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;

    @Bean
    public Consumer<BetDto> newBets() {
        return this::storeNew;
    }

    public Flux<Bet> findAllByUserId(Long userId) {
        return betRepository.findAllByUserId(userId);
    }

    public Mono<Long> retrieveUserBalance(Long userId) {
        return findAllByUserId(userId).map(Bet::getMoney).reduce(0L, Long::sum);
    }

    public Mono<Bet> retrieveLastBet(Long userId) {
        return betRepository.findAllByUserIdOrderByCreatedDesc(userId).elementAt(0);
    }

    public void storeNew(BetDto dto) {
        Bet bet = Bet.builder()
                .userId(dto.getUserId())
                .money(dto.getMoney())
                .betId(dto.getBetId())
                .previousBetId(dto.getPreviousBetId())
                .build();
        betRepository.save(bet).subscribe(System.out::println);
    }
}
