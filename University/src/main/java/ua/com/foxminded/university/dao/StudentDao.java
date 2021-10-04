package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.dto.Student;

public interface StudentDao {
    public List<Student> readAll();
    public Student readById(int id);
    public void create(Student student);
    public void update(Student student);
    public void delete(int id);
}
