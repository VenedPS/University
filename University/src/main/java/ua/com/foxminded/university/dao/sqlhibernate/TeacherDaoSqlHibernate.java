package ua.com.foxminded.university.dao.sqlhibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.exception.TeacherNotFoundException;
import ua.com.foxminded.university.util.HibernateSessionFactory;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.exception.TeacherNotChangedException;

@Repository
public class TeacherDaoSqlHibernate implements TeacherDao {

    private final Logger logger = LoggerFactory.getLogger(TeacherDaoSqlHibernate.class);

    @Override
    public List<TeacherEntity> readAll() throws TeacherNotFoundException {
        logger.info("Start reading all teachers");
        List<TeacherEntity> teachers = new ArrayList<>();
        
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            TypedQuery<TeacherEntity> query = session.createQuery("FROM TeacherEntity", TeacherEntity.class);
            teachers = query.getResultList();
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (teachers.isEmpty()) {
            throw new TeacherNotFoundException();
        }
        logger.info("All teachers was read from the DB!");
        return teachers;
    }

    @Override
    public TeacherEntity readById(int id) throws TeacherNotFoundException {
        logger.info("Start reading teacher with id={}", id);
        TeacherEntity teacherEntity = new TeacherEntity();
        try {
            teacherEntity = HibernateSessionFactory.getSessionFactory().openSession().get(TeacherEntity.class, id);
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (teacherEntity == null) {
            throw new TeacherNotFoundException(id);
        }
        logger.info("Teacher with id={} was read from the DB!", id);
        return teacherEntity;
    }

    @Override
    public void create(TeacherEntity teacher) throws TeacherNotChangedException {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher can not be null!");
        }

        logger.info("Start creating teacher with id={}", teacher.getId());

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();
            session.close();
            logger.info("Teacher with id={} was created in the DB!", teacher.getId());
        } catch (DataAccessException e) {
            throw new TeacherNotChangedException(teacher.getId(), e);
        }

    }

    @Override
    public void update(TeacherEntity teacher) throws TeacherNotChangedException {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher can not be null!");
        }

        logger.info("Start updating teacher with id={}", teacher.getId());

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();
            session.close();
            logger.info("Teacher with id={} was updated in the DB!", teacher.getId());
        } catch (DataAccessException e) {
            throw new TeacherNotChangedException(teacher.getId(), e);
        }
    }

    @Override
    public void delete(int id) throws TeacherNotChangedException {
        logger.info("Start deleting teacher with id={}", id);
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(readById(id));
            transaction.commit();
            session.close();
            logger.info("Teacher with id={} was deleted from the DB!", id);
        } catch (DataAccessException e) {
            throw new TeacherNotChangedException(id, e);
        }
    }
    
    @Override
    public List<LessonEntity> getTeacherLessons(int teacherId, LocalDate startDate, LocalDate endDate)
            throws LessonNotFoundException {
        
        logger.info("Start getting teacher lessons with teacherId={} from startDate={} to endDate={}", teacherId,
                startDate.toString(), endDate.toString());
        
        List<LessonEntity> lessons = new ArrayList<>();
        try {
            lessons = readById(teacherId).getLessons().stream()
                    .filter(lesson -> lesson.getDate().isAfter(startDate))
                    .filter(lesson -> lesson.getDate().isBefore(endDate))
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (lessons.isEmpty()) {
            throw new LessonNotFoundException(
                    String.format("Empty lessons list for teacherId=%d from startDate=%s to endDate=%s", teacherId,
                            startDate.toString(), endDate.toString()));
        }
        logger.info("All teacher lessons from DB was read!");
        return lessons;
    }

}
