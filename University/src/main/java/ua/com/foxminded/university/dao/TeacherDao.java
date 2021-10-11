package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.entity.Teacher;

public interface TeacherDao {
    public List<Teacher> readAll();
    public Teacher readById(int id);
    public void create(Teacher teacher);
    public void update(Teacher teacher);
    public void delete(int id);
}
