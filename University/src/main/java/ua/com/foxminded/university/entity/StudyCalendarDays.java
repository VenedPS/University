package ua.com.foxminded.university.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "study_calendar_days")
public class StudyCalendarDays {
    
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.ORDINAL)
    private DayType dayType;

    public StudyCalendarDays(Integer id, LocalDate date, DayType dayType) {
        this.id = id;
        this.date = date;
        this.dayType = dayType;
    }
    
    public StudyCalendarDays(StudyCalendarDays studyCalendarDays) {
        this.id = studyCalendarDays.getId();
        this.date = studyCalendarDays.getDate();
        this.dayType = studyCalendarDays.getDayType();
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

    public DayType getDayType() {
        return dayType;
    }

    public void setDayType(DayType dayType) {
        this.dayType = dayType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((dayType == null) ? 0 : dayType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        StudyCalendarDays other = (StudyCalendarDays) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (dayType != other.dayType)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "StudyCalendarDays [id=" + id + ", date=" + date + ", dayType=" + dayType + "]";
    }
}
