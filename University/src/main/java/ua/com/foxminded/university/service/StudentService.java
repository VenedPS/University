package ua.com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;

public interface StudentService {
    public List<StudentEntity> readAll();
    public StudentEntity readById(int id);
    public void create(StudentEntity studentEntity);
    public void update(StudentEntity studentEntity);
    public void delete(int id);
    public List<LessonEntity> getStudentLessons(StudentEntity studentEntity, LocalDate startDate, LocalDate endDate);
}
