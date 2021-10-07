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

import ua.com.foxminded.university.dto.Teacher;

class TeacherDaoSqlTest {

private static TeacherDaoSql teacherDaoSql;
    
    @BeforeAll
    public static void setUp() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/schema.sql")
                .addScript("classpath:/test-data.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        teacherDaoSql = new TeacherDaoSql(jdbcTemplate);
    }

    @Test
    void readAll_shouldreturnTeacherList_whenTeacherCreated() {
        teacherDaoSql.delete(3);
        teacherDaoSql.delete(4);
        
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
        
        List<Teacher> actual = teacherDaoSql.readAll();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void readById_shouldreturnNull_whenTeacherDoesNotExists() {
        Teacher expected = null;
        
        Teacher actual = teacherDaoSql.readById(0);
        
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
        
        Teacher actual = teacherDaoSql.readById(1);
        
        assertEquals(expected, actual);
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenTeacherIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherDaoSql.create(null);
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
        
        teacherDaoSql.create(expected);
        
        Teacher actual = teacherDaoSql.readById(3);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void update_shouldThrowIllegalArgumentException_whenTeacherIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherDaoSql.update(null);
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
        teacherDaoSql.create(expected);
        
        expected.setFirstName("Test");
        expected.setSecondName("Test");
        
        teacherDaoSql.update(expected);
        
        Teacher actual = teacherDaoSql.readById(4);
        
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
        teacherDaoSql.create(input);
        
        Teacher expected = null;
        
        teacherDaoSql.delete(5);
        
        Teacher actual = teacherDaoSql.readById(5);
        
        assertEquals(expected, actual);
    }
}
