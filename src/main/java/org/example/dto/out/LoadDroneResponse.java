package org.example.dto.out;

import lombok.Builder;
import org.example.dto.Medication;
import org.example.enums.Model;
import org.example.enums.State;

import java.util.List;
import java.util.Objects;

public record LoadDroneResponse(
        String id,

        String serialNumber,

        Model model,

        Integer weightLimit,

        Integer batteryCapacity,

        State state,

        List<Medication> medications
) {

    @Builder
    public LoadDroneResponse {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoadDroneResponse that = (LoadDroneResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(serialNumber, that.serialNumber)
                && Objects.equals(model, that.model)
                && Objects.equals(weightLimit, that.weightLimit)
                && Objects.equals(batteryCapacity, that.batteryCapacity)
                && Objects.equals(state, that.state)
                && Objects.equals(medications, that.medications);
    }

}
