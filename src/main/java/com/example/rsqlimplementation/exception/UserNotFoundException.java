package com.example.rsqlimplementation.exception;

import com.example.rsqlimplementation.exception.base.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {

    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Message msg, Object... params) {
        super(msg, params);
    }
}