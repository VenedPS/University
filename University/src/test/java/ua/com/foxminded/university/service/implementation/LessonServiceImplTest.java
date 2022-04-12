package ua.com.foxminded.university.service.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.university.config.ConverterTestConfig;
import ua.com.foxminded.university.config.ServiceImplTestConfig;
import ua.com.foxminded.university.dto.GroupDto;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.TeacherDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceImplTestConfig.class, ConverterTestConfig.class })
class LessonServiceImplTest {

    private final LocalDate TEST_DATE = LocalDate.of(2021, Month.OCTOBER, 6);
    
    @Autowired
    private LessonServiceImpl lessonServiceImpl;
    
    private GroupDto groupDto = new GroupDto();
    private TeacherDto teacherDto = new TeacherDto();

    @Test
    void readAll_shouldReturnLessonList_whenLessonsExists() {
        LessonDto input = new LessonDto();
        input.setId(1);
        input.setTimetableId(1);
        input.setDate(TEST_DATE);
        input.setLessonNumber(1);
        input.setGroup(groupDto);
        input.setCourseId(1);
        input.setClassroomId(1);
        input.setTeacher(teacherDto);

        List<LessonDto> expected = new ArrayList<>();
        expected.add(input);

        List<LessonDto> actual = lessonServiceImpl.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void readById_shouldThrowIllegalArgumentException_whenLessonDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> {
            lessonServiceImpl.readById(1);
        });
    }

    @Test
    void readById_shouldReturnLesson_whenLessonExists() {
        LessonDto expected = new LessonDto();
        expected.setId(1);
        expected.setTimetableId(1);
        expected.setDate(TEST_DATE);
        expected.setLessonNumber(1);
        expected.setGroup(groupDto);
        expected.setCourseId(1);
        expected.setClassroomId(1);
        expected.setTeacher(teacherDto);

        LessonDto actual = lessonServiceImpl.readById(0);

        assertEquals(expected, actual);
    }

}
