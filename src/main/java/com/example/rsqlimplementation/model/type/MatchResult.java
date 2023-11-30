package com.example.rsqlimplementation.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MatchResult {

    WINNER("Победитель"),
    LOSER("Проигравший"),
    DRAW("Ничья");

    private final String title;
}
