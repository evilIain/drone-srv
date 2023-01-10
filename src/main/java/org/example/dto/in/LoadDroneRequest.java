package org.example.dto.in;

import lombok.Builder;
import org.example.dto.Medication;

import javax.validation.Valid;
import java.util.List;

public record LoadDroneRequest(
        @Valid
        List<Medication> medications
) {

    @Builder
    public LoadDroneRequest {
    }
}
