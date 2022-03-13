package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.GroupDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.GroupEntity;
import ua.com.foxminded.university.entity.StudentEntity;

@Service
public class StudentConverter {
    
    @Autowired
    private GroupConverter groupConverter;
    
    public StudentEntity toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDto.getId());
        studentEntity.setGroup(groupConverter.toEntity(studentDto.getGroup()));
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setSecondName(studentDto.getSecondName());
        studentEntity.setBirthDate(studentDto.getBirthDate());
        studentEntity.setAddress(studentDto.getAddress());
        studentEntity.setPhone(studentDto.getPhone());
        studentEntity.setEmail(studentDto.getEmail());
        return studentEntity;
    }
    
//    public StudentEntity toEntity(StudentDto studentDto, GroupEntity groupEntity) {
//        if (studentDto == null) {
//            throw new IllegalArgumentException("Cannot convert null!");
//        }
//        
//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setId(studentDto.getId());
//        studentEntity.setGroup(groupEntity);
//        studentEntity.setFirstName(studentDto.getFirstName());
//        studentEntity.setSecondName(studentDto.getSecondName());
//        studentEntity.setBirthDate(studentDto.getBirthDate());
//        studentEntity.setAddress(studentDto.getAddress());
//        studentEntity.setPhone(studentDto.getPhone());
//        studentEntity.setEmail(studentDto.getEmail());
//        return studentEntity;
//    }

    public List<StudentEntity> toEntityList(List<StudentDto> studentDtoList) {
        if (studentDtoList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<StudentEntity> studentEntityList = new ArrayList<>();
        for (StudentDto studentDto : studentDtoList) {
            studentEntityList.add(toEntity(studentDto));
        }
        return studentEntityList;
    }

    public StudentDto toDto(StudentEntity studentEntity) {
        if (studentEntity == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentEntity.getId());
        studentDto.setGroup(groupConverter.toDto(studentEntity.getGroup()));
        studentDto.setFirstName(studentEntity.getFirstName());
        studentDto.setSecondName(studentEntity.getSecondName());
        studentDto.setBirthDate(studentEntity.getBirthDate());
        studentDto.setAddress(studentEntity.getAddress());
        studentDto.setPhone(studentEntity.getPhone());
        studentDto.setEmail(studentEntity.getEmail());
        return studentDto;
    }
    
//    public StudentDto toDto(StudentEntity studentEntity, GroupDto groupDto) {
//        if (studentEntity == null) {
//            throw new IllegalArgumentException("Cannot convert null!");
//        }
//        
//        StudentDto studentDto = new StudentDto();
//        studentDto.setId(studentEntity.getId());
//        studentDto.setGroup(groupDto);
//        studentDto.setFirstName(studentEntity.getFirstName());
//        studentDto.setSecondName(studentEntity.getSecondName());
//        studentDto.setBirthDate(studentEntity.getBirthDate());
//        studentDto.setAddress(studentEntity.getAddress());
//        studentDto.setPhone(studentEntity.getPhone());
//        studentDto.setEmail(studentEntity.getEmail());
//        return studentDto;
//    }

    public List<StudentDto> toDtoList(List<StudentEntity> studentEntityList) {
        if (studentEntityList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<StudentDto> studentDtoList = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntityList) {
            studentDtoList.add(toDto(studentEntity));
        }
        return studentDtoList;
    }
    
}
