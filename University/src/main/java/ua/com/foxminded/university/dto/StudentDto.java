package ua.com.foxminded.university.dto;

public class StudentDto extends PersonDto {
    private Integer id;
    private GroupDto group;
    
    public StudentDto() {

    }
    
    public StudentDto(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
    
    public StudentDto(StudentDto student) {
        this.group = student.getGroup();
        this.firstName = student.getFirstName();
        this.secondName = student.getSecondName();
        this.birthDate = student.getBirthDate();
        this.address = student.getAddress();
        this.phone = student.getPhone();
        this.email = student.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        StudentDto other = (StudentDto) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        return "Student [id=" + id + ", group=" + group + ", firstName=" + firstName + ", secondName=" + secondName
                + ", birthDate=" + birthDate + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
    }
}
