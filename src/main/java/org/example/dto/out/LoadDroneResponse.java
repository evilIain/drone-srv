package org.example.dto.out;

import org.example.dto.Medication;
import org.example.enums.Model;
import org.example.enums.State;

import java.util.List;
import java.util.UUID;

public record LoadDroneResponse(
        UUID id,

        String serialNumber,

        Model model,

        Integer weightLimit,

        Integer batteryCapacity,

        State state,

        List<Medication> medications
) {

}
