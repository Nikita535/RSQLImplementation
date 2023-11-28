package com.example.rsqlimplementation.service;

import com.example.rsqlimplementation.mapper.TaskMapper;
import com.example.rsqlimplementation.model.domain.Task;
import com.example.rsqlimplementation.model.dto.TaskDto;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.reposirory.TaskRepository;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public Page<TaskDto> getPage(QuerySearchParams params) {
        var page = taskRepository.findAll(RSQLJPASupport.rsql(params.getQuery()),params.getPageRequest());
        return new PageImpl<>(
                taskMapper.domainsToDtos(page.getContent()),
                params.getPageRequest(),
                page.getTotalElements()
        );
    }
}
