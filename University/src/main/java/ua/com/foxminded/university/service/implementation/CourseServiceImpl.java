package ua.com.foxminded.university.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.CourseConverter;
import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.dto.CourseDto;
import ua.com.foxminded.university.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseConverter courseConverter;    
    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao, CourseConverter courseConverter) {
        this.courseDao = courseDao;
        this.courseConverter = courseConverter;
    }
    
    @Override
    public List<CourseDto> readAll() {
        List<CourseDto> courseDto = courseConverter.toDtoList(courseDao.findAll());
        return courseDto;
    }

    @Override
    public CourseDto readById(int id) {
        CourseDto courseDto = new CourseDto();
        courseDto = courseConverter.toDto(courseDao.findById(id).get());
        return courseDto;
    }

    @Override
    public void create(CourseDto courseDto) {
        courseDao.save(courseConverter.toEntity(courseDto));
    }

    public CourseConverter getStudentConverter() {
        return courseConverter;
    }

    public void setCourseConverter(CourseConverter courseConverter) {
        this.courseConverter = courseConverter;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setStudentDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

}
