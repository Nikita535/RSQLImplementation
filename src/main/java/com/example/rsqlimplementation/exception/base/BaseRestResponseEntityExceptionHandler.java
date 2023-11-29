package com.example.rsqlimplementation.exception.base;

import java.net.URISyntaxException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import com.example.rsqlimplementation.exception.BadRequestException;
import com.example.rsqlimplementation.exception.Message;
import com.example.rsqlimplementation.exception.MessageHelper;
import com.example.rsqlimplementation.model.dto.Result;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.LazyInitializationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class BaseRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(BaseRestResponseEntityExceptionHandler.class);

    @Autowired
    private MessageHelper messageHelper;
    @Value("${app.rest.response.exception-stacktrace:false}")
    protected boolean returnStacktrace;

    public BaseRestResponseEntityExceptionHandler() {
    }

    protected ResponseEntity<Object> buildErrorResult(Message message, HttpStatus status, Exception ex, Object... params) {
        if (ex != null) {
            log.error(ex.getMessage(), ex);
        }

        if (message == null) {
            return new ResponseEntity(Result.error(-1, (String)Optional.ofNullable(ex).map(Throwable::getMessage).orElse(null), this.returnStacktrace ? ex : null), status);
        } else {
            String text = params != null && params.length != 0 ? this.messageHelper.getMessageWithParams(message.getText(), params) : this.messageHelper.getMessage(message.getText());
            return new ResponseEntity(Result.error(message.getCode(), text, this.returnStacktrace ? ex : null), status);
        }
    }

    protected MessageHelper getMessageHelper() {
        return this.messageHelper;
    }
}