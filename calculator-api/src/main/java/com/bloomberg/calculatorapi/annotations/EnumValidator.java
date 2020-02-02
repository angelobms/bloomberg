package com.bloomberg.calculatorapi.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClass();

    boolean required() default true;

    /** Used when an enum offer a method to get an enum different of the 'valueOf' */
    boolean canUseMethodsFindBy() default true;

    String message() default "Value is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};
}
