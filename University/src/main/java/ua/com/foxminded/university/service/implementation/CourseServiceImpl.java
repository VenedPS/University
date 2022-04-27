package ua.com.foxminded.university.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.entity.CourseEntity;
import ua.com.foxminded.university.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    
    @Override
    public List<CourseEntity> readAll() {
        return StreamSupport.stream(courseDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public CourseEntity readById(int id) {
        return courseDao.findById(id).get();
    }

    @Override
    public void create(CourseEntity courseEntity) {
        courseDao.save(courseEntity);
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setStudentDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

}
