package org.example.controller.impl;

import org.example.controller.BaseDroneControllerTest;
import org.example.dto.out.BaseDroneResponse;
import org.example.entity.DroneEntity;
import org.example.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetAvailableDronesControllerTest extends BaseDroneControllerTest {

    @Test
    public void getAvailableDrones() {

        final ResponseEntity<List<BaseDroneResponse>> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drones",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<BaseDroneResponse> response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(1, response.size());

        List<DroneEntity> availableDroneEntities = droneRepository.findAllByStateInAndBatteryCapacityGreaterThan(List.of(State.IDLE, State.LOADING), 25);
        assertEquals(droneMapper.toDroneBaseResponseList(availableDroneEntities), response);
    }
}
