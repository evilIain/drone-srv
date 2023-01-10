package org.example.dto.out;

import lombok.Builder;
import org.example.enums.Model;
import org.example.enums.State;

import java.util.Objects;

public record RegisterDroneResponse(

        String id,

        String serialNumber,

        Model model,

        Integer weightLimit,

        Integer batteryCapacity,

        State state
) {

    @Builder
    public RegisterDroneResponse {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegisterDroneResponse that = (RegisterDroneResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(serialNumber, that.serialNumber)
                && Objects.equals(model, that.model)
                && Objects.equals(weightLimit, that.weightLimit)
                && Objects.equals(batteryCapacity, that.batteryCapacity)
                && Objects.equals(state, that.state);
    }
}
