package com.example.rsqlimplementation.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotNull(message = "Username cannot be blank")
    @Size(min = 4, message = "Username must be at least 4 characters long")
    private String username;
    @NotNull(message = "Password cannot be blank")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    private String email;
}

