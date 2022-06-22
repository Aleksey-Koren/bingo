package com.bingo.outputservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class InvoiceElement {

    private Long id;
    private Long userId;
    private Long money;
    private String betId;
    private String previousBetId;
    private Instant created;
    private Boolean isBetChainClear;
    private Boolean isMoneyClear;
    private String errorDetails;
}