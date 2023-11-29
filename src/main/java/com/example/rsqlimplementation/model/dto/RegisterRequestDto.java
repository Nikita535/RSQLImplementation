package com.example.rsqlimplementation.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private String email;
}

