package ua.com.foxminded.university.entity;

public class StudentEntity extends PersonEntity {
    private Integer id;
    private Integer groupId;
    
    public StudentEntity() {

    }

    public StudentEntity(Integer id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public StudentEntity(StudentEntity student) {
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
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
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
        StudentEntity other = (StudentEntity) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
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
        if (secondName == null) {
            if (other.secondName != null)
                return false;
        } else if (!secondName.equals(other.secondName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", groupId=" + groupId + ", firstName=" + firstName + ", secondName=" + secondName
                + ", birthDate=" + birthDate + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
    }

}
