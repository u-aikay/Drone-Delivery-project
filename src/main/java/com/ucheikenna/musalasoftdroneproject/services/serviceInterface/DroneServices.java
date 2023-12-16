package com.ucheikenna.musalasoftdroneproject.services.serviceInterface;

import com.ucheikenna.musalasoftdroneproject.entities.Drone;
import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.DroneRequest;
import com.ucheikenna.musalasoftdroneproject.pojo.responses.DroneResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DroneServices {
    ResponseEntity<DroneResponse> registerDrone(DroneRequest droneDto);

    ResponseEntity<Drone> getDrone(Long id);

    ResponseEntity<List<Drone>> getAllDrones();

    ResponseEntity<String> loadDroneWithMedication(Long droneId, Long medicationId);

    ResponseEntity<List<Medication>> getDroneAssignedMedication(Long droneId);

    ResponseEntity<List<Drone>> getAllAvailableDrones();

    ResponseEntity<String> getDroneBatteryStatus(Long droneId);

    void scheduleFixedRateTaskAsync();
}
