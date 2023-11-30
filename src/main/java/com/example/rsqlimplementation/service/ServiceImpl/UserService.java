package com.example.rsqlimplementation.service.ServiceImpl;


import com.example.rsqlimplementation.config.jwt.JwtUtil;
import com.example.rsqlimplementation.controller.AuthController;
import com.example.rsqlimplementation.exception.UserAlreadyExistException;
import com.example.rsqlimplementation.exception.UserNotFoundException;
import com.example.rsqlimplementation.exception.UserServiceMessages;
import com.example.rsqlimplementation.mapper.UserMapper;
import com.example.rsqlimplementation.model.dto.UserCreateDto;
import com.example.rsqlimplementation.model.dto.UserPatchDto;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.model.type.Role;
import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.dto.RegisterRequestDto;
import com.example.rsqlimplementation.model.dto.UserDto;
import com.example.rsqlimplementation.reposirory.UserRepository;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class UserService extends CrudServiceImpl<User, UserCreateDto, UserPatchDto,UserDto,Long,UserRepository>{

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserService(UserRepository repository, UserMapper mapper,JwtUtil jwtUtil,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager) {
        super(repository, mapper);
        this.jwtUtil = jwtUtil;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
    }

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

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        if (repository.findByUsername(userCreateDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistException(UserServiceMessages.USER_ALREADY_EXISTS,userCreateDto.getUsername());
        }
        User user = entityMapper.toEntityFromCreateDto(userCreateDto);
        user.setAuthorities(Set.of(userCreateDto.getRole()));
        user.setActive(true);
        save(user);
        return mapper.domainToDto(user);
    }

    @Override
    public UserDto update(UserPatchDto userPatchDto, Long id) {
        if (repository.findById(id).isEmpty()){
            throw new UserNotFoundException(UserServiceMessages.USER_NOT_FOUND,id);
        }
        User user = repository.findById(id).get();
        user = entityMapper.toEntityFromCreateDtoPatch(user,userPatchDto);
        repository.save(user);
        return mapper.domainToDto(user);
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new UserNotFoundException(UserServiceMessages.USER_NOT_FOUND,id);
        }
        User user = repository.findById(id).get();
        user.setActive(false);
        repository.save(user);
    }

    @Override
    public Page<UserDto> get(QuerySearchParams params) {
        Page<User> page = repository.findAll(RSQLJPASupport.rsql(params.getQuery()),params.getPageRequest());
        return new PageImpl<>(
                mapper.domainsToDtos(page.getContent()),
                params.getPageRequest(),
                page.getTotalElements()
        );
    }

    @Override
    public UserDto getById(Long aLong) {
        return super.getById(aLong);
    }

    public void registerUser(RegisterRequestDto registerRequestDTO) {
        if (repository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistException(UserServiceMessages.USER_ALREADY_EXISTS,registerRequestDTO.getUsername());
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
