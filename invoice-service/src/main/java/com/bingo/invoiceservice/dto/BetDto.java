package com.bingo.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BetDto {

    private Long userId;
    private Long money;
    private String betId;
    private String previousBetId;
    private Boolean hasWon;
}
