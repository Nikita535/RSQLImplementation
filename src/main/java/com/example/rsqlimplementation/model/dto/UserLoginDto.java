package com.example.rsqlimplementation.model.dto;

import com.example.rsqlimplementation.model.domain.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UserLoginDto {

    private String username;

    private String email;

    private String password;
}
