package com.locomizer.resttest.contraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Documented
@Constraint(validatedBy = {EnumValidator.EnumValueValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
    String message() default "Invalid enum value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();

    boolean ignoreCase() default false;

    class EnumValueValidator implements ConstraintValidator<EnumValidator, String> {
        private EnumValidator annotation;
        private Set<String> enumValues;

        @Override
        public void initialize(EnumValidator annotation) {
            this.annotation = annotation;
            this.enumValues = Arrays.stream(this.annotation.enumClass().getEnumConstants())
                    .map(Enum::name)
                    .map(e -> annotation.ignoreCase() ? e.toLowerCase() : e)
                    .collect(Collectors.toSet());
        }

        @Override
        public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
            if (valueForValidation == null || valueForValidation.isEmpty()) {
                return false;
            }

            if (this.annotation.ignoreCase()) {
                valueForValidation = valueForValidation.toLowerCase();
            }

            return enumValues.contains(valueForValidation);
        }
    }
}
