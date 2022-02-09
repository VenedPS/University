package ua.com.foxminded.university.dao.sqlhibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.util.HibernateSessionFactory;

@Repository
public class LessonDaoSqlHibernate implements LessonDao {

    private final Logger logger = LoggerFactory.getLogger(LessonDaoSqlHibernate.class);

    @Override
    public List<LessonEntity> readAll() throws LessonNotFoundException {
        logger.info("Start reading all lessons");
        List<LessonEntity> lessons = new ArrayList<>();
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            TypedQuery<LessonEntity> query = session.createQuery("FROM LessonEntity", LessonEntity.class);
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
            lessonEntity = HibernateSessionFactory.getSessionFactory().openSession().get(LessonEntity.class, id);
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
