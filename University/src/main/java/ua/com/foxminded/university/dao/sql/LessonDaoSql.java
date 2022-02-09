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

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;

@Repository
public class LessonDaoSql implements LessonDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM lessons";
    private final String SQL_READ_BY_ID = "SELECT * FROM lessons WHERE id=?";

    private final Logger logger = LoggerFactory.getLogger(LessonDaoSql.class);

    @Autowired
    public LessonDaoSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LessonEntity> readAll() throws LessonNotFoundException {
        logger.info("Start reading all lessons");
        List<LessonEntity> lessons = new ArrayList<>();
        try {
            lessons = jdbcTemplate.query(SQL_READ_ALL, new BeanPropertyRowMapper<>(LessonEntity.class));
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
            lessonEntity = jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id);
                }
            }, new BeanPropertyRowMapper<>(LessonEntity.class)).stream().findAny().orElse(null);
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
