package org.example.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record Medication(
        String name,

        @NotNull
        Integer weight,

        @NotBlank
        String code,

        byte[] image
) {

    @Builder
    public Medication {
    }
}
