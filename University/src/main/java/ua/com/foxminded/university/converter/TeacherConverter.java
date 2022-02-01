package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.entity.TeacherEntity;

@Service
public class TeacherConverter {
    public TeacherEntity toEntity(TeacherDto teacherDto) {
        if (teacherDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(teacherDto.getId());
        teacherEntity.setFirstName(teacherDto.getFirstName());
        teacherEntity.setSecondName(teacherDto.getSecondName());
        teacherEntity.setBirthDate(teacherDto.getBirthDate());
        teacherEntity.setAddress(teacherDto.getAddress());
        teacherEntity.setPhone(teacherDto.getPhone());
        teacherEntity.setEmail(teacherDto.getEmail());
        return teacherEntity;
    }

    public List<TeacherEntity> toEntityList(List<TeacherDto> teacherDtoList) {
        if (teacherDtoList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<TeacherEntity> teacherEntityList = new ArrayList<>();
        for (TeacherDto teacherDto : teacherDtoList) {
            teacherEntityList.add(toEntity(teacherDto));
        }
        return teacherEntityList;
    }

    public TeacherDto toDto(TeacherEntity teacherEntity) {
        if (teacherEntity == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacherEntity.getId());
        teacherDto.setFirstName(teacherEntity.getFirstName());
        teacherDto.setSecondName(teacherEntity.getSecondName());
        teacherDto.setBirthDate(teacherEntity.getBirthDate());
        teacherDto.setAddress(teacherEntity.getAddress());
        teacherDto.setPhone(teacherEntity.getPhone());
        teacherDto.setEmail(teacherEntity.getEmail());
        return teacherDto;
    }

    public List<TeacherDto> toDtoList(List<TeacherEntity> teacherEntityList) {
        if (teacherEntityList == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        List<TeacherDto> teacherDtoList = new ArrayList<>();
        for (TeacherEntity teacherEntity : teacherEntityList) {
            teacherDtoList.add(toDto(teacherEntity));
        }
        return teacherDtoList;
    }
}
