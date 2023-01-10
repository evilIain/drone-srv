package org.example.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medication that = (Medication) o;
        return Objects.equals(name, that.name)
                && Objects.equals(weight, that.weight)
                && Objects.equals(code, that.code);
    }
}
