package org.example.service.scheduler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.DroneEntity;
import org.example.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckBatteryScheduler {

    Logger rollingFileLogger = LoggerFactory.getLogger("rollingFileLogger");

    private final DroneRepository droneRepository;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void checkBattery() {

        log.info("Running scheduled task: checkBattery");
        List<DroneEntity> droneEntities = droneRepository.findAll();

        droneEntities.forEach(drone -> {
            String log = "{Current battery level: " + drone.getBatteryCapacity() + " Drone: " + drone.getId() + "}";
            rollingFileLogger.info(log);
        });
    }


}
