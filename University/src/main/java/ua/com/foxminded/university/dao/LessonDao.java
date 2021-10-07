package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.dto.Lesson;

public interface LessonDao {
    public List<Lesson> readAll();
    public Lesson readById(int id);
    public List<Lesson> getStudentLessons(int studentId, int year, LocalDate startDate, LocalDate endDate);
    public List<Lesson> getTeacherLessons(int teacherId, int year, LocalDate startDate, LocalDate endDate);
}
