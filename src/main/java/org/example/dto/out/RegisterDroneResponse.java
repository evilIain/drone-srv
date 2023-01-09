package org.example.dto.out;

import lombok.Builder;
import org.example.enums.Model;
import org.example.enums.State;

import java.util.UUID;

public record RegisterDroneResponse(

        UUID id,

        String serialNumber,

        Model model,

        Integer weightLimit,

        Integer batteryCapacity,

        State state
) {

    @Builder
    public RegisterDroneResponse {
    }
}
