package com.ucheikenna.musalasoftdroneproject.repositories;

import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication,Long> {
}
