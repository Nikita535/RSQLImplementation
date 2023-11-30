package com.example.rsqlimplementation.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MatchStatus {
    PLANNED("Запланирован"),
    PROCESS("В процессе"),
    ENDED("Закончен");

    private final String title;
}
