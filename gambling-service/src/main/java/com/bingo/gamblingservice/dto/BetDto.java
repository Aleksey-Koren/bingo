package com.bingo.gamblingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BetDto {

    private Long userId;
    private Long money;
    private String betId;
    private String previousBetId;
    private Boolean hasWon;
}
