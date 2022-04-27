package ua.com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.TeacherEntity;

public interface TeacherService {
    public List<TeacherEntity> readAll();
    public TeacherEntity readById(int id);
    public void create(TeacherEntity teacherEntity);
    public void update(TeacherEntity teacherEntity);
    public void delete(int id);
    public List<LessonEntity> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate);
}
