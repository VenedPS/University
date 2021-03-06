package ua.com.foxminded.university.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.entity.GroupEntity;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.entity.TeacherEntity;
import ua.com.foxminded.university.service.implementation.LessonServiceImpl;
import ua.com.foxminded.university.service.implementation.StudentServiceImpl;
import ua.com.foxminded.university.service.implementation.TeacherServiceImpl;

@Configuration
public class ServiceImplTestConfig {
    
    private final LocalDate TEST_DATE = LocalDate.of(2021, Month.OCTOBER, 6);
    
    private GroupEntity groupEntity = new GroupEntity(0, null, null, null, null);
    private TeacherEntity teacherEntity = new TeacherEntity();
    
    @Bean
    public LessonServiceImpl lessonServiceImpl() {
        LessonEntity output = new LessonEntity();
        output.setId(1);
        output.setTimetableId(1);
        output.setDate(TEST_DATE);
        output.setLessonNumber(1);
        output.setGroup(groupEntity);
        output.setCourseId(1);
        output.setClassroomId(1);
        output.setTeacher(teacherEntity);
        
        List<LessonEntity> outputList = new ArrayList<>();
        outputList.add(output);
        
        Optional<LessonEntity> optionalOutput = Optional.ofNullable(output);
        
        LessonDao lessonDao = Mockito.mock(LessonDao.class);
        Mockito.when(lessonDao.findAll()).thenReturn(outputList);
        Mockito.when(lessonDao.findById(0)).thenReturn(optionalOutput);
        
        return new LessonServiceImpl(lessonDao);
    }
    
    
    @Bean
    public StudentServiceImpl studentServiceImpl() {
        StudentEntity output = new StudentEntity();
        output.setId(1);
        output.setGroup(groupEntity);
        output.setFirstName("first_name");
        output.setSecondName("second_name");
        output.setBirthDate(TEST_DATE);
        output.setAddress("address");
        output.setPhone("phone");
        output.setEmail("email");
        
        List<StudentEntity> outputList = new ArrayList<>();
        outputList.add(output);
        
        LessonEntity outputLesson = new LessonEntity();
        outputLesson.setId(1);
        outputLesson.setTimetableId(1);
        outputLesson.setDate(TEST_DATE);
        outputLesson.setLessonNumber(1);
        outputLesson.setGroup(groupEntity);
        outputLesson.setCourseId(1);
        outputLesson.setClassroomId(1);
        outputLesson.setTeacher(teacherEntity);
        
        List<LessonEntity> outputLessonList = new ArrayList<>();
        outputLessonList.add(outputLesson);
        
        Optional<StudentEntity> optionalOutput = Optional.ofNullable(output);
        
        StudentDao studentDao = Mockito.mock(StudentDao.class);
        Mockito.when(studentDao.findAll()).thenReturn(outputList);
        Mockito.when(studentDao.findById(0)).thenReturn(optionalOutput);
        Mockito.when(studentDao.save(output)).thenReturn(output);
        Mockito.doNothing().when(studentDao).deleteById(0);
        Mockito.when(studentDao.getStudentLessons(0,TEST_DATE,TEST_DATE)).thenReturn(outputLessonList);
        
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
        
        LessonEntity outputLesson = new LessonEntity();
        outputLesson.setId(1);
        outputLesson.setTimetableId(1);
        outputLesson.setDate(TEST_DATE);
        outputLesson.setLessonNumber(1);
        outputLesson.setGroup(groupEntity);
        outputLesson.setCourseId(1);
        outputLesson.setClassroomId(1);
        outputLesson.setTeacher(teacherEntity);
        
        List<LessonEntity> outputLessonList = new ArrayList<>();
        outputLessonList.add(outputLesson);
        
        Optional<TeacherEntity> optionalOutput = Optional.ofNullable(output);
        
        TeacherDao teacherDao = Mockito.mock(TeacherDao.class);
        Mockito.when(teacherDao.findAll()).thenReturn(outputList);
        Mockito.when(teacherDao.findById(0)).thenReturn(optionalOutput);
        Mockito.when(teacherDao.save(output)).thenReturn(output);
        Mockito.doNothing().when(teacherDao).deleteById(0);
        Mockito.when(teacherDao.getTeacherLessons(0,TEST_DATE,TEST_DATE)).thenReturn(outputLessonList);
        
        return new TeacherServiceImpl(teacherDao);
    }
}
