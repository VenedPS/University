package ua.com.foxminded.university.dto;

import java.time.LocalDate;

public class LessonDto {
    private int id;
    private int timetableId;
    private LocalDate date;
    private int lessonNumber;
    private int groupId;
    private int courseId;
    private int classroomId;
    private int teacherId;

    public LessonDto() {

    }

    public LessonDto(int id, int timetableId, LocalDate date, int lessonNumber, int groupId, int courseId, int classroomId,
            int teacherId) {
        this.id = id;
        this.timetableId = timetableId;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.groupId = groupId;
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
    }

    public LessonDto(LessonDto lesson) {
        this.id = lesson.getId();
        this.timetableId = lesson.getTimetableId();
        this.date = lesson.getDate();
        this.lessonNumber = lesson.getLessonNumber();
        this.groupId = lesson.getGroupId();
        this.courseId = lesson.getCourseId();
        this.classroomId = lesson.getClassroomId();
        this.teacherId = lesson.getTeacherId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + classroomId;
        result = prime * result + courseId;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + groupId;
        result = prime * result + id;
        result = prime * result + lessonNumber;
        result = prime * result + teacherId;
        result = prime * result + timetableId;
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
        LessonDto other = (LessonDto) obj;
        if (classroomId != other.classroomId)
            return false;
        if (courseId != other.courseId)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (groupId != other.groupId)
            return false;
        if (id != other.id)
            return false;
        if (lessonNumber != other.lessonNumber)
            return false;
        if (teacherId != other.teacherId)
            return false;
        if (timetableId != other.timetableId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + ", timetableId=" + timetableId + ", date=" + date + ", lessonNumber=" + lessonNumber
                + ", groupId=" + groupId + ", courseId=" + courseId + ", classroomId=" + classroomId + ", teacherId="
                + teacherId + "]";
    }

}
