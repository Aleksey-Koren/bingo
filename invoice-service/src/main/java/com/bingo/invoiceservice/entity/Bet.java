package com.bingo.invoiceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Bet {

    @Id
    private Long id;
    private Long userId;
    private Long money;
    private String betId;
    private String previousBetId;
    private Instant created;
}