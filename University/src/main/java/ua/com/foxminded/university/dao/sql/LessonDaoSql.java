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

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;

@Repository
public class LessonDaoSql implements LessonDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM lessons";
    private final String SQL_READ_BY_ID = "SELECT * FROM lessons WHERE id=?";
    private final String SQL_STUDENT_LESSONS_FOR_MONTH = "SELECT lessons.* " + "FROM lessons "
            + "INNER JOIN students ON students.group_id = lessons.group_id "
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id "
            + "WHERE students.id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";
    private final String SQL_TEACHER_LESSONS_FOR_MONTH = "SELECT lessons.* " + "FROM lessons "
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id "
            + "WHERE lessons.teacher_id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";

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
