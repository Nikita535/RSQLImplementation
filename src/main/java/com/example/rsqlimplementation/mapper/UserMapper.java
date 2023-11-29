package com.example.rsqlimplementation.mapper;

import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapperConfig.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
