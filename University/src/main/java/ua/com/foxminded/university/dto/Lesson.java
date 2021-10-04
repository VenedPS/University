package ua.com.foxminded.university.dto;

import java.time.LocalDate;

public class Lesson {
    private Integer id;
    private LocalDate date;
    private Integer lessonNumber;
    private Group group;
    private Course course;
    private Classroom classroom;
    private Teacher teacher;

    public Lesson() {
        
    }
    
    public Lesson(Integer id, LocalDate date, Integer lessonNumber, Group group, Course course, Classroom classroom,
            Teacher teacher) {
        this.id = id;
        this.date = date;
        this.lessonNumber = lessonNumber;
        this.group = group;
        this.course = course;
        this.classroom = classroom;
        this.teacher = teacher;
    }
    
    public Lesson(Lesson lesson) {
        this.id = lesson.getId();
        this.date = lesson.getDate();
        this.lessonNumber = lesson.getLessonNumber();
        this.group = lesson.getGroup();
        this.course = lesson.getCourse();
        this.classroom = lesson.getClassroom();
        this.teacher = lesson.getTeacher();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lessonNumber == null) ? 0 : lessonNumber.hashCode());
        result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
        Lesson other = (Lesson) obj;
        if (classroom == null) {
            if (other.classroom != null)
                return false;
        } else if (!classroom.equals(other.classroom))
            return false;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lessonNumber == null) {
            if (other.lessonNumber != null)
                return false;
        } else if (!lessonNumber.equals(other.lessonNumber))
            return false;
        if (teacher == null) {
            if (other.teacher != null)
                return false;
        } else if (!teacher.equals(other.teacher))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + ", date=" + date + ", lessonNumber=" + lessonNumber + ", group=" + group
                + ", course=" + course + ", classroom=" + classroom + ", teacher=" + teacher + "]";
    }
}
