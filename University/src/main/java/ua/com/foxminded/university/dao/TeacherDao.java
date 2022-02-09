package ua.com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.exception.TeacherNotChangedException;
import ua.com.foxminded.university.exception.TeacherNotFoundException;

public interface TeacherDao {
    public List<TeacherEntity> readAll()  throws TeacherNotFoundException;

    public TeacherEntity readById(int id) throws TeacherNotFoundException;

    public void create(TeacherEntity teacher) throws TeacherNotChangedException;

    public void update(TeacherEntity teacher) throws TeacherNotChangedException;

    public void delete(int id) throws TeacherNotChangedException;
    
    public List<LessonEntity> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate) throws LessonNotFoundException;
}
