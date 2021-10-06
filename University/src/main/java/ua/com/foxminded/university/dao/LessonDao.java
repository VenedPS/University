package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import ua.com.foxminded.university.dto.Lesson;
import ua.com.foxminded.university.dto.Student;
import ua.com.foxminded.university.dto.Teacher;

public interface LessonDao {
    public List<Lesson> readAll();
    public Lesson readById(int id);
    public List<Lesson> getLessons(Student student, int year, LocalDate date);
    public List<Lesson> getLessons(Student student, int year, Month month);
    public List<Lesson> getLessons(Teacher teacher, int year, LocalDate date);
    public List<Lesson> getLessons(Teacher teacher, int year, Month month);
}
