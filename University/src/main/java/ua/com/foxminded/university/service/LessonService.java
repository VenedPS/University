package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.LessonDto;

public interface LessonService {
    public List<LessonDto> readAll();
    public LessonDto readById(int id);
}
