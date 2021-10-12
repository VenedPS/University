package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.entity.TeacherEntity;

public interface TeacherDao {
    public List<TeacherEntity> readAll();

    public TeacherEntity readById(int id);

    public void create(TeacherEntity teacher);

    public void update(TeacherEntity teacher);

    public void delete(int id);
}
