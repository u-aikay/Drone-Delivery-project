package com.ucheikenna.musalasoftdroneproject;

import static com.ucheikenna.musalasoftdroneproject.pojo.localDbEntry.LocalEntityDatabaseData.droneDb;
import static com.ucheikenna.musalasoftdroneproject.pojo.localDbEntry.LocalEntityDatabaseData.medicationDb;
import com.ucheikenna.musalasoftdroneproject.repositories.DroneRepository;
import com.ucheikenna.musalasoftdroneproject.repositories.MedicationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MusalaSoftDroneProjectApplication {

	@Autowired
	private DroneRepository droneRepository;
	@Autowired
	private MedicationRepository medicationRepository;

	@PostConstruct
	public void init(){
		droneRepository.saveAll(droneDb());
		medicationRepository.saveAll(medicationDb());
	}

	public static void main(String[] args) {
		SpringApplication.run(MusalaSoftDroneProjectApplication.class, args);
	}

}
