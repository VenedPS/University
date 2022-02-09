package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;

public interface LessonDao {
    public List<LessonEntity> readAll() throws LessonNotFoundException;

    public LessonEntity readById(int id) throws LessonNotFoundException;

}
