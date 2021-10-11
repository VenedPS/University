package ua.com.foxminded.university.entity;

import java.time.LocalTime;

public class TimeFrameLine {
    private Integer id;
    private Integer lessonNumbe;
    private LocalTime begin;
    private LocalTime end;

    public TimeFrameLine(Integer id, Integer lessonNumbe, LocalTime begin, LocalTime end) {
        this.id = id;
        this.lessonNumbe = lessonNumbe;
        this.begin = begin;
        this.end = end;
    }
    
    public TimeFrameLine(TimeFrameLine timeFrameLine) {
        this.id = timeFrameLine.getId();
        this.lessonNumbe = timeFrameLine.getLessonNumbe();
        this.begin = timeFrameLine.getBegin();
        this.end = timeFrameLine.getEnd();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLessonNumbe() {
        return lessonNumbe;
    }

    public void setLessonNumbe(Integer lessonNumbe) {
        this.lessonNumbe = lessonNumbe;
    }

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((begin == null) ? 0 : begin.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lessonNumbe == null) ? 0 : lessonNumbe.hashCode());
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
        TimeFrameLine other = (TimeFrameLine) obj;
        if (begin == null) {
            if (other.begin != null)
                return false;
        } else if (!begin.equals(other.begin))
            return false;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lessonNumbe == null) {
            if (other.lessonNumbe != null)
                return false;
        } else if (!lessonNumbe.equals(other.lessonNumbe))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TimeFrameLine [id=" + id + ", lessonNumbe=" + lessonNumbe + ", begin=" + begin + ", end=" + end + "]";
    }
}
