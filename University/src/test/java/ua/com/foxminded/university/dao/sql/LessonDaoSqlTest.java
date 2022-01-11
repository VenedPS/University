package ua.com.foxminded.university.dao.sql;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.university.config.DaoTestConfig;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.exception.LessonNotFoundException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DaoTestConfig.class })
class LessonDaoSqlTest {

    @Autowired
    private LessonDao lessonDao;
    
    @Test
    void readAll_shouldreturnLessonList_whenLessonCreated() {
        LessonEntity lesson1 = new LessonEntity();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        LessonEntity lesson2 = new LessonEntity();
        lesson2.setId(2);
        lesson2.setTimetableId(1);
        lesson2.setDate(LocalDate.of(2021, Month.OCTOBER, 7));
        lesson2.setLessonNumber(2);
        lesson2.setGroupId(1);
        lesson2.setCourseId(1);
        lesson2.setClassroomId(1);
        lesson2.setTeacherId(1);
        
        List<LessonEntity> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson2);
        
        List<LessonEntity> actual = lessonDao.readAll();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnNull_whenLessonDoesNotExists() {
        assertThrows(LessonNotFoundException.class, () -> {
            lessonDao.readById(0);
        });
    }
    
    @Test
    void readById_shouldreturnLesson_whenLessonExists() {
        LessonEntity expected = new LessonEntity();
        expected.setId(1);
        expected.setTimetableId(1);
        expected.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setLessonNumber(1);
        expected.setGroupId(1);
        expected.setCourseId(1);
        expected.setClassroomId(1);
        expected.setTeacherId(1);
        
        LessonEntity actual = lessonDao.readById(1);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getStudentLessons_shouldreturnEmptyList_whenLessonDoesNotExists() {
        List<LessonEntity> expected = new ArrayList<>();
        
        List<LessonEntity> actual = lessonDao.getStudentLessons(1, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 5));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getStudentLessons_shouldreturnLessonList_whenOneLessonExists() {
        LessonEntity lesson1 = new LessonEntity();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        List<LessonEntity> expected = new ArrayList<>();
        expected.add(lesson1);
        
        List<LessonEntity> actual = lessonDao.getStudentLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 6));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getStudentLessons_shouldreturnLessonList_whenFewLessonExists() {
        LessonEntity lesson1 = new LessonEntity();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        LessonEntity lesson2 = new LessonEntity();
        lesson2.setId(2);
        lesson2.setTimetableId(1);
        lesson2.setDate(LocalDate.of(2021, Month.OCTOBER, 7));
        lesson2.setLessonNumber(2);
        lesson2.setGroupId(1);
        lesson2.setCourseId(1);
        lesson2.setClassroomId(1);
        lesson2.setTeacherId(1);
        
        List<LessonEntity> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson2);
        
        List<LessonEntity> actual = lessonDao.getStudentLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 7));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getTeacherLessons_shouldreturnEmptyList_whenLessonDoesNotExists() {
        List<LessonEntity> expected = new ArrayList<>();
        
        List<LessonEntity> actual = lessonDao.getTeacherLessons(1, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 5));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getTeacherLessons_shouldreturnLessonList_whenOneLessonExists() {
        LessonEntity lesson1 = new LessonEntity();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        List<LessonEntity> expected = new ArrayList<>();
        expected.add(lesson1);
        
        List<LessonEntity> actual = lessonDao.getTeacherLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 6));
        
        assertEquals(expected, actual);
    }
    
    @Test
    void getTeacherLessons_shouldreturnLessonList_whenFewLessonExists() {
        LessonEntity lesson1 = new LessonEntity();
        lesson1.setId(1);
        lesson1.setTimetableId(1);
        lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lesson1.setLessonNumber(1);
        lesson1.setGroupId(1);
        lesson1.setCourseId(1);
        lesson1.setClassroomId(1);
        lesson1.setTeacherId(1);
        
        LessonEntity lesson2 = new LessonEntity();
        lesson2.setId(2);
        lesson2.setTimetableId(1);
        lesson2.setDate(LocalDate.of(2021, Month.OCTOBER, 7));
        lesson2.setLessonNumber(2);
        lesson2.setGroupId(1);
        lesson2.setCourseId(1);
        lesson2.setClassroomId(1);
        lesson2.setTeacherId(1);
        
        List<LessonEntity> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson2);
        
        List<LessonEntity> actual = lessonDao.getTeacherLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 7));
        
        assertEquals(expected, actual);
    }
}
