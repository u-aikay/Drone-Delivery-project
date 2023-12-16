package com.ucheikenna.musalasoftdroneproject.serviceImpl;

import com.ucheikenna.musalasoftdroneproject.entities.Drone;
import com.ucheikenna.musalasoftdroneproject.enums.DroneStatus;
import com.ucheikenna.musalasoftdroneproject.enums.Model;
import com.ucheikenna.musalasoftdroneproject.exceptions.DroneException;
import com.ucheikenna.musalasoftdroneproject.exceptions.MedicationException;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.DroneRequest;
import com.ucheikenna.musalasoftdroneproject.pojo.responses.DroneResponse;
import com.ucheikenna.musalasoftdroneproject.repositories.DroneRepository;
import com.ucheikenna.musalasoftdroneproject.repositories.MedicationRepository;
import com.ucheikenna.musalasoftdroneproject.services.serviceImplementations.DroneServiceImpl;
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
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {DroneServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DroneServiceImplTest {

    @MockBean
    private DroneRepository droneRepository;

    @Autowired
    private DroneServiceImpl droneServiceImp;

    @MockBean
    private MedicationRepository medicationRepository;

    private Drone drone;

    @BeforeEach
    public void setUp(){
        Drone drone = new Drone();
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("DR-2011");
    }


    @Test
    void testToRegisterADrone() {
        when(droneRepository.save(any())).thenReturn(drone);
        ResponseEntity<DroneResponse> actualRegisterDroneResult = droneServiceImp
                .registerDrone(new DroneRequest(Model.LIGHT_WEIGHT));
        assertTrue(actualRegisterDroneResult.hasBody());
        assertTrue(actualRegisterDroneResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualRegisterDroneResult.getStatusCode());
        DroneResponse body = actualRegisterDroneResult.getBody();
        assertEquals(Model.LIGHT_WEIGHT, body.getDroneModel());
        assertEquals(100, body.getBatteryPercentage().intValue());
        assertEquals(DroneStatus.IDLE, body.getDroneDroneStatus());
        assertEquals(0.0d, body.getDroneWeigh().doubleValue());
        verify(droneRepository).save(any());
    }

    @Test
    void testToFetchADroneWithAnId() {
        Drone drone = new Drone();
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("42-THU-SUS-94II4JJ-35-23");
        Optional<Drone> droneOptional = Optional.of(drone);

        when(droneRepository.findById(any())).thenReturn(droneOptional);
        ResponseEntity<Drone> actualDrone = droneServiceImp.getDrone(drone.getDroneId());
        assertTrue(actualDrone.hasBody());
        assertTrue(actualDrone.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualDrone.getStatusCode());
        verify(droneRepository).findById(any());
        when(droneRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(DroneException.class, () -> droneServiceImp.getDrone(1L));
    }

    @Test
    void testToGetAllDrones() {

        ArrayList<Drone> droneList = new ArrayList<>();
        droneList.add(drone);
        when(droneRepository.findAll()).thenReturn(droneList);
        ResponseEntity<List<Drone>> actualAllDrones = droneServiceImp.getAllDrones();
        assertTrue(actualAllDrones.hasBody());
        assertEquals(HttpStatus.OK, actualAllDrones.getStatusCode());
        assertTrue(actualAllDrones.getHeaders().isEmpty());
        verify(droneRepository).findAll();

    }

    @Test
    void testToLoadADroneWithMedicationItems() {

        Drone drone = new Drone();
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("42-THU-SUS-94II4JJ-35-23");
        Optional<Drone> droneOptional = Optional.of(drone);

        when(droneRepository.findById(any())).thenReturn(droneOptional);
        when(medicationRepository.findById(any())).thenThrow(new DroneException("An error occurred"));
        assertThrows(DroneException.class, () -> droneServiceImp.loadDroneWithMedication(1L, 1L));
        verify(droneRepository).findById(any());
        verify(medicationRepository).findById(any());
    }

    @Test
    void testToGetMedicationItemsOnADrone() {

        Drone drone = mock(Drone.class);
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("42-THU-SUS-94II4JJ-35-23");
        Optional<Drone> droneOptional = Optional.of(drone);

        when(droneRepository.findById(any())).thenReturn(droneOptional);
        assertThrows(MedicationException.class, () -> droneServiceImp.getDroneAssignedMedication(1L));
        verify(droneRepository).findById(any());
        verify(droneRepository).findById(any());
        verify(drone).getMedications();
        verify(drone).setBatteryPercent(any());
        verify(drone).setDroneId(any());
        verify(drone).setDroneModel(any());
        verify(drone).setDroneDroneStatus(any());
        verify(drone).setDroneWeight(any());
        verify(drone).setMedications(any());
        verify(drone).setSerialNumber(any());
    }

    @Test
    void testToGetAllAvailableDronesForLoading() {

        Drone drone = new Drone();
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("42-THU-SUS-94II4JJ-35-23");
        ArrayList<Drone> droneList = new ArrayList<>();
        droneList.add(drone);

        when(droneRepository.findAll()).thenReturn(droneList);
        ResponseEntity<List<Drone>> actualAvailableDronesForLoading = droneServiceImp.getAllAvailableDrones();
        assertEquals(droneList, actualAvailableDronesForLoading.getBody());
        assertEquals(HttpStatus.OK, actualAvailableDronesForLoading.getStatusCode());
        assertTrue(actualAvailableDronesForLoading.getHeaders().isEmpty());
        verify(droneRepository).findAll();
    }

    @Test
    void testToDisplaysDroneBatteryLevel() {

        Drone drone = new Drone();
        drone.setBatteryPercent(100);
        drone.setDroneId(1L);
        drone.setDroneModel(Model.LIGHT_WEIGHT);
        drone.setDroneDroneStatus(DroneStatus.IDLE);
        drone.setDroneWeight(100.0);
        drone.setMedications(new ArrayList<>());
        drone.setSerialNumber("42-THU-SUS-94II4JJ-35-23");
        Optional<Drone> droneOptional = Optional.ofNullable(drone);

        when(droneRepository.findById((Long) any())).thenReturn(droneOptional);
        ResponseEntity<String> actualDroneBatteryLevel = droneServiceImp.getDroneBatteryStatus(1L);
        assertEquals("Drone battery level is 100 percent", actualDroneBatteryLevel.getBody());
        assertEquals(HttpStatus.OK, actualDroneBatteryLevel.getStatusCode());
        assertTrue(actualDroneBatteryLevel.getHeaders().isEmpty());
        verify(droneRepository).findById(any());
    }
}