package ua.com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.TeacherDto;

public interface TeacherService {
    public List<TeacherDto> readAll();
    public TeacherDto readById(int id);
    public void create(TeacherDto teacherDto);
    public void update(TeacherDto teacherDto);
    public void delete(int id);
    public List<LessonDto> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate);
}
