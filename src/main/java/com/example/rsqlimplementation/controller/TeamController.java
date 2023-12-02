package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.model.dto.*;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.service.ServiceImpl.TeamService;
import com.example.rsqlimplementation.service.ServiceImpl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.TEAM_API)
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @Operation(description = "Создать команду")
    public TeamDto createTeam(@RequestBody TeamCreateDto teamCreateDto){
        return teamService.create(teamCreateDto);
    }

    @PatchMapping("{id}")
    @Operation(description = "Изменить команду или ее состав")
    public TeamDto updateTeam(
            @RequestBody TeamPatchDto teamPatchDto,
            @PathVariable Long id
            ){
        return teamService.update(teamPatchDto,id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Удалить команду")
    public void deleteTeam(
            @PathVariable Long id
    ){
        teamService.delete(id);
    }

    @GetMapping
    @Operation(description = "Получение списка команд с фильтрацией")
    public Result<List<TeamDto>> get(QuerySearchParams params){
        var page = teamService.get(params);
        return PageableResult.success(page.getContent(),params.getOffset(),params.getLimit(),page.getTotalElements());
    }


}
