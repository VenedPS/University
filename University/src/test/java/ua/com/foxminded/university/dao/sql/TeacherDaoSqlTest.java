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
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dto.Teacher;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DaoTestConfig.class })
class TeacherDaoSqlTest {

    @Autowired
    private TeacherDao teacherDao;
    
    @Test
    void readAll_shouldreturnTeacherList_whenTeacherCreated() {
        teacherDao.delete(3);
        teacherDao.delete(4);
        
        Teacher teacher1 = new Teacher();
        teacher1.setId(1);
        teacher1.setFirstName("first_name");
        teacher1.setSecondName("second_name");
        teacher1.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        teacher1.setAddress("address");
        teacher1.setPhone("phone");
        teacher1.setEmail("email");
        
        Teacher teacher2 = new Teacher();
        teacher2.setId(2);
        teacher2.setFirstName("first_name");
        teacher2.setSecondName("second_name");
        teacher2.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        teacher2.setAddress("address");
        teacher2.setPhone("phone");
        teacher2.setEmail("email");
        
        List<Teacher> expected = new ArrayList<>();
        expected.add(teacher1);
        expected.add(teacher2);
        
        List<Teacher> actual = teacherDao.readAll();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnNull_whenTeacherDoesNotExists() {
        Teacher expected = null;
        
        Teacher actual = teacherDao.readById(0);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnTeacher_whenTeacherExists() {
        Teacher expected = new Teacher();
        expected.setId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        
        Teacher actual = teacherDao.readById(1);
        
        assertEquals(expected, actual);
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenTeacherIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherDao.create(null);
        });
    }
    
    @Test
    void create_readById_shoulReturnTeacherFromDb_whenTeacherCreated() {
        Teacher expected = new Teacher();
        expected.setId(3);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        
        teacherDao.create(expected);
        
        Teacher actual = teacherDao.readById(3);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void update_shouldThrowIllegalArgumentException_whenTeacherIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherDao.update(null);
        });
    }
    
    
    @Test
    void update_readById_shoulReturnTeacherFromDb_whenTeacherCreated() {
        Teacher expected = new Teacher();
        expected.setId(4);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        teacherDao.create(expected);
        
        expected.setFirstName("Test");
        expected.setSecondName("Test");
        
        teacherDao.update(expected);
        
        Teacher actual = teacherDao.readById(4);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void delete_readById_shoulReturnNull_whenTeacherWasDeleted() {
        Teacher input = new Teacher();
        input.setId(5);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");
        teacherDao.create(input);
        
        Teacher expected = null;
        
        teacherDao.delete(5);
        
        Teacher actual = teacherDao.readById(5);
        
        assertEquals(expected, actual);
    }
}
