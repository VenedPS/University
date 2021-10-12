package ua.com.foxminded.university.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.foxminded.university.converter.TeacherConverter;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherConverter teacherConverter;

    private TeacherDao teacherDao;
    
    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<TeacherDto> readAll() {
        return teacherConverter.toDtoList(teacherDao.readAll());
    }

    @Override
    public TeacherDto readById(int id) {
        return teacherConverter.toDto(teacherDao.readById(id));
    }

    @Override
    public void create(TeacherDto teacherDto) {
        teacherDao.create(teacherConverter.toEntity(teacherDto));
    }

    @Override
    public void update(TeacherDto teacherDto) {
        teacherDao.update(teacherConverter.toEntity(teacherDto));
    }

    @Override
    public void delete(int id) {
        teacherDao.delete(id);
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
