/**
 * This Controller handles api connected to drone activities
 */

package com.ucheikenna.musalasoftdroneproject.controllers;

import com.ucheikenna.musalasoftdroneproject.entities.Drone;
import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.DroneRequest;
import com.ucheikenna.musalasoftdroneproject.pojo.responses.DroneResponse;
import com.ucheikenna.musalasoftdroneproject.services.serviceInterface.DroneServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drone")
@RequiredArgsConstructor
public class DroneController {

    private final DroneServices droneServices;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DroneResponse> registerDrone(@RequestBody DroneRequest droneDto) {
        return droneServices.registerDrone(droneDto);
    }

    @GetMapping("/single/get_drone")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Drone> getDrones(@RequestParam("drone-id") Long id) {

        return droneServices.getDrone(id);
    }

    @GetMapping("/all/get_drones")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Drone>> getAllDrones() {

        return droneServices.getAllDrones();
    }

    @PostMapping("/load_drone")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> loadDroneWithMedication(@RequestParam("drone-id") Long droneId, @RequestParam("med-id") Long medicationId) {
        return droneServices.loadDroneWithMedication(droneId, medicationId);
    }

    @GetMapping("/get_medication")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Medication>> getMedicationItemsAssignedToADrone(@RequestParam("drone-id") Long droneId) {
        return droneServices.getDroneAssignedMedication(droneId);
    }

    @GetMapping("/available-drones-for-loading")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Drone>> getAvailableDronesForLoading() {
        return droneServices.getAllAvailableDrones();
    }

    @GetMapping("drone-battery-level")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getDroneBatteryStatus(@RequestParam("drone-id") Long droneId) {
        return droneServices.getDroneBatteryStatus(droneId);
    }
}
