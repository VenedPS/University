package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.entity.StudentEntity;

public interface StudentDao {
    public List<StudentEntity> readAll();

    public StudentEntity readById(int id);

    public void create(StudentEntity student);

    public void update(StudentEntity student);

    public void delete(int id);
}
