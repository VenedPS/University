package ua.com.foxminded.university.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;
    
    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<TeacherEntity> readAll() {
        return StreamSupport.stream(teacherDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public TeacherEntity readById(int id) {
    	return teacherDao.findById(id).get();
    }
    
    @Override
    public void create(TeacherEntity teacherEntity) {
        teacherDao.save(teacherEntity);
    }

    @Override
    public void update(TeacherEntity teacherEntity) {
        teacherDao.save(teacherEntity);
    }

    @Override
    public void delete(int id) {
        teacherDao.deleteById(id);
    }
    
    public List<LessonEntity> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate) {
        return teacherDao.getTeacherLessons(teacherId, startDate, endDate);
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    
}
