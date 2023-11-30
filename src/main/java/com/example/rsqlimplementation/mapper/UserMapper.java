package com.example.rsqlimplementation.mapper;

import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.dto.UserCreateDto;
import com.example.rsqlimplementation.model.dto.UserDto;
import com.example.rsqlimplementation.model.dto.UserPatchDto;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapperConfig.class)
public interface UserMapper extends EntityMapper<User, UserDto, UserCreateDto, UserPatchDto> {
}
