package com.example.rsqlimplementation.service.ServiceImpl;

import com.example.rsqlimplementation.config.jwt.JwtUtil;
import com.example.rsqlimplementation.mapper.TeamMapper;
import com.example.rsqlimplementation.mapper.UserMapper;
import com.example.rsqlimplementation.model.domain.Team;
import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.dto.*;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.reposirory.TeamRepository;
import com.example.rsqlimplementation.reposirory.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TeamService extends CrudServiceImpl<Team, TeamCreateDto, TeamPatchDto, TeamDto,Long, TeamRepository>{

    private UserRepository userRepository;
    public TeamService(TeamRepository repository, TeamMapper mapper,UserRepository userRepository) {
        super(repository, mapper);
        this.userRepository=userRepository;
    }

    @Override
    public TeamDto create(TeamCreateDto teamCreateDto) {
        Team team = entityMapper.toEntityFromCreateDto(teamCreateDto);
        repository.save(team);
        return mapper.domainToDto(team);
    }

    @Override
    public TeamDto update(TeamPatchDto teamPatchDto, Long id) {
        //TODO сделать проверку
        Set<User> newUsers=null;

        Team team = repository.findById(id).get();
        if (teamPatchDto.getUsersId() != null && !teamPatchDto.getUsersId().isEmpty()) {
            newUsers = userRepository.findAllByIdIn(teamPatchDto.getUsersId());
            team.getUsers().addAll(newUsers);
        }

        if(newUsers != null) {
            for (User user : newUsers) {
                user.setTeam(team);
            }
            userRepository.saveAll(newUsers);
        }
        return mapper.domainToDto(team);
    }

    @Override
    public void delete(Long aLong) {
        super.delete(aLong);
    }

    @Override
    public Page<TeamDto> get(QuerySearchParams params) {
        return super.get(params);
    }

    @Override
    public TeamDto getById(Long aLong) {
        return super.getById(aLong);
    }
}
