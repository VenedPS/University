package ua.com.foxminded.university.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.converter.StudentConverter;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentConverter studentConverter;    
    private LessonConverter lessonConverter;    
    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(
    		StudentDao studentDao, 
    		StudentConverter studentConverter, 
    		LessonConverter lessonConverter) {
    	
        this.studentDao = studentDao;
        this.studentConverter = studentConverter;
        this.lessonConverter = lessonConverter;
    }
    
    @Override
    public List<StudentDto> readAll() {
        Iterable<StudentEntity> studentsEntity = new ArrayList<>();
        studentsEntity = studentDao.findAll();
        List<StudentDto> studentsDto = studentConverter.toDtoList(studentsEntity);
        return studentsDto;
    }

    @Override
    public StudentDto readById(int id) {
        StudentDto studentDto = new StudentDto();
        studentDto = studentConverter.toDto(studentDao.findById(id).get());
        return studentDto;
    }

    @Override
    public void create(StudentDto studentDto) {
        studentDao.save(studentConverter.toEntity(studentDto));
    }

    @Override
    public void update(StudentDto studentDto) {
        studentDao.save(studentConverter.toEntity(studentDto));
    }

    @Override
    public void delete(int id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<LessonDto> getStudentLessons(StudentDto studentDto, LocalDate startDate, LocalDate endDate) {
    	return lessonConverter.toDtoList(studentDao.getStudentLessons(studentDto.getGroupId(), startDate, endDate));
    	
    }
    
    public StudentConverter getStudentConverter() {
        return studentConverter;
    }

    public void setStudentConverter(StudentConverter studentConverter) {
        this.studentConverter = studentConverter;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

}
