//package ua.com.foxminded.university.dao.sql;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import ua.com.foxminded.university.config.DaoTestConfig;
//import ua.com.foxminded.university.dao.TeacherDao;
//import ua.com.foxminded.university.entity.LessonEntity;
//import ua.com.foxminded.university.entity.TeacherEntity;
//import ua.com.foxminded.university.exception.LessonNotFoundException;
//import ua.com.foxminded.university.exception.TeacherNotFoundException;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { DaoTestConfig.class })
//class TeacherDaoSqlTest {
//
//    @Autowired
//    private TeacherDao teacherDao;
//    
//    @Test
//    void readAll_shouldreturnTeacherList_whenTeacherCreated() {
//        teacherDao.delete(3);
//        teacherDao.delete(4);
//        
//        TeacherEntity teacher1 = new TeacherEntity();
//        teacher1.setId(1);
//        teacher1.setFirstName("first_name");
//        teacher1.setSecondName("second_name");
//        teacher1.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        teacher1.setAddress("address");
//        teacher1.setPhone("phone");
//        teacher1.setEmail("email");
//        
//        TeacherEntity teacher2 = new TeacherEntity();
//        teacher2.setId(2);
//        teacher2.setFirstName("first_name");
//        teacher2.setSecondName("second_name");
//        teacher2.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        teacher2.setAddress("address");
//        teacher2.setPhone("phone");
//        teacher2.setEmail("email");
//        
//        List<TeacherEntity> expected = new ArrayList<>();
//        expected.add(teacher1);
//        expected.add(teacher2);
//        
//        List<TeacherEntity> actual = teacherDao.readAll();
//        
//        assertEquals(expected, actual);
//    }
//    
//    @Test
//    void readById_shouldreturnNull_whenTeacherDoesNotExists() {
//        assertThrows(TeacherNotFoundException.class, () -> {
//            teacherDao.readById(0);
//        });
//    }
//    
//    @Test
//    void readById_shouldreturnTeacher_whenTeacherExists() {
//        TeacherEntity expected = new TeacherEntity();
//        expected.setId(1);
//        expected.setFirstName("first_name");
//        expected.setSecondName("second_name");
//        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        expected.setAddress("address");
//        expected.setPhone("phone");
//        expected.setEmail("email");
//        
//        TeacherEntity actual = teacherDao.readById(1);
//        
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void create_shouldThrowIllegalArgumentException_whenTeacherIsNull() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            teacherDao.create(null);
//        });
//    }
//    
//    @Test
//    void create_readById_shoulReturnTeacherFromDb_whenTeacherCreated() {
//        TeacherEntity expected = new TeacherEntity();
//        expected.setId(3);
//        expected.setFirstName("first_name");
//        expected.setSecondName("second_name");
//        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        expected.setAddress("address");
//        expected.setPhone("phone");
//        expected.setEmail("email");
//        
//        teacherDao.create(expected);
//        
//        TeacherEntity actual = teacherDao.readById(3);
//        
//        assertEquals(expected, actual);
//    }
//    
//    @Test
//    void update_shouldThrowIllegalArgumentException_whenTeacherIsNull() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            teacherDao.update(null);
//        });
//    }
//    
//    
//    @Test
//    void update_readById_shoulReturnTeacherFromDb_whenTeacherCreated() {
//        TeacherEntity expected = new TeacherEntity();
//        expected.setId(4);
//        expected.setFirstName("first_name");
//        expected.setSecondName("second_name");
//        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        expected.setAddress("address");
//        expected.setPhone("phone");
//        expected.setEmail("email");
//        teacherDao.create(expected);
//        
//        expected.setFirstName("Test");
//        expected.setSecondName("Test");
//        
//        teacherDao.update(expected);
//        
//        TeacherEntity actual = teacherDao.readById(4);
//        
//        assertEquals(expected, actual);
//    }
//    
//    @Test
//    void delete_readById_shoulReturnNull_whenTeacherWasDeleted() {
//        TeacherEntity input = new TeacherEntity();
//        input.setId(5);
//        input.setFirstName("first_name");
//        input.setSecondName("second_name");
//        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        input.setAddress("address");
//        input.setPhone("phone");
//        input.setEmail("email");
//        teacherDao.create(input);
//        
//        teacherDao.delete(5);
//        
//        assertThrows(TeacherNotFoundException.class, () -> {
//            teacherDao.readById(5);
//        });
//    }
//    
//  @Test
//  void getTeacherLessons_shouldreturnEmptyList_whenLessonDoesNotExists() {
//      assertThrows(LessonNotFoundException.class, () -> {
//          teacherDao.getTeacherLessons(1, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 5));
//      });
//  }
//  
//  @Test
//  void getTeacherLessons_shouldreturnLessonList_whenOneLessonExists() {
//      LessonEntity lesson1 = new LessonEntity();
//      lesson1.setId(1);
//      lesson1.setTimetableId(1);
//      lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
//      lesson1.setLessonNumber(1);
//      lesson1.setGroupId(1);
//      lesson1.setCourseId(1);
//      lesson1.setClassroomId(1);
//      lesson1.setTeacherId(1);
//      
//      List<LessonEntity> expected = new ArrayList<>();
//      expected.add(lesson1);
//      
//      List<LessonEntity> actual = teacherDao.getTeacherLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 6));
//      
//      assertEquals(expected, actual);
//  }
//  
//  @Test
//  void getTeacherLessons_shouldreturnLessonList_whenFewLessonExists() {
//      LessonEntity lesson1 = new LessonEntity();
//      lesson1.setId(1);
//      lesson1.setTimetableId(1);
//      lesson1.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
//      lesson1.setLessonNumber(1);
//      lesson1.setGroupId(1);
//      lesson1.setCourseId(1);
//      lesson1.setClassroomId(1);
//      lesson1.setTeacherId(1);
//      
//      LessonEntity lesson2 = new LessonEntity();
//      lesson2.setId(2);
//      lesson2.setTimetableId(1);
//      lesson2.setDate(LocalDate.of(2021, Month.OCTOBER, 7));
//      lesson2.setLessonNumber(2);
//      lesson2.setGroupId(1);
//      lesson2.setCourseId(1);
//      lesson2.setClassroomId(1);
//      lesson2.setTeacherId(1);
//      
//      List<LessonEntity> expected = new ArrayList<>();
//      expected.add(lesson1);
//      expected.add(lesson2);
//      
//      List<LessonEntity> actual = teacherDao.getTeacherLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 7));
//      
//      assertEquals(expected, actual);
//  }
//}
