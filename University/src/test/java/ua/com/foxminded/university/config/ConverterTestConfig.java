package ua.com.foxminded.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.com.foxminded.university.converter.CourseConverter;
import ua.com.foxminded.university.converter.GroupConverter;
import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.converter.StudentConverter;
import ua.com.foxminded.university.converter.TeacherConverter;

@Configuration
public class ConverterTestConfig {
    
    @Bean
    public LessonConverter lessonConverter() {
        return new LessonConverter();
    }
    
    @Bean
    public StudentConverter studentConverter() {
        return new StudentConverter();
    }
    
    @Bean
    public TeacherConverter teacherConverter() {
        return new TeacherConverter();
    }
    
    @Bean
    public CourseConverter courseConverter() {
        return new CourseConverter();
    }
    
    @Bean
    public GroupConverter groupConverter() {
        return new GroupConverter();
    }
}
