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
public class UserController {

    private final StatusService statusService;

    @GetMapping("/{id}")
    public Mono<StatusDto> getStatus(@PathVariable("id") Long userId) {

        return statusService.getStatus(userId);
    }
}