package ua.com.foxminded.university.dto;

import java.time.LocalDateTime;

public class Person {
    private Integer id;
    private String firstName;
    private String secondName;
    private LocalDateTime birthDate;
    private String address;
    private String phone;
    private String email;

    public Person(Integer id, String firstName, String secondName, LocalDateTime birthDate, String address,
            String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    
    public Person(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.secondName = person.getSecondName();
        this.birthDate = person.getBirthDate();
        this.address = person.getAddress();
        this.phone = person.getPhone();
        this.email = person.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (secondName == null) {
            if (other.secondName != null)
                return false;
        } else if (!secondName.equals(other.secondName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", birthDate="
                + birthDate + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
    }
}
