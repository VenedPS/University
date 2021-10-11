package ua.com.foxminded.university.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.StudentConverter;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    
    @Autowired
    private StudentConverter studentConverter;
    
    @Override
    public List<StudentDto> readAll() {
        return studentConverter.toDto(studentDao.readAll());
    }

    @Override
    public StudentDto readById(int id) {
        return studentConverter.toDto(studentDao.readById(id));
    }

    @Override
    public void create(StudentDto studentDto) {
        studentDao.create(studentConverter.toEntity(studentDto));
    }

    @Override
    public void update(StudentDto studentDto) {
        studentDao.update(studentConverter.toEntity(studentDto));
    }

    @Override
    public void delete(int id) {
        studentDao.delete(id);
    }

}
