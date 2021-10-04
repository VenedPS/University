package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.dto.Lesson;

public interface LessonDao {
    public List<Lesson> readAll();
    public Lesson readById(int id);
}
