package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;
import ua.com.foxminded.university.entity.StudentEntity;

public interface StudentDao {
    public List<StudentEntity> readAll() throws StudentNotFoundException;

    public StudentEntity readById(int id) throws StudentNotFoundException;

    public void create(StudentEntity student) throws StudentNotChangedException;

    public void update(StudentEntity student) throws StudentNotChangedException;

    public void delete(int id) throws StudentNotChangedException;
}
