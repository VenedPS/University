package ua.com.foxminded.university.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dto.GroupDto;
import ua.com.foxminded.university.entity.GroupEntity;

@Service
public class GroupConverter {
    
    public GroupEntity toEntity(GroupDto groupDto) {
        if (groupDto == null) {
            throw new IllegalArgumentException("Cannot convert null!");
        }

        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(groupDto.getId());
        groupEntity.setName(groupDto.getName());
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
