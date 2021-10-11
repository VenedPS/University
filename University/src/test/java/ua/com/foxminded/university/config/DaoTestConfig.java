package ua.com.foxminded.university.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dao.sql.LessonDaoSql;
import ua.com.foxminded.university.dao.sql.StudentDaoSql;
import ua.com.foxminded.university.dao.sql.TeacherDaoSql;

@Configuration
public class DaoTestConfig {
    
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/schema.sql")
                .addScript("classpath:/test-data.sql")
                .build();
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
    
    @Bean
    public LessonDao lessonDao() {
        return new LessonDaoSql(jdbcTemplate());
    }
    
    @Bean
    public StudentDao studentDao() {
        return new StudentDaoSql(jdbcTemplate());
    }
    
    @Bean
    public TeacherDao teacherDao() {
        return new TeacherDaoSql(jdbcTemplate());
    }
}
