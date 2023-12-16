package com.ucheikenna.musalasoftdroneproject.services.serviceInterface;

import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.MedicationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicationServices {
    ResponseEntity<String> registerMedication(MedicationRequest medicationPayload);

    ResponseEntity<Medication> getMedication(Long medId);

    ResponseEntity<List<Medication>> getAllMedications();
}
