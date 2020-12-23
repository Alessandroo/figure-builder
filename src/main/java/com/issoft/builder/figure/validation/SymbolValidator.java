package com.issoft.builder.figure.validation;

import org.apache.commons.lang3.CharUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SymbolValidator implements ConstraintValidator<Symbol, Character> {

    @Override
    public void initialize(Symbol symbol) {

    }

    @Override
    public boolean isValid(Character charField, ConstraintValidatorContext constraintContext) {
        if (charField == null) {
            return false;
        }
        return CharUtils.isAsciiNumeric(charField) || CharUtils.isAsciiAlphaUpper(charField);
    }

}
