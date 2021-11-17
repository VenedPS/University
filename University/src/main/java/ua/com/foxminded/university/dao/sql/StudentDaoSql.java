package ua.com.foxminded.university.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.exception.EntityNotChangedException;
import ua.com.foxminded.university.dao.exception.EntityNotFoundException;
import ua.com.foxminded.university.entity.StudentEntity;

@Repository
public class StudentDaoSql implements StudentDao {

    private final JdbcTemplate jdbcTemplate;
    
    private final String SQL_READ_ALL = "SELECT * FROM students";
    private final String SQL_READ_BY_ID = "SELECT * FROM students WHERE id=?";
    private final String SQL_CREATE = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE students SET group_id=?, first_name=?, second_name=?, birth_date=?, address=?, phone=?, email=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM students WHERE id=?";

    @Autowired
    public StudentDaoSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StudentEntity> readAll() throws EntityNotFoundException{
        List<StudentEntity> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query(SQL_READ_ALL, new BeanPropertyRowMapper<>(StudentEntity.class));
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Can't read students list", e);
        }
        return students;
    }

    @Override
    public StudentEntity readById(int id) throws EntityNotFoundException{
        StudentEntity studentEntity = null;
        try {
            studentEntity = jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id);
                }
            }, new BeanPropertyRowMapper<>(StudentEntity.class)).stream().findAny().orElse(null);
        } catch (DataAccessException e) {
            throw new EntityNotFoundException(String.format("Can't find student by id='%s'",id), e);
        }
        return studentEntity;
    }

    @Override
    public void create(StudentEntity student) throws EntityNotChangedException{
        if (student == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }

        try {
            jdbcTemplate.update(SQL_CREATE, student.getId(), student.getGroupId(), student.getFirstName(),
                    student.getSecondName(), student.getBirthDate(), student.getAddress(), student.getPhone(),
                    student.getEmail());
        } catch (DataAccessException e) {
            throw new EntityNotChangedException(String.format("Can't create student: '%s'", student.toString()),e);
        }
    }

    @Override
    public void update(StudentEntity student) throws EntityNotChangedException{
        if (student == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }

        try {
            jdbcTemplate.update(SQL_UPDATE, student.getGroupId(), student.getFirstName(), student.getSecondName(),
                    student.getBirthDate(), student.getAddress(), student.getPhone(), student.getEmail(),
                    student.getId());
        } catch (DataAccessException e) {
            throw new EntityNotChangedException(String.format("Can't update student: '%s'", student.toString()), e);
        }
    }

    @Override
    public void delete(int id) throws EntityNotChangedException{
        try {
            jdbcTemplate.update(SQL_DELETE, id);
        } catch (DataAccessException e) {
            throw new EntityNotChangedException(String.format("Can't delete student by id='%s'", id), e);
        }
    }

}
