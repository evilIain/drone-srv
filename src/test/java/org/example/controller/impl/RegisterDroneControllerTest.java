package org.example.controller.impl;

import org.example.controller.BaseDroneControllerTest;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.RegisterDroneResponse;
import org.example.entity.DroneEntity;
import org.example.enums.Model;
import org.example.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterDroneControllerTest extends BaseDroneControllerTest {

    @Test
    public void registerDrone() {
        final RegisterDroneRequest request = RegisterDroneRequest.builder()
                .serialNumber("ABCDE1234")
                .model(Model.LIGHTWEIGHT)
                .weightLimit(400)
                .batteryCapacity(100)
                .build();

        final ResponseEntity<RegisterDroneResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        RegisterDroneResponse response = responseEntity.getBody();
        assertNotNull(response);

        final DroneEntity dbDroneEntity = droneRepository.findById(response.id()).get();

        RegisterDroneResponse expectedResponse = RegisterDroneResponse.builder()
                .id(dbDroneEntity.getId())
                .serialNumber(dbDroneEntity.getSerialNumber())
                .state(dbDroneEntity.getState())
                .batteryCapacity(dbDroneEntity.getBatteryCapacity())
                .model(dbDroneEntity.getModel())
                .weightLimit(dbDroneEntity.getWeightLimit())
                .build();

        assertEquals(expectedResponse, response);
    }

    @Test
    public void registerDroneWhenWeightLimitMoreThan500() {
        final RegisterDroneRequest request = RegisterDroneRequest.builder()
                .serialNumber("ABCDE1234")
                .model(Model.LIGHTWEIGHT)
                .weightLimit(600)
                .batteryCapacity(100)
                .build();

        final ResponseEntity<ErrorResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);

        assertEquals("Validation Error", errorResponse.message());

        assertTrue(errorResponse.errors().get(0).message().startsWith("weightLimit"));
    }
}
