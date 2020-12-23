package com.issoft.builder.figure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "triangles")
@EqualsAndHashCode(callSuper = true)
public class Triangle extends Figure {
    @Pattern(regexp = "#[a-fA-F0-9]{6}",
            message = "color have to be set in hex color format like #00BBFF")
    public String color;
}
