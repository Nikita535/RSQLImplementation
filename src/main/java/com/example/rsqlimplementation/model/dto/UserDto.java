package com.example.rsqlimplementation.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String username;
    private String password;
    private String email;

}
