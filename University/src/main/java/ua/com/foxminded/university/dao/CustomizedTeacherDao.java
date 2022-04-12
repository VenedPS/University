package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;

public interface CustomizedTeacherDao {
    public List<LessonEntity> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate) throws LessonNotFoundException;
}
