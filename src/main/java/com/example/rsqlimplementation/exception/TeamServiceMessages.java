package com.example.rsqlimplementation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamServiceMessages implements Message{

    TEAM_NOT_FOUND(-13,"error.team_not_found");

    private final int code;
    private final String text;
}
