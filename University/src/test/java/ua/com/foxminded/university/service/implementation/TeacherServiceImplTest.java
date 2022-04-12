package ua.com.foxminded.university.service.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.university.config.ConverterTestConfig;
import ua.com.foxminded.university.config.ServiceImplTestConfig;
import ua.com.foxminded.university.dto.GroupDto;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.entity.TeacherEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceImplTestConfig.class, ConverterTestConfig.class })
class TeacherServiceImplTest {

    private final LocalDate TEST_DATE = LocalDate.of(2021, Month.OCTOBER, 6);
    
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;
    
    private GroupDto groupDto = new GroupDto();
    private TeacherDto teacherDto = new TeacherDto();

    @Test
    void readAll_shouldReturnTeacherList_whenTeachersExists() {
        TeacherDto input = new TeacherDto();
        input.setId(1);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(TEST_DATE);
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");

        List<TeacherDto> expected = new ArrayList<>();
        expected.add(input);

        List<TeacherDto> actual = teacherServiceImpl.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void readById_shouldThrowIllegalArgumentException_whenTeacherDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> {
            teacherServiceImpl.readById(1);
        });
    }

    @Test
    void readById_shouldReturnTeacher_whenTeacherExists() {
        TeacherDto expected = new TeacherDto();
        expected.setId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(TEST_DATE);
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");

        TeacherDto actual = teacherServiceImpl.readById(0);

        assertEquals(expected, actual);
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherServiceImpl.create(null);
        });
    }
    
    @Test
    void create_shouldCalledVerified_whenMethodMocked() {
        TeacherDto inputDto = new TeacherDto();
        inputDto.setId(1);
        inputDto.setFirstName("first_name");
        inputDto.setSecondName("second_name");
        inputDto.setBirthDate(TEST_DATE);
        inputDto.setAddress("address");
        inputDto.setPhone("phone");
        inputDto.setEmail("email");

        TeacherEntity inputEntity = new TeacherEntity();
        inputEntity.setId(1);
        inputEntity.setFirstName("first_name");
        inputEntity.setSecondName("second_name");
        inputEntity.setBirthDate(TEST_DATE);
        inputEntity.setAddress("address");
        inputEntity.setPhone("phone");
        inputEntity.setEmail("email");

        teacherServiceImpl.create(inputDto);
        Mockito.verify(teacherServiceImpl.getTeacherDao(), Mockito.times(2)).save(inputEntity);
    }
    
    @Test
    void update_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherServiceImpl.update(null);
        });
    }
    
    @Test
    void update_shouldCalledVerified_whenMethodMocked() {
        TeacherDto inputDto = new TeacherDto();
        inputDto.setId(1);
        inputDto.setFirstName("first_name");
        inputDto.setSecondName("second_name");
        inputDto.setBirthDate(TEST_DATE);
        inputDto.setAddress("address");
        inputDto.setPhone("phone");
        inputDto.setEmail("email");
        
        TeacherEntity inputEntity = new TeacherEntity();
        inputEntity.setId(1);
        inputEntity.setFirstName("first_name");
        inputEntity.setSecondName("second_name");
        inputEntity.setBirthDate(TEST_DATE);
        inputEntity.setAddress("address");
        inputEntity.setPhone("phone");
        inputEntity.setEmail("email");
        
        teacherServiceImpl.update(inputDto);
        Mockito.verify(teacherServiceImpl.getTeacherDao(), Mockito.times(1)).save(inputEntity);
    }
    
    @Test
    void delete_shouldCalledVerified_whenMethodMocked() {
        teacherServiceImpl.delete(0);
        Mockito.verify(teacherServiceImpl.getTeacherDao(), Mockito.times(1)).deleteById(0);
    }

  @Test
  void getTeacherLessons_shouldReturnLessonList_whenLessonsExists() {
      LessonDto input = new LessonDto();
      input.setId(1);
      input.setTimetableId(1);
      input.setDate(TEST_DATE);
      input.setLessonNumber(1);
      input.setGroup(groupDto);
      input.setCourseId(1);
      input.setClassroomId(1);
      input.setTeacher(teacherDto);
      
      TeacherDto teacherDto = new TeacherDto();
      teacherDto.setId(1);
      teacherDto.setFirstName("first_name");
      teacherDto.setSecondName("second_name");
      teacherDto.setBirthDate(TEST_DATE);
      teacherDto.setAddress("address");
      teacherDto.setPhone("phone");
      teacherDto.setEmail("email");
      
      List<LessonDto> expected = new ArrayList<>();
      expected.add(input);
      
      List<LessonDto> actual = teacherServiceImpl.getTeacherLessons(0,TEST_DATE,TEST_DATE);
      
      assertEquals(expected, actual);
  }

}
