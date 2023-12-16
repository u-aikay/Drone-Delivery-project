package com.ucheikenna.musalasoftdroneproject.repositories;

import com.ucheikenna.musalasoftdroneproject.entities.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone,Long> {
}
