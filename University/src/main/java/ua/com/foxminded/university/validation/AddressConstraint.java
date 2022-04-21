package ua.com.foxminded.university.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.com.foxminded.university.validation.implementation.AddressValidator;

@Documented
@Constraint(validatedBy = AddressValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AddressConstraint {

    String message() default "Address must be like: Country: ___, City: ___, Street: ___";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
