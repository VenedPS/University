package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.CourseDto;
import ua.com.foxminded.university.dto.GroupDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.entity.CourseEntity;
import ua.com.foxminded.university.entity.GroupEntity;
import ua.com.foxminded.university.entity.StudentEntity;

@Service
public class GroupConverter {
    
    @Autowired
    private StudentConverter studentConverter;
    
    @Autowired
    private CourseConverter courseConverter;
    
    public GroupEntity toEntity(GroupDto groupDto) {
        if (groupDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(groupDto.getId());
        groupEntity.setName(groupDto.getName());
        
        List<StudentEntity> students = new ArrayList<>();
        for (StudentDto studentDto : groupDto.getStudents()) {
            students.add(studentConverter.toEntity(studentDto));
        }
        groupEntity.setStudents(students);
        
        List<CourseEntity> courses = new ArrayList<>();
        for (CourseDto courseDto : groupDto.getCourses()) {
            courses.add(courseConverter.toEntity(courseDto));
        }
        groupEntity.setCourses(courses);
        return groupEntity;
    }

    public List<GroupEntity> toEntityList(List<GroupDto> groupDtoList) {
        if (groupDtoList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<GroupEntity> groupEntityList = new ArrayList<>();
        for (GroupDto groupDto : groupDtoList) {
            groupEntityList.add(toEntity(groupDto));
        }
        return groupEntityList;
    }

    public GroupDto toDto(GroupEntity groupEntity) {
        if (groupEntity == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        GroupDto groupDto = new GroupDto();
        groupDto.setId(groupEntity.getId());
        groupDto.setName(groupEntity.getName());
        
        List<StudentDto> students = new ArrayList<>();
        for (StudentEntity studentEntity : groupEntity.getStudents()) {
            students.add(studentConverter.toDto(studentEntity));
        }
        groupDto.setStudents(students);
        
        List<CourseDto> courses = new ArrayList<>();
        for (CourseEntity courseEntity : groupEntity.getCourses()) {
            courses.add(courseConverter.toDto(courseEntity));
        }
        groupDto.setCourses(courses);
        return groupDto;
    }

    public List<GroupDto> toDtoList(List<GroupEntity> groupEntityList) {
        if (groupEntityList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<GroupDto> courseDtoList = new ArrayList<>();
        for (GroupEntity groupEntity : groupEntityList) {
            courseDtoList.add(toDto(groupEntity));
        }
        return courseDtoList;
    }
    
}
