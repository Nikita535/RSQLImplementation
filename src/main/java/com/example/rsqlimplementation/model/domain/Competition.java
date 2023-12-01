package com.example.rsqlimplementation.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "app_competition")
public class Competition implements Serializable {

    @Id
    @SequenceGenerator(name = "competitionIdSeq",sequenceName = "competition_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "competitionIdSeq")
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 128)
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startCompetition;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endCompetition;

    @Column(length = 64)
    @CreatedBy
    private String createdBy;

    @Column(length = 64)
    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;


    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private Set<Match> matches = new HashSet<>();
}
