package ua.com.foxminded.university.dao;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.university.entity.CourseEntity;

public interface CourseDao extends CrudRepository<CourseEntity, Integer>{
    
}
