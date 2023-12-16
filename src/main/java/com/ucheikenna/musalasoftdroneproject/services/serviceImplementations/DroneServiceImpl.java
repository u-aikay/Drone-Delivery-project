package com.ucheikenna.musalasoftdroneproject.services.serviceImplementations;

import static com.ucheikenna.musalasoftdroneproject.utility.DefaultMessages.*;
import com.ucheikenna.musalasoftdroneproject.entities.Drone;
import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.enums.DroneStatus;
import com.ucheikenna.musalasoftdroneproject.exceptions.DroneException;
import com.ucheikenna.musalasoftdroneproject.exceptions.MedicationException;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.DroneRequest;
import com.ucheikenna.musalasoftdroneproject.pojo.responses.DroneResponse;
import com.ucheikenna.musalasoftdroneproject.repositories.DroneRepository;
import com.ucheikenna.musalasoftdroneproject.repositories.MedicationRepository;
import com.ucheikenna.musalasoftdroneproject.services.serviceInterface.DroneServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneServices {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public ResponseEntity<DroneResponse> registerDrone(DroneRequest droneDto) {

        Drone drone = Drone.builder()
                .droneModel(droneDto.getModel())
                .droneDroneStatus(DroneStatus.IDLE)
                .serialNumber(UUID.randomUUID().toString().toUpperCase())
                .droneWeight(0.0)
                .batteryPercent(100)
                .build();
        droneRepository.save(drone);
        DroneResponse droneResponseData = droneResponseMapper(drone);
        return new ResponseEntity<>(droneResponseData, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Drone> getDrone(Long id) {

        Drone drone = droneRepository.findById(id).orElseThrow(() -> new DroneException(DRONE_NOT_FOUND));
        return ResponseEntity.ok().body(drone);
    }

    @Override
    public ResponseEntity<List<Drone>> getAllDrones() {

        List<Drone> drone = (List<Drone>) droneRepository.findAll();
        if (drone.isEmpty()) throw new DroneException(DRONE_NOT_AVAILABLE);
        return ResponseEntity.ok().body(drone);
    }

    @Override
    @Transactional
    public ResponseEntity<String> loadDroneWithMedication(Long droneId, Long medicationId) {

        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new DroneException(DRONE_NOT_FOUND));
        Medication medication = medicationRepository.findById(medicationId).orElseThrow(() -> new MedicationException(MEDICATION_NOT_FOUND));

        if (medication.getDrone() != null) throw new MedicationException(MEDICATION_ALREADY_LOADED);
        droneAvailabilityCheck(drone, medication);

        droneRepository.save(drone);
        return ResponseEntity.ok().body(MEDICATION_LOADED_SUCCESSFUL);
    }

    @Override
    public ResponseEntity<List<Medication>> getDroneAssignedMedication(Long droneId) {

        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new DroneException(DRONE_NOT_FOUND));
        List<Medication> medication = drone.getMedications();

        if (medication.isEmpty()) throw new MedicationException(MEDICATION_NOT_AVAILABLE);
        return ResponseEntity.ok().body(medication);
    }

    @Override
    public ResponseEntity<List<Drone>> getAllAvailableDrones() {

        List<Drone> drones = (List<Drone>) droneRepository.findAll();
        if (drones.isEmpty()) throw new DroneException(DRONE_NOT_AVAILABLE);
        List<Drone> availableDrones = drones.stream().filter(d -> d.getDroneDroneStatus().equals(DroneStatus.IDLE)
                        || d.getDroneDroneStatus().equals(DroneStatus.LOADING))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(availableDrones);
    }

    @Override
    public ResponseEntity<String> getDroneBatteryStatus(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new DroneException(DRONE_NOT_FOUND));
        Integer batteryLevel = drone.getBatteryPercent();
        return ResponseEntity.ok().body(String.format(DRONE_BATTERY_LEVEL, batteryLevel));
    }

    private DroneResponse droneResponseMapper(Drone drone) {

        return DroneResponse.builder()
                .serialNumber(drone.getSerialNumber())
                .droneDroneStatus(drone.getDroneDroneStatus())
                .droneModel(drone.getDroneModel())
                .droneWeigh(drone.getDroneWeight())
                .batteryPercentage(drone.getBatteryPercent())
                .build();
    }

    private void droneAvailabilityCheck(Drone drone, Medication medication) {
        double capacity = drone.getDroneModel().getMaxWeight();
        double droneWeigh = drone.getDroneWeight();
        double loadAccumulator = droneWeigh + medication.getWeight();

        if (drone.getBatteryPercent() < 25) {
            throw new DroneException(DRONE_BATTERY_LOW);
        }
        if (loadAccumulator > capacity) {
            throw new DroneException(CAPACITY_EXCEEDED);
        }
        if (!(drone.getDroneDroneStatus().equals(DroneStatus.IDLE) || drone.getDroneDroneStatus().equals(DroneStatus.LOADING))) {
            throw new DroneException(DRONE_NOT_AVAILABLE);
        }

        drone.getMedications().add(medication);
        loadAccumulator += medication.getWeight();
        drone.setDroneWeight(loadAccumulator);
        medication.setDrone(drone);

        if (loadAccumulator < capacity) {
            drone.setDroneDroneStatus(DroneStatus.LOADING);
        } else {
            drone.setDroneDroneStatus(DroneStatus.LOADED);
        }
    }
}
