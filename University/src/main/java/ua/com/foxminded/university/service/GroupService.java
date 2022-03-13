package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.GroupDto;
import ua.com.foxminded.university.exception.GroupNotChangedException;
import ua.com.foxminded.university.exception.GroupNotFoundException;

public interface GroupService {
    public List<GroupDto> readAll() throws GroupNotFoundException;
    public GroupDto readById(int id) throws GroupNotFoundException;
    public void create(GroupDto groupDto)throws GroupNotChangedException;
}
