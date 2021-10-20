package ua.com.foxminded.university.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.StudentConverter;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.exception.EntityNotChangedException;
import ua.com.foxminded.university.dao.exception.EntityNotFoundException;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentConverter studentConverter;
    
    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    
    @Override
    public List<StudentDto> readAll() {
        List<StudentEntity> studentsEntity = new ArrayList<>();
        try {
            studentsEntity = studentDao.readAll();
        } catch (EntityNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("111");
        }
        List<StudentDto> studentsDto = studentConverter.toDtoList(studentsEntity);
        return studentsDto;
    }

    @Override
    public StudentDto readById(int id) {
        StudentDto studentDto = new StudentDto();
        try {
            studentDto = studentConverter.toDto(studentDao.readById(id));
        } catch (EntityNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return studentDto;
    }

    @Override
    public void create(StudentDto studentDto) {
        try {
            studentDao.create(studentConverter.toEntity(studentDto));
        } catch (EntityNotChangedException e) {
            // TODO Auto-generated catch block
            System.out.println("111");
        }
    }

    @Override
    public void update(StudentDto studentDto) {
        try {
            studentDao.update(studentConverter.toEntity(studentDto));
        } catch (EntityNotChangedException e) {
            // TODO Auto-generated catch block
            System.out.println("111");
        }
    }

    @Override
    public void delete(int id) {
        try {
            studentDao.delete(id);
        } catch (EntityNotChangedException e) {
            // TODO Auto-generated catch block
            System.out.println("111");
        }
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
