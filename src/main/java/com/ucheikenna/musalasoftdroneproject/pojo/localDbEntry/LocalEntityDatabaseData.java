package com.ucheikenna.musalasoftdroneproject.pojo.localDbEntry;

import com.ucheikenna.musalasoftdroneproject.entities.Drone;
import com.ucheikenna.musalasoftdroneproject.entities.Medication;
import com.ucheikenna.musalasoftdroneproject.enums.DroneStatus;
import com.ucheikenna.musalasoftdroneproject.enums.Model;
import java.util.ArrayList;
import java.util.List;

public class LocalEntityDatabaseData {

    public static List<Drone> droneDb() {

        List<Drone> drones = new ArrayList<>();
        drones.add(new Drone(1L, "DR-1011", Model.CRUISER_WEIGHT, 0.0, 100, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(2L, "DR-1022", Model.LIGHT_WEIGHT, 0.0, 80, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(3L, "DR-1033", Model.HEAVY_WEIGHT, 0.0, 35, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(4L, "DR-1044", Model.CRUISER_WEIGHT, 0.0, 17, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(5L, "DR-1055", Model.CRUISER_WEIGHT, 0.0, 20, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(6L, "DR-1066", Model.LIGHT_WEIGHT, 0.0, 55, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(7L, "DR-1077", Model.HEAVY_WEIGHT, 0.0, 5, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(8L, "DR-1088", Model.LIGHT_WEIGHT, 0.0, 90, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(9L, "DR-1099", Model.MIDDLE_WEIGHT, 0.0, 100, DroneStatus.IDLE, List.of()));
        drones.add(new Drone(10L, "DR-2011", Model.MIDDLE_WEIGHT, 0.0, 45, DroneStatus.IDLE, List.of()));

        return drones;
    }

    public static List<Medication> medicationDb() {

        ArrayList<Medication> medList = new ArrayList<>();
        medList.add(new Medication(1L, "Alabukun", 25.0, "MED_101", "http://med-101.png", null));
        medList.add(new Medication(2L, "Agbo", 95.0, "MED_102", "http://med-102.png", null));
        medList.add(new Medication(3L, "Paracetamol", 10.0, "MED_103", "http://med-103.png", null));
        medList.add(new Medication(4L, "Panadol", 30.0, "MED_104", "http://med-104.png", null));
        medList.add(new Medication(5L, "Soldrex", 35.0, "MED_105", "http://med-105.png", null));
        medList.add(new Medication(6L, "Penicilin", 35.0, "MED_106", "http://med-106.png", null));
        medList.add(new Medication(7L, "Inhaler", 75.0, "MED_107", "http://med-107.png", null));
        medList.add(new Medication(8L, "Amoxil", 50.0, "MED_108", "http://med-108.png", null));
        medList.add(new Medication(9L, "Amartem", 40.0, "MED_109", "http://med-109.png", null));
        medList.add(new Medication(10L, "FluJ", 30.0, "MED_200", "http://med-200.png", null));
        medList.add(new Medication(11L, "Cough syrup", 60.0, "MED_201", "http://med-201.png", null));
        medList.add(new Medication(12L, "Vegemil", 45.0, "MED_202", "http://med-202.png", null));
        medList.add(new Medication(13L, "Anaesthetic", 100.0, "MED_203", "http://med-203.png", null));
        medList.add(new Medication(14L, "Quinol", 30.0, "MED_204", "http://med-204.png", null));
        medList.add(new Medication(15L, "Flygyl", 150.0, "MED_205", "http://med-205.png", null));

        return medList;
    }
}
