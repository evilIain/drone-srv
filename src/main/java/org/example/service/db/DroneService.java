package org.example.service.db;

import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;

import java.util.List;


public interface DroneService {

    DroneEntity register(DroneEntity droneEntity);

    DroneEntity getById(String droneId);

    DroneEntity loadDroneWithMedications(DroneEntity droneEntity, List<MedicationEntity> medicationEntities);

    List<DroneEntity> getAvailableDrones();
}
