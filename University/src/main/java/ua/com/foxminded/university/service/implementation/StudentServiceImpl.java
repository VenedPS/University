package ua.com.foxminded.university.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    
    @Override
    public List<StudentEntity> readAll() {
        return StreamSupport.stream(studentDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public StudentEntity readById(int id) {
        return studentDao.findById(id).get();
    }

    @Override
    public void create(StudentEntity studentEntity) {
        studentDao.save(studentEntity);
    }

    @Override
    public void update(StudentEntity studentEntity) {
        studentDao.save(studentEntity);
    }

    @Override
    public void delete(int id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<LessonEntity> getStudentLessons(StudentEntity studentEntity, LocalDate startDate, LocalDate endDate) {
    	return studentDao.getStudentLessons(studentEntity.getGroup().getId(), startDate, endDate);
    	
    }
    
    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

}
