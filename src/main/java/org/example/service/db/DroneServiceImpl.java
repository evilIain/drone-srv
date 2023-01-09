package org.example.service.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.DroneEntity;
import org.example.repository.DroneRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public DroneEntity register(final DroneEntity droneEntity) {
        final DroneEntity dbDroneEntity = droneRepository.save(droneEntity);

        log.info("Drone registered: {}", dbDroneEntity);
        return dbDroneEntity;
    }
}
