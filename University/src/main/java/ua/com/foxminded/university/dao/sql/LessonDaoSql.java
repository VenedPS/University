package ua.com.foxminded.university.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dto.Lesson;

@Component
public class LessonDaoSql implements LessonDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM lessons";
    private final String SQL_READ_BY_ID = "SELECT * FROM lessons WHERE id=?";

    @Autowired
    public LessonDaoSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Lesson> readAll() {
        return jdbcTemplate.query(SQL_READ_ALL, new BeanPropertyRowMapper<>(Lesson.class));
    }

    @Override
    public Lesson readById(int id) {
        return jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        }, new BeanPropertyRowMapper<>(Lesson.class)).stream().findAny().orElse(null);
    }

}
