package ua.com.foxminded.university.dao;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.university.entity.GroupEntity;

public interface GroupDao extends CrudRepository<GroupEntity, Integer> {

}
