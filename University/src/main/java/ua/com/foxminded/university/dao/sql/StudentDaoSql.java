package ua.com.foxminded.university.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dto.Student;

@Component
public class StudentDaoSql implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM students";
    private final String SQL_READ_BY_ID = "SELECT * FROM students WHERE id=?";
    private final String SQL_CREATE = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE students SET group_id=?, first_name=?, last_name=?, birth_date=?, address=?, phone=?, email=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM students WHERE id=?";

    @Autowired
    public StudentDaoSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> readAll() {
        return jdbcTemplate.query(SQL_READ_ALL, new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public Student readById(int id) {
        return jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        }, new BeanPropertyRowMapper<>(Student.class)).stream().findAny().orElse(null);
    }

    @Override
    public void create(Student student) {
        jdbcTemplate.update(SQL_CREATE, student.getId(), student.getGroupId(), student.getFirstName(),
                student.getSecondName(), student.getBirthDate(), student.getAddress(), student.getPhone(),
                student.getEmail());
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update(SQL_UPDATE, student.getGroupId(), student.getFirstName(), student.getSecondName(),
                student.getBirthDate(), student.getAddress(), student.getPhone(), student.getEmail(), student.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

}
