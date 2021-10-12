package ua.com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.dto.LessonDto;

public interface LessonService {
    public List<LessonDto> readAll();
    public LessonDto readById(int id);
    public List<LessonDto> getStudentLessons(int studentId, LocalDate startDate, LocalDate endDate);
    public List<LessonDto> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate);
}
