package org.example.controller.impl;

import org.example.controller.BaseDroneControllerTest;
import org.example.dto.Medication;
import org.example.dto.in.LoadDroneRequest;
import org.example.dto.out.LoadDroneResponse;
import org.example.entity.DroneEntity;
import org.example.enums.State;
import org.example.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoadDroneWithMedicationsControllerTest extends BaseDroneControllerTest {

    @Test
    public void loadDroneWithMedications() {

        Medication medication = Medication.builder()
                .code("ABC123")
                .name("Bandage")
                .weight(100)
                .build();

        final LoadDroneRequest request = LoadDroneRequest.builder()
                .medications(List.of(medication))
                .build();

        final ResponseEntity<LoadDroneResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone/" + droneEntity.getId(),
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        LoadDroneResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(State.LOADING, response.state());

        DroneEntity dbDroneEntity = droneRepository.getByIdWithJoin(response.id());

        LoadDroneResponse expectedResponse = LoadDroneResponse.builder()
                .id(dbDroneEntity.getId())
                .serialNumber(dbDroneEntity.getSerialNumber())
                .state(dbDroneEntity.getState())
                .batteryCapacity(dbDroneEntity.getBatteryCapacity())
                .model(dbDroneEntity.getModel())
                .weightLimit(dbDroneEntity.getWeightLimit())
                .medications(droneMapper.toMedications(dbDroneEntity.getMedications()))
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    public void loadDroneWithMedicationsFullyLoaded() {

        Medication medication = Medication.builder()
                .code("ABC123")
                .name("Bandage")
                .weight(249)
                .build();

        final LoadDroneRequest request = LoadDroneRequest.builder()
                .medications(List.of(medication))
                .build();

        final ResponseEntity<LoadDroneResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone/" + droneEntity.getId(),
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        LoadDroneResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(State.LOADED, response.state());

        DroneEntity dbDroneEntity = droneRepository.getByIdWithJoin(response.id());

        LoadDroneResponse expectedResponse = LoadDroneResponse.builder()
                .id(dbDroneEntity.getId())
                .serialNumber(dbDroneEntity.getSerialNumber())
                .state(dbDroneEntity.getState())
                .batteryCapacity(dbDroneEntity.getBatteryCapacity())
                .model(dbDroneEntity.getModel())
                .weightLimit(dbDroneEntity.getWeightLimit())
                .medications(droneMapper.toMedications(dbDroneEntity.getMedications()))
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    public void loadDroneWithMedicationsWithOverWeightLimit() {

        Medication medication = Medication.builder()
                .code("ABC123")
                .name("Bandage")
                .weight(400)
                .build();

        final LoadDroneRequest request = LoadDroneRequest.builder()
                .medications(List.of(medication))
                .build();

        final ResponseEntity<ErrorResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone/" + droneEntity.getId(),
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorResponse response = responseEntity.getBody();
        assertNotNull(response);

        assertTrue(response.message().startsWith("Current weight limit"));
    }

    @Test
    public void loadDroneWithMedicationsWithLowBattery() {

        Medication medication = Medication.builder()
                .code("ABC123")
                .name("Bandage")
                .weight(200)
                .build();

        final LoadDroneRequest request = LoadDroneRequest.builder()
                .medications(List.of(medication))
                .build();

        final ResponseEntity<ErrorResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone/" + lowBatteryDroneEntity.getId(),
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorResponse response = responseEntity.getBody();
        assertNotNull(response);

        assertTrue(response.message().startsWith("Battery capacity"));
    }

    @Test
    public void loadDroneWithMedicationsButCorruptCodeAndName() {

        Medication medication = Medication.builder()
                .code("abc123")
                .name("Bandage!")
                .weight(200)
                .build();

        final LoadDroneRequest request = LoadDroneRequest.builder()
                .medications(List.of(medication))
                .build();

        final ResponseEntity<ErrorResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone/" + lowBatteryDroneEntity.getId(),
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(2, response.errors().size());
    }
}
