package ua.com.foxminded.university.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.entity.LessonEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ConverterTestConfig.class })
class LessonConverterTest {

    @Autowired
    private LessonConverter lessonConverter;
    
    @Test
    void toEntity_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            lessonConverter.toEntity(null);
        });
    }
    
    @Test
    void toEntity_shouldreturnEmptyLessonEntity_whenEmptyLessonDto() {
        LessonEntity expected = new LessonEntity();

        LessonDto input = new LessonDto();

        LessonEntity actual = lessonConverter.toEntity(input);

        assertEquals(expected, actual);
    }
    
    @Test
    void toEntity_shouldreturnLessonEntityWithData_whenLessonDtoWithData() {
        LessonEntity expected = new LessonEntity();
        expected.setId(1);
        expected.setTimetableId(1);
        expected.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setLessonNumber(1);
        expected.setGroupId(1);
        expected.setCourseId(1);
        expected.setClassroomId(1);
        expected.setTeacherId(1);
        
        LessonDto input = new LessonDto();
        input.setId(1);
        input.setTimetableId(1);
        input.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setLessonNumber(1);
        input.setGroupId(1);
        input.setCourseId(1);
        input.setClassroomId(1);
        input.setTeacherId(1);
        
        LessonEntity actual = lessonConverter.toEntity(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toEntityList_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            lessonConverter.toEntityList(null);
        });
    }
    
    @Test
    void toEntityList_shouldreturnEmptyList_whenEmptyInputList() {
        List<LessonEntity> expected = new ArrayList<>();
        
        List<LessonDto> input = new ArrayList<>();
        
        List<LessonEntity> actual = lessonConverter.toEntityList(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toEntityList_shouldreturnLessonEntityList_whenLessonDtoListWithData() {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(1);
        lessonEntity.setTimetableId(1);
        lessonEntity.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lessonEntity.setLessonNumber(1);
        lessonEntity.setGroupId(1);
        lessonEntity.setCourseId(1);
        lessonEntity.setClassroomId(1);
        lessonEntity.setTeacherId(1);
        
        List<LessonEntity> expected = new ArrayList<>();
        expected.add(lessonEntity);
        
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(1);
        lessonDto.setTimetableId(1);
        lessonDto.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lessonDto.setLessonNumber(1);
        lessonDto.setGroupId(1);
        lessonDto.setCourseId(1);
        lessonDto.setClassroomId(1);
        lessonDto.setTeacherId(1);
        
        List<LessonDto> input = new ArrayList<>();
        input.add(lessonDto);
        
        List<LessonEntity> actual = lessonConverter.toEntityList(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDto_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            lessonConverter.toDto(null);
        });
    }
    
    @Test
    void toDto_shouldreturnEmptyLessonDto_whenEmptyLessonEntity() {
        LessonDto expected = new LessonDto();
        
        LessonEntity input = new LessonEntity();
        
        LessonDto actual = lessonConverter.toDto(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDto_shouldreturnLessonEntityWithData_whenLessonDtoWithData() {
        LessonDto expected = new LessonDto();
        expected.setId(1);
        expected.setTimetableId(1);
        expected.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setLessonNumber(1);
        expected.setGroupId(1);
        expected.setCourseId(1);
        expected.setClassroomId(1);
        expected.setTeacherId(1);
        
        LessonEntity input = new LessonEntity();
        input.setId(1);
        input.setTimetableId(1);
        input.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setLessonNumber(1);
        input.setGroupId(1);
        input.setCourseId(1);
        input.setClassroomId(1);
        input.setTeacherId(1);
        
        LessonDto actual = lessonConverter.toDto(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDtoList_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            lessonConverter.toDtoList(null);
        });
    }
    
    @Test
    void toDtoList_shouldreturnEmptyList_whenEmptyInputList() {
        List<LessonDto> expected = new ArrayList<>();
        
        List<LessonEntity> input = new ArrayList<>();
        
        List<LessonDto> actual = lessonConverter.toDtoList(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDtoList_shouldreturnLessonDtoList_whenLessonEntityListWithData() {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(1);
        lessonDto.setTimetableId(1);
        lessonDto.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lessonDto.setLessonNumber(1);
        lessonDto.setGroupId(1);
        lessonDto.setCourseId(1);
        lessonDto.setClassroomId(1);
        lessonDto.setTeacherId(1);
        
        List<LessonDto> expected = new ArrayList<>();
        expected.add(lessonDto);
        
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(1);
        lessonEntity.setTimetableId(1);
        lessonEntity.setDate(LocalDate.of(2021, Month.OCTOBER, 6));
        lessonEntity.setLessonNumber(1);
        lessonEntity.setGroupId(1);
        lessonEntity.setCourseId(1);
        lessonEntity.setClassroomId(1);
        lessonEntity.setTeacherId(1);
        
        List<LessonEntity> input = new ArrayList<>();
        input.add(lessonEntity);
        
        List<LessonDto> actual = lessonConverter.toDtoList(input);
        
        assertEquals(expected, actual);
    }


}
