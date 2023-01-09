package org.example.dto.in;

import lombok.Builder;
import org.example.enums.Model;
import org.example.enums.State;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record RegisterDroneRequest(

        @NotBlank
        @Size(max = 100)
        String serialNumber,

        @NotNull
        Model model,

        @NotNull
        @Max(value = 500)
        Integer weightLimit,

        Integer batteryCapacity,

        State state
) {

    @Builder
    public RegisterDroneRequest {
    }
}
