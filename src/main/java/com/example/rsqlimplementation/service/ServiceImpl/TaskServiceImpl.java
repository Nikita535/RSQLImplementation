package com.example.rsqlimplementation.service.ServiceImpl;

import com.example.rsqlimplementation.mapper.TaskMapper;
import com.example.rsqlimplementation.model.domain.Task;
import com.example.rsqlimplementation.model.dto.TaskCreateDto;
import com.example.rsqlimplementation.model.dto.TaskDto;
import com.example.rsqlimplementation.model.dto.TaskUpdateDto;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.reposirory.TaskRepository;
import com.example.rsqlimplementation.service.GetService;
import com.example.rsqlimplementation.service.TaskService;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends CrudServiceImpl<Task, TaskCreateDto, TaskUpdateDto,TaskDto,Long,TaskRepository> implements TaskService{

    public TaskServiceImpl(TaskRepository repository,TaskMapper mapper){
        super(repository,mapper);
    }

    @Override
    public TaskDto create(TaskCreateDto taskCreateDto) {
        Task task = entityMapper.toEntityFromCreateDto(taskCreateDto);
        return mapper.domainToDto(repository.save(task));
    }

    @Override
    public TaskDto update(TaskUpdateDto taskUpdateDto, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {
    }

    @Override
    public Page<TaskDto> get(QuerySearchParams params) {
        var page = repository.findAll(RSQLJPASupport.rsql(params.getQuery()),params.getPageRequest());
        return new PageImpl<>(
                mapper.domainsToDtos(page.getContent()),
                params.getPageRequest(),
                page.getTotalElements()
        );
    }


//    public Page<TaskDto> getPage(QuerySearchParams params) {
//        var page = taskRepository.findAll(RSQLJPASupport.rsql(params.getQuery()),params.getPageRequest());
//        return new PageImpl<>(
//                taskMapper.domainsToDtos(page.getContent()),
//                params.getPageRequest(),
//                page.getTotalElements()
//        );
//    }

}
