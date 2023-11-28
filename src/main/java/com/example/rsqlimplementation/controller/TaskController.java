package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.mapper.TaskMapper;
import com.example.rsqlimplementation.model.dto.PageableResult;
import com.example.rsqlimplementation.model.dto.Result;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.reposirory.TaskRepository;
import com.example.rsqlimplementation.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public Result<?> getTask(QuerySearchParams params){
        var page =  taskService.getPage(params);
        return PageableResult.success(page.getContent(),params.getOffset(),params.getLimit(),page.getTotalElements());
    }
}
