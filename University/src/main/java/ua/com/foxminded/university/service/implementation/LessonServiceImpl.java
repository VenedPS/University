package ua.com.foxminded.university.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

	private LessonConverter lessonConverter;    
    private LessonDao lessonDao;
    
    @Autowired
    public LessonServiceImpl(LessonDao lessonDao, LessonConverter lessonConverter) {
        this.lessonDao = lessonDao;
        this.lessonConverter = lessonConverter;
    }

    @Override
    public List<LessonDto> readAll() {
        return lessonConverter.toDtoList(lessonDao.findAll());
    }

    @Override
    public LessonDto readById(int id) {
        return lessonConverter.toDto(lessonDao.findById(id).get());
    }

    public LessonConverter getLessonConverter() {
        return lessonConverter;
    }

    public void setLessonConverter(LessonConverter lessonConverter) {
        this.lessonConverter = lessonConverter;
    }

    public LessonDao getLessonDao() {
        return lessonDao;
    }

    public void setLessonDao(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

}
