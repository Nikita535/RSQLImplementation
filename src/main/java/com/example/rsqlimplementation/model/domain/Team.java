package com.example.rsqlimplementation.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "match_team")
public class Team {

    @Id
    @SequenceGenerator(name = "teamIdSeq",sequenceName = "team_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "teamIdSeq")
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 128)
    private String description;

    @OneToMany(mappedBy = "team")
    private Set<User> users = new HashSet<>();

}
