package com.issoft.builder.figure.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum BorderStyle {
    DOTTED("Dotted"),
    DASHED("Dashed"),
    SOLID("Solid");

    private final String style;

    BorderStyle(String style) {
        this.style = style;
    }

    @JsonCreator
    public static BorderStyle decode(final String style) {
        return Stream.of(BorderStyle.values())
                .filter(targetEnum -> targetEnum.style.equalsIgnoreCase(style))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getStyle() {
        return style;
    }
}
