package org.example.exception.type;

public class WeightLimitException extends RuntimeException {

    public WeightLimitException(String message) {
        super(message);
    }
}
