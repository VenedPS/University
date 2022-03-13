package ua.com.foxminded.university.dto;

import java.time.LocalDate;

public class LessonDto {
    private int id;
    private int timetableId;
    private LocalDate date;
    private int lessonNumber;
    private GroupDto group;
    private int courseId;
    private int classroomId;
    private TeacherDto teacher;

    public LessonDto() {

    }

    public LessonDto(int id, int timetableId, LocalDate date, int lessonNumber, GroupDto group, int courseId, int classroomId,
            TeacherDto teacher) {
        this.id = id;
        this.timetableId = timetableId;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.group = group;
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.teacher = teacher;
    }

    public LessonDto(LessonDto lesson) {
        this.id = lesson.getId();
        this.timetableId = lesson.getTimetableId();
        this.date = lesson.getDate();
        this.lessonNumber = lesson.getLessonNumber();
        this.group = lesson.getGroup();
        this.courseId = lesson.getCourseId();
        this.classroomId = lesson.getClassroomId();
        this.teacher = lesson.getTeacher();
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

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
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

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + classroomId;
        result = prime * result + courseId;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + id;
        result = prime * result + lessonNumber;
        result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (id != other.id)
            return false;
        if (lessonNumber != other.lessonNumber)
            return false;
        if (teacher == null) {
            if (other.teacher != null)
                return false;
        } else if (!teacher.equals(other.teacher))
            return false;
        if (timetableId != other.timetableId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LessonDto [id=" + id + ", timetableId=" + timetableId + ", date=" + date + ", lessonNumber="
                + lessonNumber + ", group=" + group + ", courseId=" + courseId + ", classroomId=" + classroomId
                + ", teacher=" + teacher + "]";
    }

}
