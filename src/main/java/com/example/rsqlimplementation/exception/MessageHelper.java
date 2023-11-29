package com.example.rsqlimplementation.exception;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {
    private final MessageSource messageSource;

    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(id, (Object[]) null, locale);
    }

    public String getMessageWithParams(String id, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(id, params, locale);
    }

    public MessageHelper(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}