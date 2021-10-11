package ua.com.foxminded.university.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.entity.Teacher;

@Repository
public class TeacherDaoSql implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM teachers";
    private final String SQL_READ_BY_ID = "SELECT * FROM teachers WHERE id=?";
    private final String SQL_CREATE = "INSERT INTO teachers VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE teachers SET first_name=?, second_name=?, birth_date=?, address=?, phone=?, email=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM teachers WHERE id=?";

    @Autowired
    public TeacherDaoSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Teacher> readAll() {
        return jdbcTemplate.query(SQL_READ_ALL, new BeanPropertyRowMapper<>(Teacher.class));
    }

    @Override
    public Teacher readById(int id) {
        return jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        }, new BeanPropertyRowMapper<>(Teacher.class)).stream().findAny().orElse(null);
    }

    @Override
    public void create(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }
        
        jdbcTemplate.update(SQL_CREATE, teacher.getId(), teacher.getFirstName(), teacher.getSecondName(),
                teacher.getBirthDate(), teacher.getAddress(), teacher.getPhone(), teacher.getEmail());

    }

    @Override
    public void update(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }
        
        jdbcTemplate.update(SQL_UPDATE, teacher.getFirstName(), teacher.getSecondName(), teacher.getBirthDate(),
                teacher.getAddress(), teacher.getPhone(), teacher.getEmail(), teacher.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

}
