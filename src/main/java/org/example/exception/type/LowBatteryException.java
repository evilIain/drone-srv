package org.example.exception.type;

public class LowBatteryException extends RuntimeException {

    public LowBatteryException(String message) {
        super(message);
    }
}