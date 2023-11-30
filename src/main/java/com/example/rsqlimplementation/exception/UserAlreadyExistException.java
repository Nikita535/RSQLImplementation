package com.example.rsqlimplementation.exception;

import com.example.rsqlimplementation.exception.base.BaseException;

public class UserAlreadyExistException extends BaseException {
    public UserAlreadyExistException() {

    }

    public UserAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(Message msg, Object... params) {
        super(msg, params);
    }
}