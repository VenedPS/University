package ua.com.foxminded.university.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.service.LessonService;

public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonConverter lessonConverter;
    
    private LessonDao lessonDao;
    
    @Autowired
    public LessonServiceImpl(LessonDao lessonDao) {
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

    @Override
    public List<LessonDto> getStudentLessons(int studentId, LocalDate startDate, LocalDate endDate) throws LessonNotFoundException {
        return lessonConverter.toDtoList(lessonDao.getStudentLessons(studentId, startDate, endDate));
    }

    @Override
    public List<LessonDto> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate) throws LessonNotFoundException {
        return lessonConverter.toDtoList(lessonDao.getTeacherLessons(teacherId, startDate, endDate));
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
