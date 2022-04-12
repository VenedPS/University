package ua.com.foxminded.university.dao;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.university.entity.LessonEntity;

public interface LessonDao extends CrudRepository<LessonEntity, Integer>  {

}
