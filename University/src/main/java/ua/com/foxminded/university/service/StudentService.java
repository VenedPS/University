package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.service.exception.StudentNotChangedException;
import ua.com.foxminded.university.service.exception.StudentNotFoundException;

public interface StudentService {
    public List<StudentDto> readAll() throws StudentNotFoundException;
    public StudentDto readById(int id) throws StudentNotFoundException;
    public void create(StudentDto studentDto)throws StudentNotChangedException;
    public void update(StudentDto studentDto)throws StudentNotChangedException;
    public void delete(int id)throws StudentNotChangedException;
}
