package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.model.dto.PageableResult;
import com.example.rsqlimplementation.model.dto.Result;
import com.example.rsqlimplementation.model.dto.TaskCreateDto;
import com.example.rsqlimplementation.model.dto.TaskDto;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.service.ServiceImpl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImpl taskService;

    @GetMapping("/tasks")
    public Result<List<TaskDto>> getTask(QuerySearchParams params){
        var page =  taskService.get(params);
        return PageableResult.success(page.getContent(),params.getOffset(),params.getLimit(),page.getTotalElements());
    }

    @PostMapping("/create")
    public Result<TaskDto> createTask(@RequestBody TaskCreateDto taskCreateDto){
        return Result.success(taskService.create(taskCreateDto));
    }
}
