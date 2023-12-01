package com.example.rsqlimplementation.model.dto;

import com.example.rsqlimplementation.model.domain.User;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamDto {
    private Long id;

    private String name;

    private String description;

    private Set<UserDto> users;
}
