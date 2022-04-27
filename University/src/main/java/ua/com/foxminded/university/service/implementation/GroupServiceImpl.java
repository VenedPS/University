package ua.com.foxminded.university.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.entity.GroupEntity;
import ua.com.foxminded.university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public List<GroupEntity> readAll() {
        return StreamSupport.stream(groupDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public GroupEntity readById(int id) {
        return groupDao.findById(id).get();
    }
    
    @Override
    public void create(GroupEntity groupEntity) {
        groupDao.save(groupEntity);
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setStudentDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

}
