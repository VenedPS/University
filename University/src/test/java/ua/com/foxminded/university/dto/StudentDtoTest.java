package ua.com.foxminded.university.dto;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StudentDtoTest {
	
	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void validation_shouldHave5ValidationErrors_whenAllFieldsAreNull() {
		StudentDto studentDto = new StudentDto();

		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);

		assertEquals(5, constraintViolations.size());
	}
	
	@Test
	public void validation_shouldHaveFirstNameValidationError_whenFirstNameIsNull() {
		StudentDto studentDto = new StudentDto();
		studentDto.setSecondName("SecondName");
		studentDto.setBirthDate(LocalDate.of(1900, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(
		         "Please enter first name!",
		         constraintViolations.iterator().next().getMessage()
		      );
	}
	
	@Test
	public void validation_shouldHaveFirstNameValidationError_whenFirstNameSizeLesThanTwo() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("F");
		studentDto.setSecondName("SecondName");
		studentDto.setBirthDate(LocalDate.of(1900, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void validation_shouldHaveFirstNameValidationError_whenFirstNameSizeMoreThanTwenty() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("FirstNameFirstNameFirstName");
		studentDto.setSecondName("SecondName");
		studentDto.setBirthDate(LocalDate.of(1900, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void validation_shouldHaveSecondNameValidationError_whenSecondNameIsNull() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("FirstName");
		studentDto.setBirthDate(LocalDate.of(1900, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(
	         "Please enter second name!",
	         constraintViolations.iterator().next().getMessage()
	      );
	}
	
	@Test
	public void validation_shouldHaveBirthDateValidationError_whenBirthDateIsNull() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("FirstName");
		studentDto.setSecondName("SecondName");
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(
	         "Invalid birth date",
	         constraintViolations.iterator().next().getMessage()
	      );
	}
	
	@Test
	public void validation_shouldHaveBirthDateValidationError_whennvalidBirthDate() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("FirstName");
		studentDto.setSecondName("SecondName");
		studentDto.setBirthDate(LocalDate.now());
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(
				"Invalid birth date",
				constraintViolations.iterator().next().getMessage()
				);
	}
	
	@Test
	public void validation_shouldHaveAddressValidationError_whenAddressIsNull() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("FirstName");
		studentDto.setSecondName("SecondName");
		studentDto.setBirthDate(LocalDate.of(1900, 1, 1));
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(
	         "Address must be like: Country: ___, City: ___, Street: ___",
	         constraintViolations.iterator().next().getMessage()
	      );
	}
	
	@Test
	public void validation_shouldHavePhoneValidationError_whenInvalidPhoneNumber() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("FirstName");
		studentDto.setSecondName("SecondName");
		studentDto.setBirthDate(LocalDate.of(1900, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380");
		studentDto.setEmail("email@mail.xyz");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(
	         "Invalid phone number",
	         constraintViolations.iterator().next().getMessage()
	      );
	}
	
	@Test
	public void validation_shouldHaveEmailValidationError_whenInvalidEmail() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("FirstName");
		studentDto.setSecondName("SecondName");
		studentDto.setBirthDate(LocalDate.of(1900, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email");
		
		Set<ConstraintViolation<StudentDto>> constraintViolations = 
				validator.validate(studentDto);
		
		assertEquals(1, constraintViolations.size());
	}
}
