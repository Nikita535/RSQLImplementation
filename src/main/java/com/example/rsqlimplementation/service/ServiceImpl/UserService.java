package com.example.rsqlimplementation.service.ServiceImpl;


import com.example.rsqlimplementation.config.jwt.JwtUtil;
import com.example.rsqlimplementation.controller.AuthController;
import com.example.rsqlimplementation.exception.BadRequestException;
import com.example.rsqlimplementation.exception.UserServiceMessages;
import com.example.rsqlimplementation.model.type.Role;
import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.dto.RegisterRequestDto;
import com.example.rsqlimplementation.model.dto.UserDto;
import com.example.rsqlimplementation.reposirory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public void save(User user) {
        repository.save(user);
    }


    public ResponseEntity<AuthController.JwtResponse> loginUser(UserDto userDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        user.setEmail(userDto.getEmail());
        String jwt = jwtUtil.generateToken(user.getUsername());

        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(
                new AuthController.JwtResponse(
                        jwt, user.getId(),
                        user.getEmail(),
                        user.getUsername(),
                        authorities)
        );
    }

    public void registerUser(RegisterRequestDto registerRequestDTO) {
        if (repository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw new BadRequestException(UserServiceMessages.USER_ALREADY_EXISTS,registerRequestDTO.getUsername());
        }
        save(User.builder()
                .email(registerRequestDTO.getEmail())
                .username(registerRequestDTO.getUsername())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .authorities(Set.of(Role.ROLE_USER))
                .build()
        );


    }
}
