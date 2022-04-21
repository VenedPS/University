package ua.com.foxminded.university.validation.implementation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.com.foxminded.university.validation.PhoneConstraint;

public class PhoneValidator implements ConstraintValidator<PhoneConstraint, String> {
	
	private static final String PHONE_PATTERN = "[0-9]{12}";
	
	@Override
    public void initialize(PhoneConstraint phone) {
    }

    @Override
    public boolean isValid(String addressField, ConstraintValidatorContext cxt) {
        return addressField != null
        		&& addressField.matches(PHONE_PATTERN);
    }
}
