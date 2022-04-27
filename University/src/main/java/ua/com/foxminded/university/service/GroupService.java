package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.entity.GroupEntity;

public interface GroupService {
    public List<GroupEntity> readAll();
    public GroupEntity readById(int id);
    public void create(GroupEntity groupEntity);
}
