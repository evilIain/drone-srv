package org.example.service.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;
import org.example.enums.State;
import org.example.repository.DroneRepository;
import org.example.service.DroneServiceHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final DroneServiceHelper droneServiceHelper;

    @Override
    public DroneEntity register(final DroneEntity droneEntity) {
        final DroneEntity dbDroneEntity = droneRepository.save(droneEntity);

        log.info("Drone registered: {}", dbDroneEntity);
        return dbDroneEntity;
    }

    @Override
    public Optional<DroneEntity> getById(String droneId) {
        return droneRepository.findById(droneId);
    }

    @Override
    public DroneEntity loadDroneWithMedications(DroneEntity droneEntity, List<MedicationEntity> medicationEntities) {

        medicationEntities.forEach(m -> m.setDrone(droneEntity));
        List<MedicationEntity> currentMedications = droneEntity.getMedications();
        currentMedications.addAll(medicationEntities);
        droneEntity.setMedications(currentMedications);

        if (droneServiceHelper.getCurrentWorkload(droneEntity) == droneEntity.getWeightLimit()) {
            droneEntity.setState(State.LOADED);
        } else {
            droneEntity.setState(State.LOADING);
        }

        return droneRepository.save(droneEntity);
    }
}
