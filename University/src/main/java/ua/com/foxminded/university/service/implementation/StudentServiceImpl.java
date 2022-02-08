package ua.com.foxminded.university.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.StudentConverter;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentConverter studentConverter;
    
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
