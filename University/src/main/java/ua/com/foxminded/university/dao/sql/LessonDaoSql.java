package ua.com.foxminded.university.dao.sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dto.Lesson;

@Repository
public class LessonDaoSql implements LessonDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM lessons";
    private final String SQL_READ_BY_ID = "SELECT * FROM lessons WHERE id=?";
    private final String SQL_STUDENT_LESSONS_FOR_MONTH = 
            "SELECT lessons.* " 
            + "FROM lessons "
            + "INNER JOIN students ON students.group_id = lessons.group_id "
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id "
            + "WHERE students.id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";
    private final String SQL_TEACHER_LESSONS_FOR_MONTH = 
            "SELECT lessons.* " 
            + "FROM lessons "
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id "
            + "WHERE lessons.teacher_id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";

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

    @Override
    public List<Lesson> getStudentLessons(int studentId, int year, LocalDate startDate, LocalDate endDate) {
        return jdbcTemplate.query(SQL_STUDENT_LESSONS_FOR_MONTH, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, studentId);
                ps.setInt(2, year);
                ps.setDate(3, Date.valueOf(startDate));
                ps.setDate(4, Date.valueOf(endDate));
            }
        }, new BeanPropertyRowMapper<>(Lesson.class));
    }

    @Override
    public List<Lesson> getTeacherLessons(int teacherId, int year, LocalDate startDate, LocalDate endDate) {
        return jdbcTemplate.query(SQL_TEACHER_LESSONS_FOR_MONTH, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, teacherId);
                ps.setInt(2, year);
                ps.setDate(3, Date.valueOf(startDate));
                ps.setDate(4, Date.valueOf(endDate));
            }
        }, new BeanPropertyRowMapper<>(Lesson.class));
    }

}
