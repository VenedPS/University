package ua.com.foxminded.university.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonConverter lessonConverter;
    
    private LessonDao lessonDao;
    
    @Autowired
    public LessonServiceImpl(@Qualifier("lessonDaoSqlHibernate") LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<LessonDto> readAll() throws LessonNotFoundException {
        return lessonConverter.toDtoList(lessonDao.readAll());
    }

    @Override
    public LessonDto readById(int id) throws LessonNotFoundException {
        return lessonConverter.toDto(lessonDao.readById(id));
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
