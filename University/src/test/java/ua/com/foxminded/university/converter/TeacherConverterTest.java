package ua.com.foxminded.university.converter;

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
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.entity.TeacherEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ConverterTestConfig.class })
class TeacherConverterTest {

    @Autowired
    private TeacherConverter teacherConverter;

    @Test
    void toEntity_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherConverter.toEntity(null);
        });
    }

    @Test
    void toEntity_shouldreturnEmptyTeacherEntity_whenEmptyTeacherDto() {
        TeacherEntity expected = new TeacherEntity();

        TeacherDto input = new TeacherDto();

        TeacherEntity actual = teacherConverter.toEntity(input);

        assertEquals(expected, actual);
    }

    @Test
    void toEntity_shouldreturnTeacherEntityWithData_whenTeacherDtoWithData() {
        TeacherEntity expected = new TeacherEntity();
        expected.setId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");

        TeacherDto input = new TeacherDto();
        input.setId(1);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");

        TeacherEntity actual = teacherConverter.toEntity(input);

        assertEquals(expected, actual);
    }

    @Test
    void toEntityList_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherConverter.toEntityList(null);
        });
    }

    @Test
    void toEntityList_shouldreturnEmptyList_whenEmptyInputList() {
        List<TeacherEntity> expected = new ArrayList<>();

        List<TeacherDto> input = new ArrayList<>();

        List<TeacherEntity> actual = teacherConverter.toEntityList(input);

        assertEquals(expected, actual);
    }

    @Test
    void toEntityList_shouldreturnTeacherEntityList_whenTeacherDtoListWithData() {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(1);
        teacherEntity.setFirstName("first_name");
        teacherEntity.setSecondName("second_name");
        teacherEntity.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        teacherEntity.setAddress("address");
        teacherEntity.setPhone("phone");
        teacherEntity.setEmail("email");

        List<TeacherEntity> expected = new ArrayList<>();
        expected.add(teacherEntity);

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1);
        teacherDto.setFirstName("first_name");
        teacherDto.setSecondName("second_name");
        teacherDto.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        teacherDto.setAddress("address");
        teacherDto.setPhone("phone");
        teacherDto.setEmail("email");

        List<TeacherDto> input = new ArrayList<>();
        input.add(teacherDto);

        List<TeacherEntity> actual = teacherConverter.toEntityList(input);

        assertEquals(expected, actual);
    }

    @Test
    void toDto_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherConverter.toDto(null);
        });
    }

    @Test
    void toDto_shouldreturnEmptyTeacherDto_whenEmptyTeacherEntity() {
        TeacherDto expected = new TeacherDto();

        TeacherEntity input = new TeacherEntity();

        TeacherDto actual = teacherConverter.toDto(input);

        assertEquals(expected, actual);
    }

    @Test
    void toDto_shouldreturnTeacherEntityWithData_whenTeacherDtoWithData() {
        TeacherDto expected = new TeacherDto();
        expected.setId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");

        TeacherEntity input = new TeacherEntity();
        input.setId(1);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");

        TeacherDto actual = teacherConverter.toDto(input);

        assertEquals(expected, actual);
    }

    @Test
    void toDtoList_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            teacherConverter.toDtoList(null);
        });
    }

    @Test
    void toDtoList_shouldreturnEmptyList_whenEmptyInputList() {
        List<TeacherDto> expected = new ArrayList<>();

        List<TeacherEntity> input = new ArrayList<>();

        List<TeacherDto> actual = teacherConverter.toDtoList(input);

        assertEquals(expected, actual);
    }

    @Test
    void toDtoList_shouldreturnTeacherDtoList_whenTeacherEntityListWithData() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1);
        teacherDto.setFirstName("first_name");
        teacherDto.setSecondName("second_name");
        teacherDto.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        teacherDto.setAddress("address");
        teacherDto.setPhone("phone");
        teacherDto.setEmail("email");

        List<TeacherDto> expected = new ArrayList<>();
        expected.add(teacherDto);

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(1);
        teacherEntity.setFirstName("first_name");
        teacherEntity.setSecondName("second_name");
        teacherEntity.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        teacherEntity.setAddress("address");
        teacherEntity.setPhone("phone");
        teacherEntity.setEmail("email");

        List<TeacherEntity> input = new ArrayList<>();
        input.add(teacherEntity);

        List<TeacherDto> actual = teacherConverter.toDtoList(input);

        assertEquals(expected, actual);
    }

}
