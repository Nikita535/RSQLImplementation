package com.example.rsqlimplementation.exception;

import com.example.rsqlimplementation.exception.Message;
import com.example.rsqlimplementation.exception.base.BaseException;

public class BadRequestException extends BaseException {
    public BadRequestException() {

    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Message msg, Object... params) {
        super(msg, params);
    }
}