package com.bingo.invoiceservice.dto;

import com.bingo.invoiceservice.entity.Bet;
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

    public InvoiceElement(Bet bet) {
        this.id = bet.getId();
        this.userId = bet.getUserId();
        this.money = bet.getMoney();
        this.betId = bet.getBetId();
        this.previousBetId = bet.getPreviousBetId();
        this.created = bet.getCreated();
    }
}
