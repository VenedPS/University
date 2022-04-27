package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.entity.CourseEntity;

public interface CourseService {
    public List<CourseEntity> readAll();
    public CourseEntity readById(int id);
    public void create(CourseEntity courseEntity);
}
