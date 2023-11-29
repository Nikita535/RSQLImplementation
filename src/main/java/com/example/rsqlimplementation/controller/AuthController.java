package com.example.rsqlimplementation.controller;

import com.example.rsqlimplementation.model.dto.RegisterRequestDto;
import com.example.rsqlimplementation.model.dto.RegisterResponseDto;
import com.example.rsqlimplementation.model.dto.UserDto;
import com.example.rsqlimplementation.service.ServiceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPath.AUTH_API)
public class AuthController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDTO) {
        userService.registerUser(registerRequestDTO);
        return ResponseEntity.ok(new RegisterResponseDto("Пользователь зарегистрирован!"));
    }



    public record JwtResponse(String jwt, Long id, String email, String username, List<String> authorities) {
    }

}
