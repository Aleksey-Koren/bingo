package com.bingo.invoiceservice.repository;

import com.bingo.invoiceservice.entity.Bet;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BetRepository extends ReactiveSortingRepository<Bet, Long>  {

    Flux<Bet> findAllByUserId(Long userId);

    Flux<Bet> findAllByUserIdOrderByCreatedDesc(Long userId);
}