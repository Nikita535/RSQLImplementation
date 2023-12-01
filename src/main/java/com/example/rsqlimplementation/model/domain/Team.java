package com.example.rsqlimplementation.model.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "match_team")
public class Team implements Serializable {

    @Id
    @SequenceGenerator(name = "teamIdSeq",sequenceName = "team_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "teamIdSeq")
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 128)
    private String description;

    @CreatedBy
    private String createdBy;

    @Column(length = 64)
    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private Set<User> users = new HashSet<>();

}
