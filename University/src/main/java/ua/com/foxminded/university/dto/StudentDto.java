package ua.com.foxminded.university.dto;

import java.util.Objects;

public class StudentDto extends PersonDto {
    private Integer id;
    private Integer groupId;
    
    public StudentDto() {

    }
    
    public StudentDto(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
    
    public StudentDto(StudentDto student) {
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
		return Objects.hash(groupId, id);
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
		return Objects.equals(groupId, other.groupId) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", groupId=" + groupId + "]";
	}
    
}
