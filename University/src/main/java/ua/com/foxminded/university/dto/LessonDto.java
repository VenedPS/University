package ua.com.foxminded.university.dto;

import java.time.LocalDate;
import java.util.Objects;

public class LessonDto {
    private Integer id;
    private Integer timetableId;
    private LocalDate date;
    private Integer lessonNumber;
    private Integer groupId;
    private Integer courseId;
    private Integer classroomId;
    private Integer teacherId;

    public LessonDto() {

    }

    public LessonDto(Integer id, Integer timetableId, LocalDate date, 
    		Integer lessonNumber, Integer groupId, Integer courseId, 
    		Integer classroomId, Integer teacherId) {
    	
        this.id = id;
        this.timetableId = timetableId;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.groupId = groupId;
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTimetableId() {
		return timetableId;
	}

	public void setTimetableId(Integer timetableId) {
		this.timetableId = timetableId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(Integer lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classroomId, courseId, date, groupId, id, lessonNumber, teacherId, timetableId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LessonDto other = (LessonDto) obj;
		return Objects.equals(classroomId, other.classroomId) && Objects.equals(courseId, other.courseId)
				&& Objects.equals(date, other.date) && Objects.equals(groupId, other.groupId)
				&& Objects.equals(id, other.id) && Objects.equals(lessonNumber, other.lessonNumber)
				&& Objects.equals(teacherId, other.teacherId) && Objects.equals(timetableId, other.timetableId);
	}

	@Override
	public String toString() {
		return "LessonDto [id=" + id + ", timetableId=" + timetableId + ", date=" + date + ", lessonNumber="
				+ lessonNumber + ", groupId=" + groupId + ", courseId=" + courseId + ", classroomId=" + classroomId
				+ ", teacherId=" + teacherId + "]";
	}

}
