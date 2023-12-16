package com.ucheikenna.musalasoftdroneproject.services.serviceImplementations;

import static com.ucheikenna.musalasoftdroneproject.utility.DefaultMessages.*;
import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.exceptions.MedicationException;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.MedicationRequest;
import com.ucheikenna.musalasoftdroneproject.repositories.MedicationRepository;
import com.ucheikenna.musalasoftdroneproject.services.serviceInterface.MedicationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationServices {

    private final MedicationRepository medicationRepository;


    @Override
    public ResponseEntity<String> registerMedication(MedicationRequest medicationPayload) {

        Medication medication = Medication.builder()
                .code(UUID.randomUUID().toString())
                .image(medicationPayload.getImage())
                .weight(medicationPayload.getWeight())
                .name(medicationPayload.getName())
                .build();
        medicationRepository.save(medication);

        return ResponseEntity.ok().body(MEDICATION_CREATED_SUCCESS);
    }

    @Override
    public ResponseEntity<Medication> getMedication(Long medId) {

        Medication medication = medicationRepository.findById(medId).orElseThrow(()-> new MedicationException(MEDICATION_NOT_FOUND));

        return ResponseEntity.ok().body(medication);
    }

    @Override
    public ResponseEntity<List<Medication>> getAllMedications() {

        List<Medication> medList = (List<Medication>) medicationRepository.findAll();
        if (medList.isEmpty()) throw new MedicationException(MEDICATION_NOT_AVAILABLE);

        return ResponseEntity.ok().body(medList);
    }
}
