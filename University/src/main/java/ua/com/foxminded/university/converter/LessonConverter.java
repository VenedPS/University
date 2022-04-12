package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.entity.LessonEntity;

@Service
public class LessonConverter {
    
	@Autowired
	private TeacherConverter teacherConverter;
    
	@Autowired
	private GroupConverter groupConverter;
    
    public LessonEntity toEntity(LessonDto lessonDto) {
        if (lessonDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(lessonDto.getId());
        lessonEntity.setTimetableId(lessonDto.getTimetableId());
        lessonEntity.setDate(lessonDto.getDate());
        lessonEntity.setLessonNumber(lessonDto.getLessonNumber());
        lessonEntity.setGroup(groupConverter.toEntity(lessonDto.getGroup()));
        lessonEntity.setCourseId(lessonDto.getCourseId());
        lessonEntity.setClassroomId(lessonDto.getClassroomId());
        lessonEntity.setTeacher(teacherConverter.toEntity(lessonDto.getTeacher()));
        return lessonEntity;
    }
    
    public List<LessonEntity> toEntityList(Iterable<LessonDto> lessonDtoList) {
    	if (lessonDtoList == null) {
    		throw new IllegalArgumentException("Cannot convert null!");
    	}
    	
    	List<LessonEntity> lessonEntityList = new ArrayList<>();
    	for (LessonDto lessonDto : lessonDtoList) {
    		lessonEntityList.add(toEntity(lessonDto));
    	}
    	return lessonEntityList;
    }

    public LessonDto toDto(LessonEntity lessonEntity) {
        if (lessonEntity == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(lessonEntity.getId());
        lessonDto.setTimetableId(lessonEntity.getTimetableId());
        lessonDto.setDate(lessonEntity.getDate());
        lessonDto.setLessonNumber(lessonEntity.getLessonNumber());
        lessonDto.setGroup(groupConverter.toDto(lessonEntity.getGroup()));
        lessonDto.setCourseId(lessonEntity.getCourseId());
        lessonDto.setClassroomId(lessonEntity.getClassroomId());
        lessonDto.setTeacher(teacherConverter.toDto(lessonEntity.getTeacher()));
        return lessonDto;
    }
    
    public List<LessonDto> toDtoList(Iterable<LessonEntity> lessonEntityList) {
        if (lessonEntityList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<LessonDto> lessonDtoList = new ArrayList<>();
        for (LessonEntity lessonEntity : lessonEntityList) {
            lessonDtoList.add(toDto(lessonEntity));
        }
        return lessonDtoList;
    }
    
}
