package ua.com.foxminded.university.validation.implementation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.com.foxminded.university.validation.BirthDateConstraint;

public class BirthDateValidator implements ConstraintValidator<BirthDateConstraint, LocalDate> {
	
	@Override
    public void initialize(BirthDateConstraint birthDate) {
    }

    @Override
    public boolean isValid(LocalDate birthDateField, ConstraintValidatorContext cxt) {
        return birthDateField != null
        		&& birthDateField.getYear() < (LocalDate.now().getYear() - 16);
    }
}
