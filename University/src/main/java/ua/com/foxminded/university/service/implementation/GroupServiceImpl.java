package ua.com.foxminded.university.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.converter.GroupConverter;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dto.GroupDto;
import ua.com.foxminded.university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	private GroupConverter groupConverter;
    private GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao, GroupConverter groupConverter) {
        this.groupDao = groupDao;
        this.groupConverter = groupConverter;
    }

    @Override
    public List<GroupDto> readAll() {
        List<GroupDto> groupDto = groupConverter.toDtoList(groupDao.findAll());
        return groupDto;
    }

    @Override
    public GroupDto readById(int id) {
        return groupConverter.toDto(groupDao.findById(id).get());
    }
    
    @Override
    public void create(GroupDto groupDto) {
        groupDao.save(groupConverter.toEntity(groupDto));
    }

    public GroupConverter getStudentConverter() {
        return groupConverter;
    }

    public void setGroupConverter(GroupConverter groupConverter) {
        this.groupConverter = groupConverter;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setStudentDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

}
