package org.example.controller.impl;

import org.example.controller.BaseDroneControllerTest;
import org.example.dto.out.CheckDroneBatteryResponse;
import org.example.dto.out.GetLoadedMedicationsResponse;
import org.example.entity.DroneEntity;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GetDroneBatteryCapacityControllerTest extends BaseDroneControllerTest {

    @Test
    public void getDroneBatteryCapacity() {

        final ResponseEntity<CheckDroneBatteryResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone/" + droneEntity.getId() + "/battery",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        CheckDroneBatteryResponse response = responseEntity.getBody();
        assertNotNull(response);

        DroneEntity dbDroneEntity = droneRepository.findById(response.id()).get();

        assertEquals(dbDroneEntity.getBatteryCapacity(), response.batteryCapacity());
    }
}
