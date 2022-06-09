package com.bingo.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    private Long userId;
    private Long balance;
    private String lastOperation;
}
