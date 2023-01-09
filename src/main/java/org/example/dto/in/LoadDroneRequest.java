package org.example.dto.in;

import lombok.Builder;
import org.example.dto.Medication;

import java.util.List;

public record LoadDroneRequest(
        List<Medication> medications
) {

    @Builder
    public LoadDroneRequest {
    }
}
