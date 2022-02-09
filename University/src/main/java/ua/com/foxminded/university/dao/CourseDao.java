package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.entity.CourseEntity;
import ua.com.foxminded.university.exception.CourseNotChangedException;
import ua.com.foxminded.university.exception.CourseNotFoundException;

public interface CourseDao {
    public List<CourseEntity> readAll() throws CourseNotFoundException;

    public CourseEntity readById(int id) throws CourseNotFoundException;

    public void create(CourseEntity student) throws CourseNotChangedException;
}
