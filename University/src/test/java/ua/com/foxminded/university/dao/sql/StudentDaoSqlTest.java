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

import ua.com.foxminded.university.dto.Student;

class StudentDaoSqlTest {
    
    private static StudentDaoSql studentDaoSql;
    
    @BeforeAll
    public static void setUp() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/schema.sql")
                .addScript("classpath:/test-data.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        studentDaoSql = new StudentDaoSql(jdbcTemplate);
    }
    
    @Test
    void readAll_shouldreturnStudentList_whenStudentCreated() {
        studentDaoSql.delete(3);
        studentDaoSql.delete(4);
        
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
        
        List<Student> actual = studentDaoSql.readAll();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnNull_whenStudentDoesNotExists() {
        Student expected = null;
        
       Student actual = studentDaoSql.readById(0);
        
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
        
        Student actual = studentDaoSql.readById(1);
        
        assertEquals(expected, actual);
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenStudentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentDaoSql.create(null);
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
        
        studentDaoSql.create(expected);
        
        Student actual = studentDaoSql.readById(3);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void update_shouldThrowIllegalArgumentException_whenStudentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentDaoSql.update(null);
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
        studentDaoSql.create(expected);
        
        expected.setFirstName("Test");
        expected.setSecondName("Test");        
        
        studentDaoSql.update(expected);
        
        Student actual = studentDaoSql.readById(4);
        
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
        studentDaoSql.create(input);
        
        Student expected = null;
        
        studentDaoSql.delete(5);
        
        Student actual = studentDaoSql.readById(5);
        
        assertEquals(expected, actual);
    }

}
