package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.GroupDto;

public interface GroupService {
    public List<GroupDto> readAll();
    public GroupDto readById(int id);
    public void create(GroupDto groupDto);
}
