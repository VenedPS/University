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

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.entity.CourseEntity;
import ua.com.foxminded.university.exception.CourseNotChangedException;
import ua.com.foxminded.university.exception.CourseNotFoundException;
import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;

@Repository
@Transactional
public class CourseDaoSqlHibernate implements CourseDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final Logger logger = LoggerFactory.getLogger(StudentDaoSqlHibernate.class);

    @Override
    public List<CourseEntity> readAll() throws CourseNotFoundException {
        logger.info("Start reading all courses");
        List<CourseEntity> courses = new ArrayList<>();

        try {
            TypedQuery<CourseEntity> query = entityManager.createQuery("FROM CourseEntity", CourseEntity.class);
            courses = query.getResultList();
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (courses.isEmpty()) {
            throw new StudentNotFoundException();
        }

        logger.info("All courses was read from the DB!");
        return courses;
    }

    @Override
    public CourseEntity readById(int id) throws CourseNotFoundException {
        logger.info("Start reading course with id={}", id);
        CourseEntity courseEntity = null;
        try {
            courseEntity = entityManager.find(CourseEntity.class, id);
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (courseEntity == null) {
            throw new StudentNotFoundException(id);
        }
        logger.info("Course with id={} was read from the DB!", id);
        return courseEntity;
    }

    @Override
    public void create(CourseEntity course) throws CourseNotChangedException {
        if (course == null) {
            throw new IllegalArgumentException("Course can not be null!");
        }

        logger.info("Start creating course with id={}", course.getId());

        try {
            entityManager.persist(course);
            logger.info("Course with id={} was created in the DB!", course.getId());
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(course.getId(), e);
        }
        
    }

}
