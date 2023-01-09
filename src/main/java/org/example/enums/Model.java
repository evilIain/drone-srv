package org.example.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Model {

    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISERWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    private final String modelName;

    Model(final String modelName) {
        this.modelName = modelName;
    }

    @JsonValue
    public String getModelName() {
        return modelName;
    }
}
