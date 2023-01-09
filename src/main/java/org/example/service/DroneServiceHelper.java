package org.example.service;

import org.example.dto.Medication;
import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;
import org.example.exception.type.LowBatteryException;
import org.example.exception.type.NotFoundException;
import org.example.exception.type.WeightLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceHelper {

    public boolean isDroneValid(DroneEntity droneEntity, List<Medication> medications) {
        if (validateBatteryCapacity(droneEntity)) {
            throw new LowBatteryException("Battery capacity for drone with id " + droneEntity.getId() + " is less than 25%");
        }

        int currentWorkload = getCurrentWorkload(droneEntity);
        int medicationsWeight = getMedicationsWeight(medications);

        if (droneEntity.getWeightLimit() - currentWorkload < medicationsWeight) {
            throw new WeightLimitException("Current weight limit for drone with id " + droneEntity.getId() + " is less than medication weight");
        }

        return true;
    }

    public boolean validateBatteryCapacity(DroneEntity droneEntity) {
        return droneEntity.getBatteryCapacity() <= 25;
    }
    public int getCurrentWorkload(DroneEntity droneEntity) {
        return droneEntity.getMedications()
                .stream()
                .mapToInt(MedicationEntity::getWeight)
                .sum();
    }

    public int getMedicationsWeight(List<Medication> medications) {
        return medications.stream()
                .mapToInt(Medication::weight)
                .sum();
    }

}
