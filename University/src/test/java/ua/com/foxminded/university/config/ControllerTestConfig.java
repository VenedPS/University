//package ua.com.foxminded.university.config;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.mockito.Mockito;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import ua.com.foxminded.university.controller.StudentController;
//import ua.com.foxminded.university.controller.TeacherController;
//import ua.com.foxminded.university.dto.LessonDto;
//import ua.com.foxminded.university.dto.StudentDto;
//import ua.com.foxminded.university.dto.TeacherDto;
//import ua.com.foxminded.university.service.StudentService;
//import ua.com.foxminded.university.service.GroupService;
//import ua.com.foxminded.university.service.TeacherService;
//
//@Configuration
//public class ControllerTestConfig {
//
//    @Bean
//    public StudentController studentController() {
//        StudentService studentService = Mockito.mock(StudentService.class);
//        GroupService groupService = Mockito.mock(GroupService.class);
//        List<StudentDto> students = new ArrayList<>();
//        Mockito.when(studentService.readAll()).thenReturn(students);
//        StudentDto studentDto = new StudentDto("firstName", "secondName");
//        Mockito.when(studentService.readById(0)).thenReturn(studentDto);
//        Mockito.doNothing().when(studentService).create(studentDto);
//        Mockito.doNothing().when(studentService).update(studentDto);
//        Mockito.doNothing().when(studentService).delete(0);
//        
//        LocalDate testDate = LocalDate.of(1, 1, 1);
//        List<LessonDto> lessons = new ArrayList<LessonDto>();
//        Mockito.when(studentService.getStudentLessons(studentDto, testDate, testDate)).thenReturn(lessons);
//
//        return new StudentController(studentService, groupService);
//    }
//
//    @Bean
//    public TeacherController teacherController() {
//        TeacherService teacherService = Mockito.mock(TeacherService.class);
//        List<TeacherDto> teachers = new ArrayList<>();
//        Mockito.when(teacherService.readAll()).thenReturn(teachers);
//        TeacherDto teacherDto = new TeacherDto(0, "firstName", "secondName");
//        Mockito.when(teacherService.readById(0)).thenReturn(teacherDto);
//        Mockito.doNothing().when(teacherService).create(teacherDto);
//        Mockito.doNothing().when(teacherService).update(teacherDto);
//        Mockito.doNothing().when(teacherService).delete(0);
//        
//        LocalDate testDate = LocalDate.of(1, 1, 1);
//        List<LessonDto> lessons = new ArrayList<LessonDto>();
//        Mockito.when(teacherService.getTeacherLessons(0, testDate, testDate)).thenReturn(lessons);
//        
//        return new TeacherController(teacherService);
//
//    }
//}
