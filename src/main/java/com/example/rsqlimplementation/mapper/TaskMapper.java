package com.example.rsqlimplementation.mapper;

import com.example.rsqlimplementation.model.domain.Task;
import com.example.rsqlimplementation.model.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(config = EntityMapperConfig.class)
public interface TaskMapper extends BaseMapper<Task, TaskDto> {
}
