package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.model.dto.TeamCreateDto;
import com.example.rsqlimplementation.model.dto.TeamDto;
import com.example.rsqlimplementation.model.dto.TeamPatchDto;
import com.example.rsqlimplementation.service.ServiceImpl.TeamService;
import com.example.rsqlimplementation.service.ServiceImpl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @Operation(description = "Изменить комнду")
    public TeamDto updateTeam(
            @RequestBody TeamPatchDto teamPatchDto,
            @PathVariable Long id
            ){
        return teamService.update(teamPatchDto,id);
    }
}
