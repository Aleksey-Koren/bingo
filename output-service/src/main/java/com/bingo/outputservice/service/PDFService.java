package com.bingo.outputservice.service;

import com.bingo.outputservice.dto.Invoice;
import com.bingo.outputservice.dto.UserDto;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PDFService {

    private final TemplateEngine templateEngine;

    public byte[] generatePdfInvoice(UserDto user, Invoice invoice) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("elements", invoice.getElements());
        String user_invoice = templateEngine.process("user_invoice", context);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(user_invoice);
        renderer.layout();
        try {
            renderer.createPDF(outputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}