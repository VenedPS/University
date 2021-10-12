package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;

public interface LessonDao {
    public List<LessonEntity> readAll();

    public LessonEntity readById(int id);

    public List<LessonEntity> getStudentLessons(int studentId, LocalDate startDate, LocalDate endDate);

    public List<LessonEntity> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate);
}
