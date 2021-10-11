package ua.com.foxminded.university.entity;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private Integer id;
    private Integer year;
    private Integer semester;
    private List<Lesson> lessons;

    public Timetable(Integer id, Integer year, Integer semester, List<Lesson> lessons) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.lessons = lessons;
    }
    
    public Timetable(Timetable timetable) {
        this.id = timetable.getId();
        this.year = timetable.getYear();
        this.semester = timetable.getSemester();
        this.lessons = new ArrayList<Lesson>(timetable.getLessons());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
        result = prime * result + ((semester == null) ? 0 : semester.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
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
        Timetable other = (Timetable) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lessons == null) {
            if (other.lessons != null)
                return false;
        } else if (!lessons.equals(other.lessons))
            return false;
        if (semester == null) {
            if (other.semester != null)
                return false;
        } else if (!semester.equals(other.semester))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Timetable [id=" + id + ", year=" + year + ", semester=" + semester + ", lessons=" + lessons + "]";
    }
}
