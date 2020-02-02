package com.bloomberg.mathexpression.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private List<String> values = null;

    private boolean required;

    private boolean canUseFindBy;

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {

        this.values = new ArrayList<>();
        this.required = constraintAnnotation.required();
        this.canUseFindBy = constraintAnnotation.canUseMethodsFindBy();
        this.enumClass = constraintAnnotation.enumClass();
        @SuppressWarnings("rawtypes")
        Enum[] enumArray = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumArray) {
            this.values.add(enumVal.name().toUpperCase());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean valid;
        if (required) {
            valid = value != null && values.contains(value.toUpperCase());
        } else {
            valid = value == null || values.contains(value.toUpperCase());
        }
        if (!valid && canUseFindBy) {
            valid = isAnyEnumMethodValid(value);
        }
        return valid;
    }

    private boolean isAnyEnumMethodValid(String value) {
        List<Method> methods = Arrays.stream(enumClass.getDeclaredMethods())
                .filter(method -> method.getName().startsWith("findBy")).collect(Collectors.toList());
        for (Method method : methods) {
            try {
                Object result = method.invoke(null, value);
                if (result != null) {
                    return true;
                }
            } catch (Exception e) {
              //do nothing
            }
        }
        return false;
    }
}
