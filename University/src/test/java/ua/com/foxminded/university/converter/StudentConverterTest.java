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
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ConverterTestConfig.class })
class StudentConverterTest {

    @Autowired
    private StudentConverter studentConverter;
    
    @Test
    void toEntity_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentConverter.toEntity(null);
        });
    }
    
    @Test
    void toEntity_shouldreturnEmptyStudentEntity_whenEmptyStudentDto() {
        StudentEntity expected = new StudentEntity();

        StudentDto input = new StudentDto();

        StudentEntity actual = studentConverter.toEntity(input);

        assertEquals(expected, actual);
    }
    
    @Test
    void toEntity_shouldreturnStudentEntityWithData_whenStudentDtoWithData() {
        StudentEntity expected = new StudentEntity();
        expected.setId(1);
        expected.setGroupId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        
        StudentDto input = new StudentDto();
        input.setId(1);
        input.setGroupId(1);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");
        
        StudentEntity actual = studentConverter.toEntity(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toEntityList_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentConverter.toEntityList(null);
        });
    }
    
    @Test
    void toEntityList_shouldreturnEmptyList_whenEmptyInputList() {
        List<StudentEntity> expected = new ArrayList<>();
        
        List<StudentDto> input = new ArrayList<>();
        
        List<StudentEntity> actual = studentConverter.toEntityList(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toEntityList_shouldreturnStudentEntityList_whenStudentDtoListWithData() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1);
        studentEntity.setGroupId(1);
        studentEntity.setFirstName("first_name");
        studentEntity.setSecondName("second_name");
        studentEntity.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        studentEntity.setAddress("address");
        studentEntity.setPhone("phone");
        studentEntity.setEmail("email");
        
        List<StudentEntity> expected = new ArrayList<>();
        expected.add(studentEntity);
        
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1);
        studentDto.setGroupId(1);
        studentDto.setFirstName("first_name");
        studentDto.setSecondName("second_name");
        studentDto.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        studentDto.setAddress("address");
        studentDto.setPhone("phone");
        studentDto.setEmail("email");
        
        List<StudentDto> input = new ArrayList<>();
        input.add(studentDto);
        
        List<StudentEntity> actual = studentConverter.toEntityList(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDto_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentConverter.toDto(null);
        });
    }
    
    @Test
    void toDto_shouldreturnEmptyStudentDto_whenEmptyStudentEntity() {
        StudentDto expected = new StudentDto();
        
        StudentEntity input = new StudentEntity();
        
        StudentDto actual = studentConverter.toDto(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDto_shouldreturnStudentEntityWithData_whenStudentDtoWithData() {
        StudentDto expected = new StudentDto();
        expected.setId(1);
        expected.setGroupId(1);
        expected.setFirstName("first_name");
        expected.setSecondName("second_name");
        expected.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        expected.setAddress("address");
        expected.setPhone("phone");
        expected.setEmail("email");
        
        StudentEntity input = new StudentEntity();
        input.setId(1);
        input.setGroupId(1);
        input.setFirstName("first_name");
        input.setSecondName("second_name");
        input.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        input.setAddress("address");
        input.setPhone("phone");
        input.setEmail("email");
        
        StudentDto actual = studentConverter.toDto(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDtoList_shouldThrowIllegalArgumentException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentConverter.toDtoList(null);
        });
    }
    
    @Test
    void toDtoList_shouldreturnEmptyList_whenEmptyInputList() {
        List<StudentDto> expected = new ArrayList<>();
        
        List<StudentEntity> input = new ArrayList<>();
        
        List<StudentDto> actual = studentConverter.toDtoList(input);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void toDtoList_shouldreturnStudentDtoList_whenStudentEntityListWithData() {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1);
        studentDto.setGroupId(1);
        studentDto.setFirstName("first_name");
        studentDto.setSecondName("second_name");
        studentDto.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        studentDto.setAddress("address");
        studentDto.setPhone("phone");
        studentDto.setEmail("email");
        
        List<StudentDto> expected = new ArrayList<>();
        expected.add(studentDto);
        
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1);
        studentEntity.setGroupId(1);
        studentEntity.setFirstName("first_name");
        studentEntity.setSecondName("second_name");
        studentEntity.setBirthDate(LocalDate.of(2021, Month.OCTOBER, 6));
        studentEntity.setAddress("address");
        studentEntity.setPhone("phone");
        studentEntity.setEmail("email");
        
        List<StudentEntity> input = new ArrayList<>();
        input.add(studentEntity);
        
        List<StudentDto> actual = studentConverter.toDtoList(input);
        
        assertEquals(expected, actual);
    }

}
