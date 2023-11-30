package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.model.dto.PageableResult;
import com.example.rsqlimplementation.model.dto.Result;
import com.example.rsqlimplementation.model.dto.UserDto;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.model.type.Role;
import com.example.rsqlimplementation.service.ServiceImpl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        var page = userService.getUsers(params);
        return PageableResult.success(page.getContent(),params.getOffset(),params.getLimit(),page.getTotalElements());
    }

    @PostMapping
    @Operation(description = "Создание пользователя")
    public UserDto createUser(
            @Parameter(description = "Данные пользователя")
            @RequestBody UserDto userDto,
            @RequestParam Role role
            ){
        return userService.insertUser(userDto,role);
    }

}
