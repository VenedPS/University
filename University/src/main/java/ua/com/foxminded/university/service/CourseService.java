package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.CourseDto;

public interface CourseService {
    public List<CourseDto> readAll();
    public CourseDto readById(int id);
    public void create(CourseDto courseDto);
}
