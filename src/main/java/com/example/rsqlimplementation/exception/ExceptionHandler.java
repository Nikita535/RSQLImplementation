package com.example.rsqlimplementation.exception;


import com.example.rsqlimplementation.exception.base.BaseException;
import com.example.rsqlimplementation.model.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageHelper messageHelper;
    @Value("${app.rest.response.exception-stacktrace:false}")
    protected boolean returnStacktrace;
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler({UserAlreadyExistException.class,UserNotFoundException.class,TeamNotFoundException.class})
    private ResponseEntity<Object> handleUserException(BaseException ex){
        if(ex instanceof UserAlreadyExistException){
            return buildErrorResult(UserServiceMessages.USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST,ex);
        }else if(ex instanceof UserNotFoundException){
            return buildErrorResult(UserServiceMessages.USER_NOT_FOUND, HttpStatus.BAD_REQUEST,ex);
        }else if(ex instanceof TeamNotFoundException){
            return buildErrorResult(TeamServiceMessages.TEAM_NOT_FOUND, HttpStatus.BAD_REQUEST,ex);
        }
        return buildErrorResult(UserServiceMessages.USER_NOT_FOUND, HttpStatus.BAD_REQUEST,ex);
    }

    protected ResponseEntity<Object> buildErrorResult(Message message, HttpStatus status, Exception ex, Object... params) {
        if (ex != null) {
            log.error(ex.getMessage(), ex);
        }

        if (message == null) {
            return new ResponseEntity(Result.error(-1, (String) Optional.ofNullable(ex).map(Throwable::getMessage).orElse(null), this.returnStacktrace ? ex : null), status);
        } else {
            String text = params != null && params.length != 0 ? this.messageHelper.getMessageWithParams(message.getText(), params) : this.messageHelper.getMessage(message.getText());
            return new ResponseEntity(Result.error(message.getCode(), text, this.returnStacktrace ? ex : null), status);
        }
    }

    protected MessageHelper getMessageHelper() {
        return this.messageHelper;
    }
}
