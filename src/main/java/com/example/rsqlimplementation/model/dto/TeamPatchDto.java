package com.example.rsqlimplementation.model.dto;

import com.example.rsqlimplementation.model.domain.User;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamPatchDto {
    private String name;

    private String description;

    private Set<Long> usersId;
}
