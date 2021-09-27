package ua.com.foxminded.university.dto;

import java.util.ArrayList;
import java.util.List;

public class StudyCalendar {
    private Integer id;
    private Integer year;
    private Integer semester;
    private List<StudyCalendarDays> days;

    public StudyCalendar(Integer id, Integer year, Integer semester, List<StudyCalendarDays> days) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.days = days;
    }
    
    public StudyCalendar(StudyCalendar studyCalendar) {
        this.id = studyCalendar.getId();
        this.year = studyCalendar.getYear();
        this.semester = studyCalendar.getSemester();
        this.days = new ArrayList<StudyCalendarDays>(studyCalendar.getDays());
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

    public List<StudyCalendarDays> getDays() {
        return days;
    }

    public void setDays(List<StudyCalendarDays> days) {
        this.days = days;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((days == null) ? 0 : days.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        StudyCalendar other = (StudyCalendar) obj;
        if (days == null) {
            if (other.days != null)
                return false;
        } else if (!days.equals(other.days))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        return "StudyCalendar [id=" + id + ", year=" + year + ", semester=" + semester + ", days=" + days + "]";
    }
}
