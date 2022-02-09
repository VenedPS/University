package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.CourseDto;
import ua.com.foxminded.university.entity.CourseEntity;

@Service
public class CourseConverter {
    
    @Autowired
    private TeacherConverter teacherConverter;
    
    public  CourseEntity toEntity(CourseDto courseDto) {
        if (courseDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(courseDto.getId());
        courseEntity.setName(courseDto.getName());
        courseEntity.setTeacher(teacherConverter.toEntity(courseDto.getTeacher()));
        return courseEntity;
    }

    public List<CourseEntity> toEntityList(List<CourseDto> courseDtoList) {
        if (courseDtoList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<CourseEntity> courseEntityList = new ArrayList<>();
        for (CourseDto courseDto : courseDtoList) {
            courseEntityList.add(toEntity(courseDto));
        }
        return courseEntityList;
    }

    public CourseDto toDto(CourseEntity courseEntity) {
        if (courseEntity == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        CourseDto courseDto = new CourseDto();
        courseDto.setId(courseEntity.getId());
        courseDto.setName(courseEntity.getName());
        courseDto.setTeacher(teacherConverter.toDto(courseEntity.getTeacher()));
        return courseDto;
    }

    public List<CourseDto> toDtoList(List<CourseEntity> courseEntityList) {
        if (courseEntityList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<CourseDto> courseDtoList = new ArrayList<>();
        for (CourseEntity courseEntity : courseEntityList) {
            courseDtoList.add(toDto(courseEntity));
        }
        return courseDtoList;
    }
    
}
