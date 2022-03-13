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
//import ua.com.foxminded.university.dao.StudentDao;
//import ua.com.foxminded.university.entity.LessonEntity;
//import ua.com.foxminded.university.entity.StudentEntity;
//import ua.com.foxminded.university.exception.LessonNotFoundException;
//import ua.com.foxminded.university.exception.StudentNotFoundException;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { DaoTestConfig.class })
//class StudentDaoSqlTest {
//
//    @Autowired
//    private StudentDao studentDao;
//
//    @Test
//    void readAll_shouldreturnStudentList_whenStudentCreated() {
//        studentDao.delete(3);
//        studentDao.delete(4);
//
//        StudentEntity student1 = new StudentEntity();
//        student1.setId(1);
//        student1.setGroupId(1);
//        student1.setFirstName("first_name");
//        student1.setSecondName("second_name");
//        student1.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        student1.setAddress("address");
//        student1.setPhone("phone");
//        student1.setEmail("email");
//
//        StudentEntity student2 = new StudentEntity();
//        student2.setId(2);
//        student2.setGroupId(1);
//        student2.setFirstName("first_name");
//        student2.setSecondName("second_name");
//        student2.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        student2.setAddress("address");
//        student2.setPhone("phone");
//        student2.setEmail("email");
//
//        List<StudentEntity> expected = new ArrayList<>();
//        expected.add(student1);
//        expected.add(student2);
//
//        List<StudentEntity> actual = studentDao.readAll();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void readById_shouldreturnNull_whenStudentDoesNotExists() {
//        assertThrows(StudentNotFoundException.class, () -> {
//            studentDao.readById(0);
//        });
//    }
//
//    @Test
//    void readById_shouldreturnStudent_whenStudentExists() {
//        StudentEntity expected = new StudentEntity();
//        expected.setId(1);
//        expected.setGroupId(1);
//        expected.setFirstName("first_name");
//        expected.setSecondName("second_name");
//        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        expected.setAddress("address");
//        expected.setPhone("phone");
//        expected.setEmail("email");
//
//        StudentEntity actual = studentDao.readById(1);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void create_shouldThrowIllegalArgumentException_whenStudentIsNull() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            studentDao.create(null);
//        });
//    }
//
//    @Test
//    void create_readById_shoulReturnStudentFromDb_whenStudentCreated() {
//        StudentEntity expected = new StudentEntity();
//        expected.setId(3);
//        expected.setGroupId(1);
//        expected.setFirstName("first_name");
//        expected.setSecondName("second_name");
//        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        expected.setAddress("address");
//        expected.setPhone("phone");
//        expected.setEmail("email");
//
//        studentDao.create(expected);
//
//        StudentEntity actual = studentDao.readById(3);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void update_shouldThrowIllegalArgumentException_whenStudentIsNull() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            studentDao.update(null);
//        });
//    }
//
//    @Test
//    void update_readById_shoulReturnStudentFromDb_whenStudentCreated() {
//        StudentEntity expected = new StudentEntity();
//        expected.setId(4);
//        expected.setGroupId(1);
//        expected.setFirstName("first_name");
//        expected.setSecondName("second_name");
//        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        expected.setAddress("address");
//        expected.setPhone("phone");
//        expected.setEmail("email");
//        studentDao.create(expected);
//
//        expected.setFirstName("Test");
//        expected.setSecondName("Test");
//
//        studentDao.update(expected);
//
//        StudentEntity actual = studentDao.readById(4);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void delete_readById_shoulReturnNull_whenStudentWasDeleted() {
//        StudentEntity input = new StudentEntity();
//        input.setId(5);
//        input.setGroupId(1);
//        input.setFirstName("first_name");
//        input.setSecondName("second_name");
//        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
//        input.setAddress("address");
//        input.setPhone("phone");
//        input.setEmail("email");
//        studentDao.create(input);
//
//        studentDao.delete(5);
//
//        assertThrows(StudentNotFoundException.class, () -> {
//            studentDao.readById(5);
//        });
//    }
//    
//  @Test
//  void getStudentLessons_shouldreturnEmptyList_whenLessonDoesNotExists() {
//      assertThrows(LessonNotFoundException.class, () -> {
//          studentDao.getStudentLessons(1, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 5));
//      });
//  }
//  
//  @Test
//  void getStudentLessons_shouldreturnLessonList_whenOneLessonExists() {
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
//      List<LessonEntity> actual = studentDao.getStudentLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 6));
//      
//      assertEquals(expected, actual);
//  }
//  
//  @Test
//  void getStudentLessons_shouldreturnLessonList_whenFewLessonExists() {
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
//      List<LessonEntity> actual = studentDao.getStudentLessons(1, LocalDate.of(2021, Month.OCTOBER, 6), LocalDate.of(2021, Month.OCTOBER, 7));
//      
//      assertEquals(expected, actual);
//  }
//
//}
