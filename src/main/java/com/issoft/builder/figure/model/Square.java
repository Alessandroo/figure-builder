package com.issoft.builder.figure.model;

import com.issoft.builder.figure.validation.Symbol;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "squares")
@EqualsAndHashCode(callSuper = true)
public class Square extends Figure {
    @Symbol
    private Character symbol;
}
