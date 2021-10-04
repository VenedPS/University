package ua.com.foxminded.university.dto;

public class Student extends Person {
    private Integer id;
    private Integer groupId;

    public Student(Integer id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
    
    public Student(Student student) {
        this.id = student.getId();
        this.groupId = student.getGroupId();
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Student other = (Student) obj;
        if (groupId == null) {
            if (other.groupId != null)
                return false;
        } else if (!groupId.equals(other.groupId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", groupId=" + groupId + ", firstName=" + firstName + ", secondName=" + secondName
                + ", birthDate=" + birthDate + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
    }

}