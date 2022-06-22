package com.bingo.outputservice.service;

import com.bingo.outputservice.dto.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final WebClient webClient;

    public Mono<Invoice> getInvoiceByUserId(Long userId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("gateway")
                        .path("invoice-service/api/invoice/{userId}")
                        .build(userId)
                )
                .retrieve()
                .bodyToMono(Invoice.class);
    }
}
