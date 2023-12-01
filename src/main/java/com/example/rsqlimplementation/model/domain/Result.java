package com.example.rsqlimplementation.model.domain;


import com.example.rsqlimplementation.model.type.MatchResult;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "match_result")
public class Result implements Serializable {

    @Id
    @SequenceGenerator(name = "resultIdSeq",sequenceName = "result_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "resultIdSeq")
    private Long id;

    @Column(length = 128)
    private String description;

    private MatchResult result;

    private Long teamId;

    @OneToOne
    @JoinColumn(name = "match_id", unique = true)
    private Match match;
}
