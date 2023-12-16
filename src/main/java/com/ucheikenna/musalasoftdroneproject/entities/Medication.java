package com.ucheikenna.musalasoftdroneproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Medication {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;
    @Pattern(regexp="^[a-zA-Z0-9_-]$",message="only letters, numbers, ‘-‘ and ‘_' are accepted")
    private String name;
    private Double weight;
    @Pattern(regexp="^[A-Z0-9_]$",message="only upper case letters, numbers and ‘_' are accepted")
    private String code;
    private String image;
    @ManyToOne
    @JoinColumn(name = "drone_id")
    @JsonIgnore
    private Drone drone;
}
