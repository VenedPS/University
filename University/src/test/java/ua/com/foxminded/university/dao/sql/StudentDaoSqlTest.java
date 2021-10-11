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
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dto.Student;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DaoTestConfig.class })
class StudentDaoSqlTest {
    
    @Autowired
    private StudentDao studentDao;
    
    @Test
    void readAll_shouldreturnStudentList_whenStudentCreated() {
        studentDao.delete(3);
        studentDao.delete(4);
        
        Student student1 = new Student();
        student1.setId(1);
        student1.setGroupId(1);
        student1.setFirstName("first_name");
        student1.setSecondName("second_name");
        student1.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        student1.setAddress("address");
        student1.setPhone("phone");
        student1.setEmail("email");
        
        Student student2 = new Student();
        student2.setId(2);
        student2.setGroupId(1);
        student2.setFirstName("first_name");
        student2.setSecondName("second_name");
        student2.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        student2.setAddress("address");
        student2.setPhone("phone");
        student2.setEmail("email");
        
        List<Student> expected = new ArrayList<>();
        expected.add(student1);
        expected.add(student2);
        
        List<Student> actual = studentDao.readAll();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnNull_whenStudentDoesNotExists() {
        Student expected = null;
        
       Student actual = studentDao.readById(0);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnStudent_whenStudentExists() {
        Student expected = new Student();
        expected.setId(1);
        expected.setGroupId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        
        Student actual = studentDao.readById(1);
        
        assertEquals(expected, actual);
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenStudentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentDao.create(null);
        });
    }
    
    @Test
    void create_readById_shoulReturnStudentFromDb_whenStudentCreated() {
        Student expected = new Student();
        expected.setId(3);
        expected.setGroupId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        
        studentDao.create(expected);
        
        Student actual = studentDao.readById(3);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void update_shouldThrowIllegalArgumentException_whenStudentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentDao.update(null);
        });
    }
    
    
    @Test
    void update_readById_shoulReturnStudentFromDb_whenStudentCreated() {
        Student expected = new Student();
        expected.setId(4);
        expected.setGroupId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        studentDao.create(expected);
        
        expected.setFirstName("Test");
        expected.setSecondName("Test");        
        
        studentDao.update(expected);
        
        Student actual = studentDao.readById(4);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void delete_readById_shoulReturnNull_whenStudentWasDeleted() {
        Student input = new Student();
        input.setId(5);
        input.setGroupId(1);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");
        studentDao.create(input);
        
        Student expected = null;
        
        studentDao.delete(5);
        
        Student actual = studentDao.readById(5);
        
        assertEquals(expected, actual);
    }

}
