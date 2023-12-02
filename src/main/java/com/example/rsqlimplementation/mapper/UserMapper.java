package com.example.rsqlimplementation.mapper;

import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.dto.UserCreateDto;
import com.example.rsqlimplementation.model.dto.UserDto;
import com.example.rsqlimplementation.model.dto.UserPatchDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(config = EntityMapperConfig.class)
public interface UserMapper extends EntityMapper<User, UserDto, UserCreateDto, UserPatchDto> {
    @Override
    @Mappings({
            @Mapping(source = "teamId",target = "team.id")
    })
    User dtoToDomain(UserDto userDto);

    @Override
    @Mappings({
            @Mapping(source = "team.id",target = "teamId")
    })
    UserDto domainToDto(User domain);

    @Override
    @Mappings({
            @Mapping(target = "username", source = "userPatchDto.username", defaultExpression = "java(getDefaultValue(entity.getUsername(), userPatchDto.getUsername()))"),
            @Mapping(target = "email", source = "userPatchDto.email", defaultExpression = "java(getDefaultValue(entity.getEmail(), userPatchDto.getEmail()))"),
            @Mapping(target = "firstName", source = "userPatchDto.firstName", defaultExpression = "java(getDefaultValue(entity.getFirstName(), userPatchDto.getFirstName()))"),
            @Mapping(target = "lastName", source = "userPatchDto.lastName", defaultExpression = "java(getDefaultValue(entity.getLastName(), userPatchDto.getLastName()))"),
            @Mapping(target = "middleName", source = "userPatchDto.middleName", defaultExpression = "java(getDefaultValue(entity.getMiddleName(), userPatchDto.getMiddleName()))"),
            @Mapping(target = "mobilePhoneNumber", source = "userPatchDto.mobilePhoneNumber", defaultExpression = "java(getDefaultValue(entity.getMobilePhoneNumber(), userPatchDto.getMobilePhoneNumber()))"),
    })
            User toEntityFromCreateDtoPatch(User entity, UserPatchDto userPatchDto);

    default String getDefaultValue(String currentValue, String newValue) {
        return newValue != null ? newValue : currentValue;
    }
}
