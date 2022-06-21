package com.bingo.invoiceservice.service;

import com.bingo.invoiceservice.dto.Invoice;
import com.bingo.invoiceservice.dto.InvoiceElement;
import com.bingo.invoiceservice.entity.Bet;
import com.bingo.invoiceservice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final BetRepository betRepository;

   public Mono<Invoice> generateInvoice(Long userId) {
       return validateBetsToInvoice(betRepository.findAllByUserIdOrderByCreatedAsc(userId));
   }

    private Mono<Invoice> validateBetsToInvoice(Flux<Bet> bets) {
       Mono<List<Bet>> betsList = bets.collectList();
       return betsList.map(s -> {
            boolean isBetChainClear = true;
            boolean isMoneyClear = true;
            long money = 0;
            List<InvoiceElement> elements = new ArrayList<>();
            for(int i = 0; i < s.size(); i++) {
                StringBuilder errorDetails = new StringBuilder();
                InvoiceElement el = new InvoiceElement(s.get(i));
                if(i != 0) {
                    el.setIsBetChainClear(s.get(i).getPreviousBetId().equals(s.get(i - 1).getBetId()));
                    if (!el.getIsBetChainClear()) {
                        isBetChainClear = false;
                        errorDetails.append("| Bet chain is interrupted |");
                    }
                    if(isMoneyClear) {
                        el.setIsMoneyClear(el.getMoney() <= money);
                        if(!el.getIsMoneyClear()) {
                            isMoneyClear = false;
                            errorDetails.append("| Bet money are impossible |");
                        }
                    }else {
                        el.setIsMoneyClear(false);
                        errorDetails.append("| Money could be impossible. Errors in previous bets");
                    }
                    el.setErrorDetails(errorDetails.toString());
                }else{
                    el.setIsBetChainClear(true);
                    el.setIsMoneyClear(true);
                    el.setErrorDetails("");
                }
                money = money + el.getMoney();
                elements.add(el);
            }
            return new Invoice(elements, money, isBetChainClear, isMoneyClear);
       });
    }
}