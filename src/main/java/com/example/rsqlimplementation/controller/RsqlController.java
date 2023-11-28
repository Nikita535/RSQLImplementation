package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.mapper.TaskMapper;
import com.example.rsqlimplementation.model.domain.Task;
import com.example.rsqlimplementation.model.dto.TaskDto;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.reposirory.TaskRepository;
import io.github.perplexhub.rsql.RSQLCommonSupport;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RsqlController {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @GetMapping("/get")
    public ResponseEntity<?> getTask(QuerySearchParams params){
        return ResponseEntity.of(Optional.of(taskRepository.findAll(RSQLJPASupport.rsql(params.getQuery()),params.getPageRequest())));
    }

    @GetMapping("/getdto")
    public ResponseEntity<?> getTaskDto(@RequestParam(name = "id") Long id){
        Task task = taskRepository.findById(id).get();
        TaskDto taskDto = taskMapper.domainToDto(task);
        return ResponseEntity.ok(taskDto);
    }
}
