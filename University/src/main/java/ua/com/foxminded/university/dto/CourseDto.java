package ua.com.foxminded.university.dto;

import java.util.Objects;

public class CourseDto {

    private Integer id;
    private String name;
    private Integer teacherId;

    public CourseDto() {

    }

    public CourseDto(String name, Integer teacherId) {
        this.name = name;
        this.teacherId = teacherId;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, teacherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseDto other = (CourseDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(teacherId, other.teacherId);
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ", name=" + name + ", teacherId=" + teacherId + "]";
	}
    
}
