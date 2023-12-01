package com.example.rsqlimplementation.mapper;

import com.example.rsqlimplementation.model.domain.Team;
import com.example.rsqlimplementation.model.dto.TeamCreateDto;
import com.example.rsqlimplementation.model.dto.TeamDto;
import com.example.rsqlimplementation.model.dto.TeamPatchDto;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapperConfig.class,uses = {UserMapper.class})
public interface TeamMapper extends EntityMapper<Team, TeamDto, TeamCreateDto, TeamPatchDto>{
}
