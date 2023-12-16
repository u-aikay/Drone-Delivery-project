package com.ucheikenna.musalasoftdroneproject.serviceImpl;

import com.ucheikenna.musalasoftdroneproject.entities.Drone;
import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.enums.DroneStatus;
import com.ucheikenna.musalasoftdroneproject.enums.Model;
import com.ucheikenna.musalasoftdroneproject.exceptions.MedicationException;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.MedicationRequest;
import com.ucheikenna.musalasoftdroneproject.repositories.MedicationRepository;
import com.ucheikenna.musalasoftdroneproject.services.serviceImplementations.MedicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MedicationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MedicationServiceImplTest {

    @MockBean
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationServiceImpl medicationServiceImp;

    private Medication medication;

    @BeforeEach
    void setUp() {

        Drone drone = new Drone();
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("42-THU-SUS-94II4JJ-35-23");

        Medication medication = new Medication();
        medication.setCode("FGYH-1234");
        medication.setDrone(drone);
        medication.setImage("http://image.png");
        medication.setMedicationId(1L);
        medication.setName("Paracetamol");
        medication.setWeight(20.0d);

    }

    @Test
    void testToRegisterMedication() {
        when(medicationRepository.save(any())).thenReturn(medication);
        ResponseEntity<String> actualRegisterMedicationResult = medicationServiceImp
                .registerMedication(new MedicationRequest());
        assertEquals("medication added to stock successfully.", actualRegisterMedicationResult.getBody());
        assertEquals(HttpStatus.OK, actualRegisterMedicationResult.getStatusCode());
        assertTrue(actualRegisterMedicationResult.getHeaders().isEmpty());
        verify(medicationRepository).save(any());
        when(medicationRepository.save(any())).thenThrow(new MedicationException("An error occurred"));
        assertThrows(MedicationException.class,
                () -> medicationServiceImp.registerMedication(new MedicationRequest()));
    }

    @Test
    void testToGetMedication() {

        Drone drone = new Drone();
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("42-THU-SUS-94II4JJ-35-23");

        Medication medication = new Medication();
        medication.setCode("FGYH-1234");
        medication.setDrone(drone);
        medication.setImage("http://image.png");
        medication.setMedicationId(1L);
        medication.setName("Paracetamol");
        medication.setWeight(20.0d);

        Optional<Medication> ofResult = Optional.of(medication);
        when(medicationRepository.findById(any())).thenReturn(ofResult);
        ResponseEntity<Medication> actualMedication = medicationServiceImp.getMedication(1L);
        assertTrue(actualMedication.hasBody());
        assertTrue(actualMedication.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualMedication.getStatusCode());
        verify(medicationRepository).findById(any());

    }

    @Test
    void testToGetAllMedications() {

        ArrayList<Medication> medicationList = new ArrayList<>();
        medicationList.add(medication);
        when(medicationRepository.findAll()).thenReturn(medicationList);
        ResponseEntity<List<Medication>> actualAllMedications = medicationServiceImp.getAllMedications();
        assertTrue(actualAllMedications.hasBody());
        assertEquals(HttpStatus.OK, actualAllMedications.getStatusCode());
        assertTrue(actualAllMedications.getHeaders().isEmpty());
        verify(medicationRepository).findAll();
    }
}