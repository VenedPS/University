package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.StudentDto;

public interface StudentService {
    public List<StudentDto> readAll();
    public StudentDto readById(int id);
    public void create(StudentDto studentDto);
    public void update(StudentDto studentDto);
    public void delete(int id);
}
