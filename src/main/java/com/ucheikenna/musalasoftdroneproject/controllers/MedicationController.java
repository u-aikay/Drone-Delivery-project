/**
 * This Controller handles api connected to Medication activities
 */

package com.ucheikenna.musalasoftdroneproject.controllers;

import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.MedicationRequest;
import com.ucheikenna.musalasoftdroneproject.services.serviceInterface.MedicationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationServices medicationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerMedication(@RequestBody MedicationRequest medicationPayload){
        return medicationService.registerMedication(medicationPayload);
    }

    @GetMapping("/single/get_medication")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Medication> getMedication(@RequestParam("med_id")Long medId){
        return medicationService.getMedication(medId);
    }

    @GetMapping("/all/get_medications")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Medication>> getAllMedications(){
        return medicationService.getAllMedications();
    }
}
