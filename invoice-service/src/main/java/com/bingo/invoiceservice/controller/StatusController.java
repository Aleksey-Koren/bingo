package com.bingo.invoiceservice.controller;

import com.bingo.invoiceservice.dto.StatusDto;
import com.bingo.invoiceservice.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;

    @GetMapping("/{userId}")
    public Mono<StatusDto> getStatus(@PathVariable("userId") Long userId) {

        return statusService.getStatus(userId);
    }
}