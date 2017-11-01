package com.locomizer.resttest.contraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EnumValidator.EnumValueValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
    public abstract String message() default "Invalid enum value";

    public abstract Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

    public abstract Class<? extends java.lang.Enum<?>> enumClass();

    public abstract boolean ignoreCase() default false;

    public class EnumValueValidator implements ConstraintValidator<EnumValidator, String> {
        private EnumValidator annotation;

        @Override
        public void initialize(EnumValidator annotation) {
            this.annotation = annotation;
        }

        @Override
        public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
            boolean result = false;

            Object[] enumValues = this.annotation.enumClass().getEnumConstants();

            if (enumValues != null) {
                for (Object enumValue : enumValues) {
                    if (valueForValidation.equals(enumValue.toString())
                            || (this.annotation.ignoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString()))) {
                        result = true;
                        break;
                    }
                }
            }

            return result;
        }
    }
}
