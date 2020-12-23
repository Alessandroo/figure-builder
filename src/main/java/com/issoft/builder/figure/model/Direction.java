package com.issoft.builder.figure.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Direction {
    HORIZONTAL("Horizontal"),
    VERTICAL("Vertical");

    private final String position;

    Direction(String position) {
        this.position = position;
    }

    @JsonCreator
    public static Direction decode(final String position) {
        return Stream.of(Direction.values())
                .filter(targetEnum -> targetEnum.position.equalsIgnoreCase(position))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getStyle() {
        return position;
    }
}
