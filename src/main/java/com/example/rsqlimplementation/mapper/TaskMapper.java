package com.example.rsqlimplementation.mapper;

import com.example.rsqlimplementation.model.domain.Task;
import com.example.rsqlimplementation.model.dto.TaskCreateDto;
import com.example.rsqlimplementation.model.dto.TaskDto;
import com.example.rsqlimplementation.model.dto.TaskUpdateDto;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapperConfig.class)
public interface TaskMapper extends EntityMapper<Task, TaskDto, TaskCreateDto, TaskUpdateDto> {
}
