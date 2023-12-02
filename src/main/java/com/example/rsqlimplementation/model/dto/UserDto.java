package com.example.rsqlimplementation.model.dto;

import com.example.rsqlimplementation.model.domain.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String middleName;

    private String fullName;

    private LocalDateTime registrationDate;

    private LocalDateTime updatedDate;

    private String mobilePhoneNumber;
    private boolean active;

    private String teamId;
}
