package com.example.rsqlimplementation.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamCreateDto {
    private String name;
    private String description;
}
