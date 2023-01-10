package org.example.dto.out;

import org.example.enums.State;

public record BaseDroneResponse(
        String id,

        String serialNumber,

        Integer weightLimit,

        Integer batteryCapacity,

        State state
) {

}
