package ua.com.foxminded.university.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.converter.StudentConverter;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentConverter studentConverter;
    
    @Autowired
    private LessonConverter lessonConverter;
    
    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(@Qualifier("studentDaoSqlHibernate") StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    
    @Override
    public List<StudentDto> readAll() throws StudentNotFoundException {
        List<StudentEntity> studentsEntity = new ArrayList<>();
        studentsEntity = studentDao.readAll();
        List<StudentDto> studentsDto = studentConverter.toDtoList(studentsEntity);
        return studentsDto;
    }

    @Override
    public StudentDto readById(int id) throws StudentNotFoundException {
        StudentDto studentDto = new StudentDto();
        studentDto = studentConverter.toDto(studentDao.readById(id));
        return studentDto;
    }

    @Override
    public void create(StudentDto studentDto) throws StudentNotChangedException {
        studentDao.create(studentConverter.toEntity(studentDto));
    }

    @Override
    public void update(StudentDto studentDto) throws StudentNotChangedException {
        studentDao.update(studentConverter.toEntity(studentDto));
    }

    @Override
    public void delete(int id) throws StudentNotChangedException {
        studentDao.delete(id);
    }

    @Override
    public List<LessonDto> getStudentLessons(int studentId, LocalDate startDate, LocalDate endDate) throws LessonNotFoundException {
        return lessonConverter.toDtoList(studentDao.getStudentLessons(studentId, startDate, endDate));
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
