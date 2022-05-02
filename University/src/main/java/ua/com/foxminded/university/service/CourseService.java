package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.CourseDto;
import ua.com.foxminded.university.exception.CourseNotChangedException;
import ua.com.foxminded.university.exception.CourseNotFoundException;

public interface CourseService {
    public List<CourseDto> readAll() throws CourseNotFoundException;
    public CourseDto readById(int id) throws CourseNotFoundException;
    public void create(CourseDto courseDto)throws CourseNotChangedException;
}
