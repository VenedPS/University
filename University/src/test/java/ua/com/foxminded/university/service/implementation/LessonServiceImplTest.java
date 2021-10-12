package ua.com.foxminded.university.service.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.university.config.ConverterTestConfig;
import ua.com.foxminded.university.config.ServiceImplTestConfig;
import ua.com.foxminded.university.dto.LessonDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceImplTestConfig.class, ConverterTestConfig.class })
class LessonServiceImplTest {

    private final LocalDate TEST_DATE = LocalDate.of(2021, Month.OCTOBER, 6);
    
    @Autowired
    private LessonServiceImpl lessonServiceImpl;

    @Test
    void readAll_shouldReturnLessonList_whenLessonsExists() {
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

        List<LessonDto> actual = lessonServiceImpl.readAll();

        assertEquals(expected, actual);
    }

    @Test
    void readById_shouldThrowIllegalArgumentException_whenLessonDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
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
        expected.setGroupId(1);
        expected.setCourseId(1);
        expected.setClassroomId(1);
        expected.setTeacherId(1);

        LessonDto actual = lessonServiceImpl.readById(0);

        assertEquals(expected, actual);
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

        List<LessonDto> actual = lessonServiceImpl.getTeacherLessons(0,TEST_DATE,TEST_DATE);

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherLessons_shouldReturnLessonList_whenLessonsExists() {
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
        
        List<LessonDto> actual = lessonServiceImpl.getTeacherLessons(0,TEST_DATE,TEST_DATE);
        
        assertEquals(expected, actual);
    }

}
