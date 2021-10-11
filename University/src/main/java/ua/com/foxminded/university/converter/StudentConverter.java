package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;

@Service
public class StudentConverter {
    public StudentEntity toEntity(StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDto.getId());
        studentEntity.setGroupId(studentDto.getGroupId());
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setSecondName(studentDto.getSecondName());
        studentEntity.setBirthDate(studentDto.getBirthDate());
        studentEntity.setAddress(studentDto.getAddress());
        studentEntity.setPhone(studentDto.getPhone());
        studentEntity.setEmail(studentDto.getEmail());
        return studentEntity;
    }
    
    public List<StudentEntity> toEntityList (List<StudentDto> studentDtoList) {
        List<StudentEntity> studentEntityList = new ArrayList<>();
        for (StudentDto studentDto : studentDtoList) {
            studentEntityList.add(toEntity(studentDto));        
        }
        return studentEntityList;
    }

    public StudentDto toDto(StudentEntity studentEntity) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentEntity.getId());
        studentDto.setGroupId(studentEntity.getGroupId());
        studentDto.setFirstName(studentEntity.getFirstName());
        studentDto.setSecondName(studentEntity.getSecondName());
        studentDto.setBirthDate(studentEntity.getBirthDate());
        studentDto.setAddress(studentEntity.getAddress());
        studentDto.setPhone(studentEntity.getPhone());
        studentDto.setEmail(studentEntity.getEmail());
        return studentDto;
    }
    
    public List<StudentDto> toDto (List<StudentEntity> studentEntityList) {
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntityList) {
            studentDtoList.add(toDto(studentEntity));        
        }
        return studentDtoList;
    }
}
