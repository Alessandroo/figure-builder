package com.issoft.builder.figure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "circles")
public class Circle extends Figure {
    @Enumerated(EnumType.ORDINAL)
    private BorderStyle borderStyle;
}
