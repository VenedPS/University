package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.StudentEntity;
import ua.com.foxminded.university.service.GroupService;

@Service
public class StudentConverter {
    
	@Autowired
	private GroupService groupService;
    
    public StudentEntity toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDto.getId());
        studentEntity.setGroup(groupService.readById(studentDto.getGroupId()));
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setSecondName(studentDto.getSecondName());
        studentEntity.setBirthDate(studentDto.getBirthDate());
        studentEntity.setAddress(studentDto.getAddress());
        studentEntity.setPhone(studentDto.getPhone());
        studentEntity.setEmail(studentDto.getEmail());
        return studentEntity;
    }
    
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
        studentDto.setGroupId(studentEntity.getGroup().getId());
        studentDto.setFirstName(studentEntity.getFirstName());
        studentDto.setSecondName(studentEntity.getSecondName());
        studentDto.setBirthDate(studentEntity.getBirthDate());
        studentDto.setAddress(studentEntity.getAddress());
        studentDto.setPhone(studentEntity.getPhone());
        studentDto.setEmail(studentEntity.getEmail());
        return studentDto;
    }
    
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
