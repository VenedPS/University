package ua.com.foxminded.university.validation.implementation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.com.foxminded.university.validation.AddressConstraint;

public class AddressValidator implements ConstraintValidator<AddressConstraint, String> {
	
	private static final String ADRESS_PATTERN = "Country: \\S.*\\S, City: \\S.*\\S, Street: \\S.*\\S";
	
	@Override
    public void initialize(AddressConstraint address) {
    }

    @Override
    public boolean isValid(String addressField, ConstraintValidatorContext cxt) {
        return addressField != null
        		&& addressField.matches(ADRESS_PATTERN);
    }
}
