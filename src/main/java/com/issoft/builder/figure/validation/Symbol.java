package com.issoft.builder.figure.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = SymbolValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Symbol {
    String message() default "Invalid symbol value. Use characters from [0-9] or [A-Z].";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}