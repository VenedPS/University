package ua.com.foxminded.university.dao.sqlhibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;
import ua.com.foxminded.university.util.HibernateSessionFactory;
import ua.com.foxminded.university.entity.StudentEntity;

@Repository
public class StudentDaoSqlHibernate implements StudentDao {

    private final Logger logger = LoggerFactory.getLogger(StudentDaoSqlHibernate.class);

    @Override
    public List<StudentEntity> readAll() throws StudentNotFoundException {
        logger.info("Start reading all students");
        List<StudentEntity> students = new ArrayList<>();

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<StudentEntity> criteriaQuery = criteriaBuilder.createQuery(StudentEntity.class);
            Root<StudentEntity> rootEntry = criteriaQuery.from(StudentEntity.class);
            CriteriaQuery<StudentEntity> all = criteriaQuery.select(rootEntry);
            TypedQuery<StudentEntity> allQuery = session.createQuery(all);
            students = allQuery.getResultList();
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (students.isEmpty()) {
            throw new StudentNotFoundException();
        }

        logger.info("All students was read from the DB!");
        return students;
    }

    @Override
    public StudentEntity readById(int id) throws StudentNotFoundException {
        logger.info("Start reading student with id={}", id);
        StudentEntity studentEntity = null;
        try {
            studentEntity = HibernateSessionFactory.getSessionFactory().openSession().get(StudentEntity.class, id);
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (studentEntity == null) {
            throw new StudentNotFoundException(id);
        }
        logger.info("Student with id={} was read from the DB!", id);
        return studentEntity;
    }

    @Override
    public void create(StudentEntity student) throws StudentNotChangedException {
        if (student == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }

        logger.info("Start creating student with id={}", student.getId());

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            session.close();
            logger.info("Student with id={} was created in the DB!", student.getId());
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(student.getId(), e);
        }
    }

    @Override
    public void update(StudentEntity student) throws StudentNotChangedException {
        if (student == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }

        logger.info("Start updating student with id={}", student.getId());

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            session.close();
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(student.getId(), e);
        }
    }

    @Override
    public void delete(int id) throws StudentNotChangedException {
        logger.info("Start deleting student with id={}", id);

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(readById(id));
            transaction.commit();
            session.close();
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(id, e);
        }
    }

}
