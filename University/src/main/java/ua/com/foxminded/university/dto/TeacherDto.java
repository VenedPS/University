package ua.com.foxminded.university.dto;

import java.util.Objects;

public class TeacherDto extends PersonDto {
    private Integer id;

    public TeacherDto() {
        
    }
    
    public TeacherDto(Integer id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }
    
    public TeacherDto(TeacherDto teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.secondName = teacher.getSecondName();
        this.birthDate = teacher.getBirthDate();
        this.address = teacher.getAddress();
        this.phone = teacher.getPhone();
        this.email = teacher.getEmail();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeacherDto other = (TeacherDto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TeacherDto [id=" + id + "]";
	}
    
}
