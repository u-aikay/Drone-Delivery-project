package com.ucheikenna.musalasoftdroneproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucheikenna.musalasoftdroneproject.enums.Model;
import com.ucheikenna.musalasoftdroneproject.enums.DroneStatus;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Drone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long droneId;
    @Size(min = 6, max = 100)
    @NotNull(message = "serial number can't be empty")
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private Model droneModel;
    @Size(max = 500, message = "max weight exceeded")
    private Double droneWeight;
    private Integer batteryPercent;
    @Enumerated(EnumType.STRING)
    private DroneStatus droneDroneStatus = DroneStatus.IDLE;
    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    List<Medication> medications;
}
