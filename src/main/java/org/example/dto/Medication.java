package org.example.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;


public record Medication(

        @Pattern(regexp = "^[0-9A-Za-z\\-_]+$", message = "not matching pattern. Only letters, numbers, `-`, `_` are allowed")
        String name,

        @NotNull
        Integer weight,

        @NotBlank
        @Pattern(regexp = "^[0-9A-Z_]+$", message = "not matching pattern. Only upper letters, numbers, `_` are allowed")
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
