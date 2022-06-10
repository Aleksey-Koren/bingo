package com.bingo.gamblingservice.service;

import com.bingo.gamblingservice.dto.BetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BetService {

    private final StreamBridge streamBridge;

    public Mono<BetDto> processBet(Mono<BetDto> betDto) {
        return betDto.map(s -> {
            s.setBetId(UUID.randomUUID().toString());
            s.setHasWon((Math.random() > 0.51));
            calculateMoney(s);
            sendToInvoiceService(s);
            return s;
        });
    }

    private void calculateMoney(BetDto dto) {
        if(dto.getHasWon()) {
            dto.setMoney(dto.getMoney() * 2);
        } else {
            dto.setMoney(dto.getMoney() * -1);
        }
    }

    private void sendToInvoiceService(BetDto dto) {
        streamBridge.send("invoice-service", dto);
    }
}
