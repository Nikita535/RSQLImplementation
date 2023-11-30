package com.example.rsqlimplementation.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPatchDto {
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String middleName;

    private String mobilePhoneNumber;
    private String password;
}
