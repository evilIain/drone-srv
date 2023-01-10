package org.example.dto.out;

import lombok.Builder;

import java.util.Objects;

public record CheckDroneBatteryResponse(
        String id,
        Integer batteryCapacity
) {
    @Builder
    public CheckDroneBatteryResponse {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CheckDroneBatteryResponse that = (CheckDroneBatteryResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(batteryCapacity, that.batteryCapacity);
    }
}
