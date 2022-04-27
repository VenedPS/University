package ua.com.foxminded.university.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

    private LessonDao lessonDao;
    
    @Autowired
    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<LessonEntity> readAll() throws LessonNotFoundException {
        return StreamSupport.stream(lessonDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public LessonEntity readById(int id) throws LessonNotFoundException {
        return lessonDao.findById(id).get();
    }

    public LessonDao getLessonDao() {
        return lessonDao;
    }

    public void setLessonDao(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

}
