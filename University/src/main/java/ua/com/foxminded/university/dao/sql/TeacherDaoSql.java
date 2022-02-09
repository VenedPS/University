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

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.exception.TeacherNotFoundException;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.exception.TeacherNotChangedException;

@Repository
public class TeacherDaoSql implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM teachers";
    private final String SQL_READ_BY_ID = "SELECT * FROM teachers WHERE id=?";
    private final String SQL_CREATE = "INSERT INTO teachers VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE teachers SET first_name=?, second_name=?, birth_date=?, address=?, phone=?, email=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM teachers WHERE id=?";
    private final String SQL_TEACHER_LESSONS_FOR_MONTH = "SELECT lessons.* " + "FROM lessons "
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id "
            + "WHERE lessons.teacher_id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";
    
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
            teacherEntity = jdbcTemplate.query(SQL_READ_BY_ID, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id);
                }
            }, new BeanPropertyRowMapper<>(TeacherEntity.class)).stream().findAny().orElse(null);
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
            jdbcTemplate.update(SQL_CREATE, teacher.getId(), teacher.getFirstName(), teacher.getSecondName(),
                    teacher.getBirthDate(), teacher.getAddress(), teacher.getPhone(), teacher.getEmail());
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
            jdbcTemplate.update(SQL_UPDATE, teacher.getFirstName(), teacher.getSecondName(), teacher.getBirthDate(),
                    teacher.getAddress(), teacher.getPhone(), teacher.getEmail(), teacher.getId());
            logger.info("Teacher with id={} was updated in the DB!", teacher.getId());
        } catch (DataAccessException e) {
            throw new TeacherNotChangedException(teacher.getId(), e);
        }
    }

    @Override
    public void delete(int id) throws TeacherNotChangedException {
        logger.info("Start deleting teacher with id={}", id);
        try {
            jdbcTemplate.update(SQL_DELETE, id);
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
            lessons = jdbcTemplate.query(SQL_TEACHER_LESSONS_FOR_MONTH, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, teacherId);
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
                    String.format("Empty lessons list for teacherId=%d from startDate=%s to endDate=%s", teacherId,
                            startDate.toString(), endDate.toString()));
        }
        logger.info("All teacher lessons from DB was read!");
        return lessons;
    }

}
