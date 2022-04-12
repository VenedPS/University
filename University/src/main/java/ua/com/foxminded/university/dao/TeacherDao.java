package ua.com.foxminded.university.dao;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.university.entity.TeacherEntity;

public interface TeacherDao extends CrudRepository<TeacherEntity, Integer>, CustomizedTeacherDao {

}
