package com.example.rsqlimplementation.exception;


import com.example.rsqlimplementation.exception.base.BaseRestResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserServiceExceptionHandler extends BaseRestResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    private ResponseEntity<Object> handleUserException(BadRequestException ex){
        return buildErrorResult(UserServiceMessages.USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST,ex);
    }
}
