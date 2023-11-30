package com.example.rsqlimplementation.model.dto;

import com.example.rsqlimplementation.model.type.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCreateDto {
    private String username;
    private String password;
    private String email;
    private Role role;
}
