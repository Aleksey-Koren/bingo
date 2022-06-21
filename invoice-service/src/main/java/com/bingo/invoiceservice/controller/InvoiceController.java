package com.bingo.invoiceservice.controller;

import com.bingo.invoiceservice.dto.Invoice;
import com.bingo.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("{userId}")
    public Mono<Invoice> getInvoice(@PathVariable("userId") Long userId) {
        return invoiceService.generateInvoice(userId);
    }
}