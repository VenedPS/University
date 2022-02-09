package ua.com.foxminded.university.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.service.implementation.LessonServiceImpl;
import ua.com.foxminded.university.service.implementation.StudentServiceImpl;
import ua.com.foxminded.university.service.implementation.TeacherServiceImpl;

@Configuration
public class ServiceImplTestConfig {
    
    private final LocalDate TEST_DATE = LocalDate.of(2021, Month.OCTOBER, 6);
    
    @Bean
    public LessonServiceImpl lessonServiceImpl() {
        LessonEntity output = new LessonEntity();
        output.setId(1);
        output.setTimetableId(1);
        output.setDate(TEST_DATE);
        output.setLessonNumber(1);
        output.setGroupId(1);
        output.setCourseId(1);
        output.setClassroomId(1);
        output.setTeacherId(1);
        
        List<LessonEntity> outputList = new ArrayList<>();
        outputList.add(output);
        
        LessonDao lessonDao = Mockito.mock(LessonDao.class);
        Mockito.when(lessonDao.readAll()).thenReturn(outputList);
        Mockito.when(lessonDao.readById(0)).thenReturn(output);
        
        return new LessonServiceImpl(lessonDao);
    }
    
    
    @Bean
    public StudentServiceImpl studentServiceImpl() {
        StudentEntity output = new StudentEntity();
        output.setId(1);
        output.setGroupId(1);
        output.setFirstName("first_name");
        output.setSecondName("second_name");
        output.setBirthDate(TEST_DATE);
        output.setAddress("address");
        output.setPhone("phone");
        output.setEmail("email");
        
        List<StudentEntity> outputList = new ArrayList<>();
        outputList.add(output);
        
        StudentDao studentDao = Mockito.mock(StudentDao.class);
        Mockito.when(studentDao.readAll()).thenReturn(outputList);
        Mockito.when(studentDao.readById(0)).thenReturn(output);
        Mockito.doNothing().when(studentDao).create(output);
        Mockito.doNothing().when(studentDao).update(output);
        Mockito.doNothing().when(studentDao).delete(0);
        
        return new StudentServiceImpl(studentDao);
    }
    
    @Bean
    public TeacherServiceImpl teacherServiceImpl() {
        TeacherEntity output = new TeacherEntity();
        output.setId(1);
        output.setFirstName("first_name");
        output.setSecondName("second_name");
        output.setBirthDate(TEST_DATE);
        output.setAddress("address");
        output.setPhone("phone");
        output.setEmail("email");
        
        List<TeacherEntity> outputList = new ArrayList<>();
        outputList.add(output);
        
        TeacherDao teacherDao = Mockito.mock(TeacherDao.class);
        Mockito.when(teacherDao.readAll()).thenReturn(outputList);
        Mockito.when(teacherDao.readById(0)).thenReturn(output);
        Mockito.doNothing().when(teacherDao).create(output);
        Mockito.doNothing().when(teacherDao).update(output);
        Mockito.doNothing().when(teacherDao).delete(0);
        
        return new TeacherServiceImpl(teacherDao);
    }
}
