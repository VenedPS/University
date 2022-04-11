package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;

public interface CustomizedStudentDao {
    public List<LessonEntity> getStudentLessons(StudentEntity studentEntity, LocalDate startDate, LocalDate endDate);
}
