package ua.com.foxminded.university.dao.sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.exception.StudentNotChangedException;
import ua.com.foxminded.university.exception.StudentNotFoundException;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;

@Repository
public class StudentDaoSql implements StudentDao {

    private final JdbcTemplate jdbcTemplate;
    
    private final String SQL_READ_ALL = "SELECT * FROM students";
    private final String SQL_READ_BY_ID = "SELECT * FROM students WHERE id=?";
    private final String SQL_CREATE = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE students SET group_id=?, first_name=?, second_name=?, birth_date=?, address=?, phone=?, email=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM students WHERE id=?";
    private final String SQL_STUDENT_LESSONS_FOR_MONTH = "SELECT lessons.* " + "FROM lessons "
            + "INNER JOIN students ON students.group_id = lessons.group_id "
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id "
            + "WHERE students.id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";
    
    private final Logger logger = LoggerFactory.getLogger(StudentDaoSql.class);
    
    @Autowired
    public StudentDaoSql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StudentEntity> readAll() throws StudentNotFoundException{
        logger.info("Start reading all students");
        List<StudentEntity> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query(SQL_READ_ALL, new BeanPropertyRowMapper<>(StudentEntity.class));
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
    public StudentEntity readById(int id) throws StudentNotFoundException{
        logger.info("Start reading student with id={}",id);
        StudentEntity studentEntity = null;
        try {
            studentEntity = jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id);
                }
            }, new BeanPropertyRowMapper<>(StudentEntity.class)).stream().findAny().orElse(null);
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
    public void create(StudentEntity student) throws StudentNotChangedException{
        if (student == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }
        
        logger.info("Start creating student with id={}",student.getId());
        
        try {
            jdbcTemplate.update(SQL_CREATE, student.getId(), student.getGroup(), student.getFirstName(),
                    student.getSecondName(), student.getBirthDate(), student.getAddress(), student.getPhone(),
                    student.getEmail());
            logger.info("Student with id={} was created in the DB!", student.getId());
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(student.getId(),e);
        }
    }

    @Override
    public void update(StudentEntity student) throws StudentNotChangedException{
        if (student == null) {
            throw new IllegalArgumentException("Student can not be null!");
        }

        logger.info("Start updating student with id={}",student.getId());
        
        try {
            jdbcTemplate.update(SQL_UPDATE, student.getGroup(), student.getFirstName(), student.getSecondName(),
                    student.getBirthDate(), student.getAddress(), student.getPhone(), student.getEmail(),
                    student.getId());
            logger.info("Student with id={} was updated in the DB!", student.getId());
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(student.getId(), e);
        }
    }

    @Override
    public void delete(int id) throws StudentNotChangedException{
        logger.info("Start deleting student with id={}",id);
        try {
            jdbcTemplate.update(SQL_DELETE, id);
            logger.info("Student with id={} was deleted from the DB!", id);
        } catch (DataAccessException e) {
            throw new StudentNotChangedException(id, e);
        }
    }

    @Override
    public List<LessonEntity> getStudentLessons(int studentId, LocalDate startDate, LocalDate endDate)
            throws LessonNotFoundException {
        logger.info("Start getting student lessons with studentId={} from startDate={} to endDate={}", studentId,
                startDate.toString(), endDate.toString());
        List<LessonEntity> lessons = new ArrayList<>();
        try {
            lessons = jdbcTemplate.query(SQL_STUDENT_LESSONS_FOR_MONTH, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, studentId);
                    ps.setInt(2, startDate.getYear());
                    ps.setDate(3, Date.valueOf(startDate));
                    ps.setDate(4, Date.valueOf(endDate));
                }
            }, new BeanPropertyRowMapper<>(LessonEntity.class));
        } catch (DataAccessException e) {
            logger.error("DB not available! Reason: {}", e.getMessage());
        }
        if (lessons.isEmpty()) {
            throw new LessonNotFoundException(
                    String.format("Empty lessons list for studentId=%d from startDate=%s to endDate=%s", studentId,
                            startDate.toString(), endDate.toString()));
        }
        logger.info("All student lessons from DB was read!");
        return lessons;
    }

}
