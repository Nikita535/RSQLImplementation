package com.example.rsqlimplementation.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_event")
public class Event {

    @Id
    @SequenceGenerator(name = "eventIdSeq",sequenceName = "event_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "eventIdSeq")
    private Long id;

    @Column(length = 128)
    private String description;

    @Column(length = 64)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startEvent;

    @Column(length = 128)
    private String location;

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

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Competition> competitions = new HashSet<>();
}
