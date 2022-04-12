package ua.com.foxminded.university.dao;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.university.entity.StudentEntity;

public interface StudentDao extends CrudRepository<StudentEntity, Integer>, CustomizedStudentDao  {

}
