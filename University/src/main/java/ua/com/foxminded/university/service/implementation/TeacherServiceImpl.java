package ua.com.foxminded.university.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.converter.TeacherConverter;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.exception.TeacherNotChangedException;
import ua.com.foxminded.university.exception.TeacherNotFoundException;
import ua.com.foxminded.university.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherConverter teacherConverter;
    
    @Autowired
    private LessonConverter lessonConverter;

    private TeacherDao teacherDao;
    
    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<TeacherDto> readAll() throws TeacherNotFoundException {
        return teacherConverter.toDtoList(teacherDao.findAll());
    }

    @Override
    public TeacherDto readById(int id) throws TeacherNotFoundException {
    	return teacherConverter.toDto(teacherDao.findById(id).get());
    }

    @Override
    public void create(TeacherDto teacherDto) throws TeacherNotChangedException {
        teacherDao.save(teacherConverter.toEntity(teacherDto));
    }

    @Override
    public void update(TeacherDto teacherDto) throws TeacherNotChangedException {
        teacherDao.save(teacherConverter.toEntity(teacherDto));
    }

    @Override
    public void delete(int id) throws TeacherNotChangedException {
        teacherDao.deleteById(id);
    }
    
    public List<LessonDto> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate) throws LessonNotFoundException  {
        return lessonConverter.toDtoList(teacherDao.getTeacherLessons(teacherId, startDate, endDate));
    }

    public TeacherConverter getTeacherConverter() {
        return teacherConverter;
    }

    public void setTeacherConverter(TeacherConverter teacherConverter) {
        this.teacherConverter = teacherConverter;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    
}
