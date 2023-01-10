package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.BaseIntegrationTest;
import org.example.DroneSrvApplication;
import org.example.converter.DroneMapper;
import org.example.db.TestDroneRepository;
import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;
import org.example.enums.Model;
import org.example.enums.State;
import org.example.repository.DroneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@ActiveProfiles(BaseIntegrationTest.TEST_PROFILE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = DroneSrvApplication.class)
public abstract class BaseDroneControllerTest extends BaseIntegrationTest {


    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected TestDroneRepository droneRepository;

    @Autowired
    protected DroneMapper droneMapper;

    protected DroneEntity droneEntity;
    protected DroneEntity lowBatteryDroneEntity;
    protected MedicationEntity medicationEntity;

    @BeforeEach
    public void init() {
        medicationEntity = MedicationEntity.builder()
                .code("ABC123")
                .name("Bandage")
                .weight(1)
                .build();

        droneEntity = DroneEntity.builder()
                .serialNumber("1234ABC")
                .model(Model.LIGHTWEIGHT)
                .batteryCapacity(100)
                .weightLimit(250)
                .medications(new ArrayList<>(List.of(medicationEntity)))
                .state(State.LOADING)
                .build();

        lowBatteryDroneEntity = DroneEntity.builder()
                .serialNumber("1234ABC")
                .model(Model.LIGHTWEIGHT)
                .batteryCapacity(25)
                .weightLimit(250)
                .state(State.IDLE)
                .build();

        medicationEntity.setDrone(droneEntity);

        droneRepository.save(droneEntity);
        droneRepository.save(lowBatteryDroneEntity);
    }

    @AfterEach
    public void tearDown() {

        droneRepository.deleteAll();
    }
}
