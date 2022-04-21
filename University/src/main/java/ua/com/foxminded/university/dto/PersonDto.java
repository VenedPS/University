package ua.com.foxminded.university.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import ua.com.foxminded.university.validation.AddressConstraint;
import ua.com.foxminded.university.validation.BirthDateConstraint;
import ua.com.foxminded.university.validation.PhoneConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public abstract class PersonDto {
    
    @NotEmpty(message = "Please enter first name!")
    @Size(min = 2, max = 20)
    protected String firstName;
    
    @NotEmpty(message = "Please enter second name!")
    @Size(min = 2, max = 25)
    protected String secondName;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @BirthDateConstraint
    protected LocalDate birthDate;
    
    @AddressConstraint
    protected String address;
    
    @PhoneConstraint
    protected String phone;
    
    @Email
    protected String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
