package com.example.rsqlimplementation.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class UserShortDto {

    private Long id;
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String middleName;

    private String fullName;
    private String mobilePhoneNumber;
    private boolean active;
}
