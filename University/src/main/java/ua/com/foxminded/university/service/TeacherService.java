package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.TeacherDto;

public interface TeacherService {
    public List<TeacherDto> readAll();
    public TeacherDto readById(int id);
    public void create(TeacherDto teacherDto);
    public void update(TeacherDto teacherDto);
    public void delete(int id);
}
