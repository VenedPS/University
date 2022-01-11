package ua.com.foxminded.university.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.exception.TeacherNotFoundException;
import ua.com.foxminded.university.exception.TeacherNotChangedException;

@Repository
public class TeacherDaoSql implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM teachers";
    private final String SQL_READ_BY_ID = "SELECT * FROM teachers WHERE id=?";
    private final String SQL_CREATE = "INSERT INTO teachers VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE teachers SET first_name=?, second_name=?, birth_date=?, address=?, phone=?, email=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM teachers WHERE id=?";

    private final Logger logger = LoggerFactory.getLogger(TeacherDaoSql.class);

    @Autowired
    public TeacherDaoSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TeacherEntity> readAll() throws TeacherNotFoundException {
        logger.info("Start reading all teachers");
        List<TeacherEntity> teachers = new ArrayList<>();
        try {
            teachers = jdbcTemplate.query(SQL_READ_ALL, new BeanPropertyRowMapper<>(TeacherEntity.class));
            logger.info("All teachers was readed from the DB!");
        } catch (DataAccessException e) {
            throw new TeacherNotFoundException(e);
        }
        return teachers;
    }

    @Override
    public TeacherEntity readById(int id) throws TeacherNotFoundException {
        logger.info(String.format("Start reading teacher with id=%d", id));
        TeacherEntity teacherEntity = new TeacherEntity();
        try {
            teacherEntity = jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id);
                }
            }, new BeanPropertyRowMapper<>(TeacherEntity.class)).stream().findAny().orElse(null);
        } catch (DataAccessException e) {
            throw new TeacherNotFoundException(id, e);
        }
        if (teacherEntity == null) {
            throw new TeacherNotFoundException(id);
        } else {
            logger.info(String.format("Teacher with id=%d was readed from the DB!", id));
        }
        return teacherEntity;
    }

    @Override
    public void create(TeacherEntity teacher) throws TeacherNotChangedException {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher can not be null!");
        }

        logger.info(String.format("Start creating teacher with id=%d", teacher.getId()));

        try {
            jdbcTemplate.update(SQL_CREATE, teacher.getId(), teacher.getFirstName(), teacher.getSecondName(),
                    teacher.getBirthDate(), teacher.getAddress(), teacher.getPhone(), teacher.getEmail());
            logger.info(String.format("Teacher with id=%d was created in the DB!", teacher.getId()));
        } catch (DataAccessException e) {
            throw new TeacherNotChangedException(teacher.getId(), e);
        }

    }

    @Override
    public void update(TeacherEntity teacher) throws TeacherNotChangedException {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher can not be null!");
        }

        logger.info(String.format("Start updating teacher with id=%d", teacher.getId()));

        try {
            jdbcTemplate.update(SQL_UPDATE, teacher.getFirstName(), teacher.getSecondName(), teacher.getBirthDate(),
                    teacher.getAddress(), teacher.getPhone(), teacher.getEmail(), teacher.getId());
            logger.info(String.format("Teacher with id=%d was updated in the DB!", teacher.getId()));
        } catch (DataAccessException e) {
            throw new TeacherNotChangedException(teacher.getId(), e);
        }
    }

    @Override
    public void delete(int id) throws TeacherNotChangedException {
        logger.info(String.format("Start deleting teacher with id=%d", id));
        try {
            jdbcTemplate.update(SQL_DELETE, id);
            logger.info(String.format("Teacher with id=%d was deleted from the DB!", id));
        } catch (DataAccessException e) {
            throw new TeacherNotChangedException(id, e);
        }
    }

}
