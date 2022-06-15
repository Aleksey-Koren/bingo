package com.bingo.gamblingservice.service;

import com.bingo.gamblingservice.dto.BetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BetService {

    @Value("${app.registration-money}")
    private String registrationMoney;

    private final StreamBridge streamBridge;

    public Mono<BetDto> processBet(Mono<BetDto> betDto) {
        return betDto.map(s -> {
            s.setBetId(UUID.randomUUID().toString());
            s.setHasWon((Math.random() > 0.51));
            s.setMoney(calculateMoney(s));
            sendToInvoiceService(s);
            return s;
        });
    }

    public Mono<BetDto> processRegistrationBet(Mono<BetDto> betDto) {
       return betDto.map(s -> {
                    s.setBetId(UUID.randomUUID().toString());
                    s.setHasWon(true);
                    s.setMoney(Long.valueOf(registrationMoney));
                    sendToInvoiceService(s);
                    return s;
                }
        );
    }

    private Long calculateMoney(BetDto dto) {
        if(dto.getHasWon()) {
            return dto.getMoney();
        } else {
            return dto.getMoney() * -1;
        }
    }

    private void sendToInvoiceService(BetDto dto) {
        streamBridge.send("invoice-service", dto);
    }
}
