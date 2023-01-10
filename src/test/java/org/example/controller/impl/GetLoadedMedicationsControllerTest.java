package org.example.controller.impl;

import org.example.controller.BaseDroneControllerTest;
import org.example.dto.out.GetLoadedMedicationsResponse;
import org.example.entity.DroneEntity;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetLoadedMedicationsControllerTest extends BaseDroneControllerTest {

    @Test
    public void getLoadedMedications() {

        final ResponseEntity<GetLoadedMedicationsResponse> responseEntity = testRestTemplate.exchange(
                CONTROLLER_URI + "/drone/" + droneEntity.getId(),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        GetLoadedMedicationsResponse response = responseEntity.getBody();
        assertNotNull(response);

        DroneEntity dbDroneEntity = droneRepository.getByIdWithJoin(droneEntity.getId());

        GetLoadedMedicationsResponse expectedResponse = GetLoadedMedicationsResponse.builder()
                .medications(droneMapper.toMedications(dbDroneEntity.getMedications()))
                .build();

        assertEquals(expectedResponse, response);
    }
}
