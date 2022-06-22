package com.bingo.outputservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private List<InvoiceElement> elements;
    private Long money;
    private Boolean isBetChainClear;
    private Boolean isMoneyClear;
}
