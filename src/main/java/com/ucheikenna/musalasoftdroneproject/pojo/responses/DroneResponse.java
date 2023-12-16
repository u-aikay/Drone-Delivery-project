package com.ucheikenna.musalasoftdroneproject.pojo.responses;

import com.ucheikenna.musalasoftdroneproject.enums.Model;
import com.ucheikenna.musalasoftdroneproject.enums.DroneStatus;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneResponse {

    private Model droneModel;
    private DroneStatus droneDroneStatus;
    private String serialNumber;
    private Double droneWeigh;
    private Integer batteryPercentage;
}
