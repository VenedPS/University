package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.entity.LessonEntity;

public interface LessonService {
    public List<LessonEntity> readAll();
    public LessonEntity readById(int id);
}
