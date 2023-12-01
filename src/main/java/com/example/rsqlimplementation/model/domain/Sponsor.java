package com.example.rsqlimplementation.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="event_sponsor")
public class Sponsor implements Serializable {

    @Id
    @SequenceGenerator(name = "sponsorIdSeq",sequenceName = "sponsor_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sponsorIdSeq")
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String contactNumber;
}
