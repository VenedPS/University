package ua.com.foxminded.university.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.CourseConverter;
import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.exception.CourseNotChangedException;
import ua.com.foxminded.university.exception.CourseNotFoundException;
import ua.com.foxminded.university.dto.CourseDto;
import ua.com.foxminded.university.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseConverter courseConverter;
    
    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    
    @Override
    public List<CourseDto> readAll() throws CourseNotFoundException {
        List<CourseDto> courseDto = courseConverter.toDtoList(courseDao.findAll());
        return courseDto;
    }

    @Override
    public CourseDto readById(int id) throws CourseNotFoundException {
        CourseDto courseDto = new CourseDto();
        courseDto = courseConverter.toDto(courseDao.findById(id).get());
        return courseDto;
    }

    @Override
    public void create(CourseDto courseDto) throws CourseNotChangedException {
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
