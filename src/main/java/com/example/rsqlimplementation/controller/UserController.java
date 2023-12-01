package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.model.dto.*;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.model.type.Role;
import com.example.rsqlimplementation.service.ServiceImpl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPath.USER_API)
public class UserController {
    private final UserService userService;

    @GetMapping
    @Operation(description = "Получение списка пользователей с фильтрацией")
    public Result<List<UserDto>> getUser
        (@Parameter(description = "query - содержит условия для фильтрации")
            QuerySearchParams params
        ){
        var page = userService.get(params);
        return PageableResult.success(page.getContent(),params.getOffset(),params.getLimit(),page.getTotalElements());
    }

    @PostMapping
    @Operation(description = "Создание пользователя")
    public UserDto createUser(
            @Parameter(description = "Данные пользователя")
            @RequestBody UserCreateDto userDto
    ){
        return userService.create(userDto);
    }

    @DeleteMapping({"{id}"})
    @Operation(description = "Удаление пользователя")
    public ResponseEntity<?> createUser(
            @Parameter(description = "Данные пользователя")
            @PathVariable Long id
    ){
            userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping({"{id}"})
    @Operation(description = "Обновление пользователя")
    public ResponseEntity<?> updateUser(
            @Parameter(description = "Данные пользователя")
            @RequestBody UserPatchDto userPatchDto,
            @PathVariable Long id
            ){
        userService.update(userPatchDto,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/roles")
    @Operation(description = "Получение ролей пользователя")
    public List<Role> getRoles(
            @Parameter(description = "Id пользователя")
            @PathVariable Long id
    ){
        return userService.getRoles(id);
    }

    @PostMapping("/role/{id}")
    @Operation(description = "Выдать роль пользователю")
    public Set<Role> setRole(
            @Parameter(description = "идентификатор пользователя") @PathVariable Long id,
            @Parameter(description = "Роль") @RequestParam Role role
                           )
    {
        return userService.addRole(id,role);
    }

    @PostMapping("/role/{id}/remove")
    @Operation(description = "Выдать роль пользователю")
    public Set<Role> removeRole(
            @Parameter(description = "идентификатор пользователя") @PathVariable Long id,
            @Parameter(description = "Роль") @RequestParam Role role
    )
    {
        return userService.removeRole(id,role);
    }

}
