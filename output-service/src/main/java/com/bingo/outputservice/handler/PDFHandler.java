package com.bingo.outputservice.handler;

import com.bingo.outputservice.dto.Invoice;
import com.bingo.outputservice.dto.UserDto;
import com.bingo.outputservice.service.InvoiceService;
import com.bingo.outputservice.service.PDFService;
import com.bingo.outputservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Service
@RequiredArgsConstructor
public class PDFHandler {

    private final UserService userService;
    private final InvoiceService invoiceService;
    private final PDFService pdfService;

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        Long userId = Long.parseLong(serverRequest.pathVariable("userId"));
        Mono<UserDto> user = userService.getById(userId);
        Mono<Invoice> invoice = invoiceService.getInvoiceByUserId(userId);
        Mono<byte[]> response = Mono.zip(user, invoice).map(s -> pdfService.generatePdfInvoice(s.getT1(), s.getT2()));
        return response.flatMap(byteArray -> ServerResponse.ok().body(fromValue(byteArray)))
                .switchIfEmpty(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}