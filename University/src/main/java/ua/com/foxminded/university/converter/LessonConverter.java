package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.entity.LessonEntity;
import ua.com.foxminded.university.service.GroupService;
import ua.com.foxminded.university.service.TeacherService;

@Service
public class LessonConverter {
    
    private TeacherService teacherService;
	private GroupService groupService;
	
	@Autowired
    public LessonConverter(TeacherService teacherService, GroupService groupService) {
        this.teacherService = teacherService;
        this.groupService = groupService;
    }
    
    public LessonEntity toEntity(LessonDto lessonDto) {
        if (lessonDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(lessonDto.getId());
        lessonEntity.setTimetableId(lessonDto.getTimetableId());
        lessonEntity.setDate(lessonDto.getDate());
        lessonEntity.setLessonNumber(lessonDto.getLessonNumber());
        lessonEntity.setGroup(groupService.readByIdEntity(lessonDto.getGroupId()));
        lessonEntity.setCourseId(lessonDto.getCourseId());
        lessonEntity.setClassroomId(lessonDto.getClassroomId());
        lessonEntity.setTeacher(teacherService.readByIdEntity(lessonDto.getTeacherId()));
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
        lessonDto.setGroupId(lessonEntity.getGroup().getId());
        lessonDto.setCourseId(lessonEntity.getCourseId());
        lessonDto.setClassroomId(lessonEntity.getClassroomId());
        lessonDto.setTeacherId(lessonEntity.getTeacher().getId());
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
