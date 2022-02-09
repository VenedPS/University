package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.entity.GroupEntity;
import ua.com.foxminded.university.exception.GroupNotChangedException;
import ua.com.foxminded.university.exception.GroupNotFoundException;

public interface GroupDao {
    public List<GroupEntity> readAll() throws GroupNotFoundException;

    public GroupEntity readById(int id) throws GroupNotFoundException;

    public void create(GroupEntity student) throws GroupNotChangedException;
}
