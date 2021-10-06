package ua.com.foxminded.university.dao.sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dto.Lesson;
import ua.com.foxminded.university.dto.Student;
import ua.com.foxminded.university.dto.Teacher;

@Repository
public class LessonDaoSql implements LessonDao {

    private final JdbcTemplate jdbcTemplate;

    private final String SQL_READ_ALL = "SELECT * FROM lessons";
    private final String SQL_READ_BY_ID = "SELECT * FROM lessons WHERE id=?";
    private final String SQL_STUDENT_LESSONS_FOR_DAY = 
            "SELECT lessons.*" 
            + "FROM lessons"
            + "INNER JOIN students ON students.group_id = lessons.group_id"
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id"
            + "WHERE students.id = ? AND timetables.year = ? AND lessons.date = ?";
    private final String SQL_STUDENT_LESSONS_FOR_MONTH = 
            "SELECT lessons.*" 
            + "FROM lessons"
            + "INNER JOIN students ON students.group_id = lessons.group_id"
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id"
            + "WHERE students.id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";
    private final String SQL_TEACHER_LESSONS_FOR_DAY = 
            "SELECT lessons.*" 
            + "FROM lessons"
            + "INNER JOIN teachers ON teachers.course_id = lessons.course_id"
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id"
            + "WHERE teachers.id = ? AND timetables.year = ? AND lessons.date = ?";
    private final String SQL_TEACHER_LESSONS_FOR_MONTH = 
            "SELECT lessons.*" 
            + "FROM lessons"
            + "INNER JOIN teachers ON teachers.course_id = lessons.course_id"
            + "INNER JOIN timetables ON timetables.id = lessons.timetable_id"
            + "WHERE teachers.id = ? AND timetables.year = ? AND lessons.date >= ? AND lessons.date <= ?";

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
    public List<Lesson> getLessons(Student student, int year, LocalDate date) {
        return jdbcTemplate.query(SQL_STUDENT_LESSONS_FOR_DAY, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, student.getId());
                ps.setInt(2, year);
                ps.setDate(3, Date.valueOf(date));
            }
        }, new BeanPropertyRowMapper<>(Lesson.class));
    }

    @Override
    public List<Lesson> getLessons(Student student, int year, Month month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDdate = LocalDate.of(year, month, month.maxLength());
        return jdbcTemplate.query(SQL_STUDENT_LESSONS_FOR_MONTH, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, student.getId());
                ps.setInt(2, year);
                ps.setDate(3, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDdate));
            }
        }, new BeanPropertyRowMapper<>(Lesson.class));
    }

    @Override
    public List<Lesson> getLessons(Teacher teacher, int year, LocalDate date) {
        return jdbcTemplate.query(SQL_TEACHER_LESSONS_FOR_DAY, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, teacher.getId());
                ps.setInt(2, year);
                ps.setDate(3, Date.valueOf(date));
            }
        }, new BeanPropertyRowMapper<>(Lesson.class));
    }

    @Override
    public List<Lesson> getLessons(Teacher teacher, int year, Month month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDdate = LocalDate.of(year, month, month.maxLength());
        return jdbcTemplate.query(SQL_TEACHER_LESSONS_FOR_MONTH, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, teacher.getId());
                ps.setInt(2, year);
                ps.setDate(3, Date.valueOf(startDate));
                ps.setDate(3, Date.valueOf(endDdate));
            }
        }, new BeanPropertyRowMapper<>(Lesson.class));
    }

}
