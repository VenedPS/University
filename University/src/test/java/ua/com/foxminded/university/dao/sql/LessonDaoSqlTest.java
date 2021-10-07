package ua.com.foxminded.university.dao.sql;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import ua.com.foxminded.university.dto.Lesson;

class LessonDaoSqlTest {

    private static LessonDaoSql lessonDaoSql;
    
    @BeforeAll
    public static void setUp() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/schema.sql")
                .addScript("classpath:/test-data.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        lessonDaoSql = new LessonDaoSql(jdbcTemplate);
    }

    @Test
    void readAll_shouldreturnLessonList_whenLessonCreated() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        Lesson lesson2 = new Lesson();
        lesson2.setId(2);
        lesson2.setTimetableId(1);
        lesson2.setDate(LocalDate.of(2021, Month.OCTOBER, 7));
        lesson2.setLessonNumber(2);
        lesson2.setGroupId(1);
        lesson2.setCourseId(1);
        lesson2.setClassroomId(1);
        lesson2.setTeacherId(1);
        
        List<Lesson> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson2);
        
        List<Lesson> actual = lessonDaoSql.readAll();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnNull_whenLessonDoesNotExists() {
        Lesson expected = null;
        
        Lesson actual = lessonDaoSql.readById(0);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnLesson_whenLessonExists() {
        Lesson expected = new Lesson();
        expected.setId(1);
        expected.setTimetableId(1);
        expected.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setLessonNumber(1);
        expected.setGroupId(1);
        expected.setCourseId(1);
        expected.setClassroomId(1);
        expected.setTeacherId(1);
        
        Lesson actual = lessonDaoSql.readById(1);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getStudentLessons_shouldreturnEmptyList_whenLessonDoesNotExists() {
        List<Lesson> expected = new ArrayList<>();
        
        List<Lesson> actual = lessonDaoSql.getStudentLessons(1, 2021, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 5));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getStudentLessons_shouldreturnLessonList_whenOneLessonExists() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        List<Lesson> expected = new ArrayList<>();
        expected.add(lesson1);
        
        List<Lesson> actual = lessonDaoSql.getStudentLessons(1, 2021, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 6));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getStudentLessons_shouldreturnLessonList_whenFewLessonExists() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        Lesson lesson2 = new Lesson();
        lesson2.setId(2);
        lesson2.setTimetableId(1);
        lesson2.setDate(LocalDate.of(2021, Month.OCTOBER, 7));
        lesson2.setLessonNumber(2);
        lesson2.setGroupId(1);
        lesson2.setCourseId(1);
        lesson2.setClassroomId(1);
        lesson2.setTeacherId(1);
        
        List<Lesson> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson2);
        
        List<Lesson> actual = lessonDaoSql.getStudentLessons(1, 2021, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 7));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getTeacherLessons_shouldreturnEmptyList_whenLessonDoesNotExists() {
        List<Lesson> expected = new ArrayList<>();
        
        List<Lesson> actual = lessonDaoSql.getTeacherLessons(1, 2021, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 5));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getTeacherLessons_shouldreturnLessonList_whenOneLessonExists() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        List<Lesson> expected = new ArrayList<>();
        expected.add(lesson1);
        
        List<Lesson> actual = lessonDaoSql.getTeacherLessons(1, 2021, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 6));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getTeacherLessons_shouldreturnLessonList_whenFewLessonExists() {
        Lesson lesson1 = new Lesson();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        Lesson lesson2 = new Lesson();
        lesson2.setId(2);
        lesson2.setTimetableId(1);
        lesson2.setDate(LocalDate.of(2021, Month.OCTOBER, 7));
        lesson2.setLessonNumber(2);
        lesson2.setGroupId(1);
        lesson2.setCourseId(1);
        lesson2.setClassroomId(1);
        lesson2.setTeacherId(1);
        
        List<Lesson> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson2);
        
        List<Lesson> actual = lessonDaoSql.getTeacherLessons(1, 2021, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 7));
        
        assertEquals(expected, actual);
    }
}