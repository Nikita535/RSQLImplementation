package com.example.rsqlimplementation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserServiceMessages implements Message {

    USER_ALREADY_EXISTS(-10,"error.user_already_exists");



    private final int code;
    private final String text;
}
