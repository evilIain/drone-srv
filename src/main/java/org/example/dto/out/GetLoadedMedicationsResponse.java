package org.example.dto.out;

import lombok.Builder;
import org.example.dto.Medication;

import java.util.List;

public record GetLoadedMedicationsResponse(
        List<Medication> medications
) {

    @Builder
    public GetLoadedMedicationsResponse {
    }
}
