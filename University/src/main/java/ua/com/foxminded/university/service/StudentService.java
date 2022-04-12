package ua.com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;

public interface StudentService {
    public List<StudentDto> readAll() throws StudentNotFoundException;
    public StudentDto readById(int id) throws StudentNotFoundException;
    public void create(StudentDto studentDto)throws StudentNotChangedException;
    public void update(StudentDto studentDto)throws StudentNotChangedException;
    public void delete(int id)throws StudentNotChangedException;
    public List<LessonDto> getStudentLessons(StudentDto studentDto, LocalDate startDate, LocalDate endDate);
}
