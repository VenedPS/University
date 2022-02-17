package ua.com.foxminded.university.dao.sqlhibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.entity.GroupEntity;
import ua.com.foxminded.university.exception.GroupNotChangedException;
import ua.com.foxminded.university.exception.GroupNotFoundException;
import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;

@Repository
@Transactional
public class GroupDaoSqlHibernate implements GroupDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final Logger logger = LoggerFactory.getLogger(StudentDaoSqlHibernate.class);

    @Override
    public List<GroupEntity> readAll()  throws GroupNotFoundException {
        logger.info("Start reading all groups");
        List<GroupEntity> groups = new ArrayList<>();

        try {
            TypedQuery<GroupEntity> query = entityManager.createQuery("FROM GroupEntity", GroupEntity.class);
            groups = query.getResultList();
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (groups.isEmpty()) {
            throw new StudentNotFoundException();
        }

        logger.info("All groups was read from the DB!");
        return groups;
    }

    @Override
    public GroupEntity readById(int id)  throws GroupNotFoundException {
        logger.info("Start reading group with id={}", id);
        GroupEntity groupEntity = null;
        try {
            groupEntity = entityManager.find(GroupEntity.class, id);
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (groupEntity == null) {
            throw new StudentNotFoundException(id);
        }
        logger.info("Group with id={} was read from the DB!", id);
        return groupEntity;
    }

    @Override
    public void create(GroupEntity group) throws GroupNotChangedException {
        if (group == null) {
            throw new IllegalArgumentException("Group can not be null!");
        }

        logger.info("Start creating group with id={}", group.getId());

        try {
            entityManager.persist(group);
            logger.info("Group with id={} was created in the DB!", group.getId());
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(group.getId(), e);
        }
        
    }

}

