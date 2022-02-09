package ua.com.foxminded.university.service.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.university.config.ConverterTestConfig;
import ua.com.foxminded.university.config.ServiceImplTestConfig;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceImplTestConfig.class, ConverterTestConfig.class })
class StudentServiceImplTest {
    
    private final LocalDate TEST_DATE = LocalDate.of(2021, Month.OCTOBER, 6);
    
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Test
    void readAll_shouldReturnStudentList_whenStudentsExists() {
        StudentDto input = new StudentDto();
        input.setId(1);
        input.setGroupId(1);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(TEST_DATE);
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");

        List<StudentDto> expected = new ArrayList<>();
        expected.add(input);

        List<StudentDto> actual = studentServiceImpl.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void readById_shouldThrowIllegalArgumentException_whenStudentDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.readById(1);
        });
    }

    @Test
    void readById_shouldReturnStudent_whenStudentExists() {
        StudentDto expected = new StudentDto();
        expected.setId(1);
        expected.setGroupId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(TEST_DATE);
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");

        StudentDto actual = studentServiceImpl.readById(0);

        assertEquals(expected, actual);
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.create(null);
        });
    }
    
    @Test
    void create_shouldCalledVerified_whenMethodMocked() {
        StudentDto inputDto = new StudentDto();
        inputDto.setId(1);
        inputDto.setGroupId(1);
        inputDto.setFirstName("first_name");
        inputDto.setSecondName("second_name");
        inputDto.setBirthDate(TEST_DATE);
        inputDto.setAddress("address");
        inputDto.setPhone("phone");
        inputDto.setEmail("email");

        StudentEntity inputEntity = new StudentEntity();
        inputEntity.setId(1);
        inputEntity.setGroupId(1);
        inputEntity.setFirstName("first_name");
        inputEntity.setSecondName("second_name");
        inputEntity.setBirthDate(TEST_DATE);
        inputEntity.setAddress("address");
        inputEntity.setPhone("phone");
        inputEntity.setEmail("email");

        studentServiceImpl.create(inputDto);
        Mockito.verify(studentServiceImpl.getStudentDao(), Mockito.times(1)).create(inputEntity);
    }
    
    @Test
    void update_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.update(null);
        });
    }
    
    @Test
    void update_shouldCalledVerified_whenMethodMocked() {
        StudentDto inputDto = new StudentDto();
        inputDto.setId(1);
        inputDto.setGroupId(1);
        inputDto.setFirstName("first_name");
        inputDto.setSecondName("second_name");
        inputDto.setBirthDate(TEST_DATE);
        inputDto.setAddress("address");
        inputDto.setPhone("phone");
        inputDto.setEmail("email");
        
        StudentEntity inputEntity = new StudentEntity();
        inputEntity.setId(1);
        inputEntity.setGroupId(1);
        inputEntity.setFirstName("first_name");
        inputEntity.setSecondName("second_name");
        inputEntity.setBirthDate(TEST_DATE);
        inputEntity.setAddress("address");
        inputEntity.setPhone("phone");
        inputEntity.setEmail("email");
        
        studentServiceImpl.update(inputDto);
        Mockito.verify(studentServiceImpl.getStudentDao(), Mockito.times(1)).update(inputEntity);
    }
    
    @Test
    void delete_shouldCalledVerified_whenMethodMocked() {
        studentServiceImpl.delete(0);
        Mockito.verify(studentServiceImpl.getStudentDao(), Mockito.times(1)).delete(0);
    }
    
  @Test
  void getStudentLessons_shouldReturnLessonList_whenLessonsExists() {
      LessonDto input = new LessonDto();
      input.setId(1);
      input.setTimetableId(1);
      input.setDate(TEST_DATE);
      input.setLessonNumber(1);
      input.setGroupId(1);
      input.setCourseId(1);
      input.setClassroomId(1);
      input.setTeacherId(1);

      List<LessonDto> expected = new ArrayList<>();
      expected.add(input);

      List<LessonDto> actual = studentServiceImpl.getStudentLessons(0,TEST_DATE,TEST_DATE);

      assertEquals(expected, actual);
  }
  
}
