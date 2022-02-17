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

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;

@Repository
@Transactional
public class LessonDaoSqlHibernate implements LessonDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    private final Logger logger = LoggerFactory.getLogger(LessonDaoSqlHibernate.class);

    @Override
    public List<LessonEntity> readAll() throws LessonNotFoundException {
        logger.info("Start reading all lessons");
        List<LessonEntity> lessons = new ArrayList<>();
        try {
            TypedQuery<LessonEntity> query = entityManager.createQuery("FROM LessonEntity", LessonEntity.class);
            lessons = query.getResultList();
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (lessons.isEmpty()) {
            throw new LessonNotFoundException();
        }
        logger.info("All lessons was read from the DB!");
        return lessons;
    }

    @Override
    public LessonEntity readById(int id) throws LessonNotFoundException {
        logger.info("Start reading lesson with id={}", id);
        LessonEntity lessonEntity = new LessonEntity();
        try {
            lessonEntity = entityManager.find(LessonEntity.class, id);
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (lessonEntity == null) {
            throw new LessonNotFoundException(id);
        }
        logger.info("Lesson with id={} was read from the DB!", id);
        return lessonEntity;
    }

}
