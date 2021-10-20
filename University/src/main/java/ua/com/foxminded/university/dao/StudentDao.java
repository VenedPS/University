package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.dao.exception.EntityNotChangedException;
import ua.com.foxminded.university.dao.exception.EntityNotFoundException;
import ua.com.foxminded.university.entity.StudentEntity;

public interface StudentDao {
    public List<StudentEntity> readAll() throws EntityNotFoundException;

    public StudentEntity readById(int id) throws EntityNotFoundException;

    public void create(StudentEntity student) throws EntityNotChangedException;

    public void update(StudentEntity student) throws EntityNotChangedException;

    public void delete(int id) throws EntityNotChangedException;
}
