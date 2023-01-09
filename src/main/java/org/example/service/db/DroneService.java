package org.example.service.db;

import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;

import java.util.List;
import java.util.Optional;


public interface DroneService {

    DroneEntity register(DroneEntity droneEntity);

    Optional<DroneEntity> getById(String droneId);

    DroneEntity loadDroneWithMedications(DroneEntity droneEntity, List<MedicationEntity> medicationEntities);
}
