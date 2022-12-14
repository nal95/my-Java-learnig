package com.nal95.clinic.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@AllArgsConstructor
@Service
public class MailContentBuilder {
    private final TemplateEngine templateEngine;
    public String build(String message, String link) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("link", link);
        return templateEngine.process("index", context);
    }
}
