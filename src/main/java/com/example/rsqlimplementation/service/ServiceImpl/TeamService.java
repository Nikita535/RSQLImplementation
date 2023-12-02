package com.example.rsqlimplementation.service.ServiceImpl;

import com.example.rsqlimplementation.config.jwt.JwtUtil;
import com.example.rsqlimplementation.exception.TeamNotFoundException;
import com.example.rsqlimplementation.exception.TeamServiceMessages;
import com.example.rsqlimplementation.exception.UserNotFoundException;
import com.example.rsqlimplementation.mapper.TeamMapper;
import com.example.rsqlimplementation.mapper.UserMapper;
import com.example.rsqlimplementation.model.domain.Team;
import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.dto.*;
import com.example.rsqlimplementation.model.param.QuerySearchParams;
import com.example.rsqlimplementation.reposirory.TeamRepository;
import com.example.rsqlimplementation.reposirory.UserRepository;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Team team = repository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(TeamServiceMessages.TEAM_NOT_FOUND, id));

        Set<Long> userIds = teamPatchDto.getUsersId();

        if (userIds != null && !userIds.isEmpty()) {
            Set<User> existingUsers = userRepository.findAllByIdIn(userIds);
            if (existingUsers.size() != userIds.size()) {
                Set<Long> missingUserIds = new HashSet<>(userIds);
                missingUserIds.removeAll(existingUsers.stream().map(User::getId).collect(Collectors.toSet()));
                throw new UserNotFoundException("Users not found with ids: " + missingUserIds);
            }
            for (User user : existingUsers) {
                user.setTeam(team);
            }
        }

        if(teamPatchDto.getName()!=null) team.setName(teamPatchDto.getName());
        if(teamPatchDto.getDescription()!=null) team.setDescription(teamPatchDto.getDescription());

        repository.save(team);

        return mapper.domainToDto(team);
    }

    @Override
    public void delete(Long id) {
        Team teamToDelete = repository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(TeamServiceMessages.TEAM_NOT_FOUND, id));
        for (User user : teamToDelete.getUsers()) {
            user.setTeam(null);
        }
        userRepository.saveAll(teamToDelete.getUsers());
        repository.delete(teamToDelete);
    }

    @Override
    public Page<TeamDto> get(QuerySearchParams params) {
        Page<Team> page = repository.findAll(RSQLJPASupport.rsql(params.getQuery()),params.getPageRequest());
        return new PageImpl<>(
                mapper.domainsToDtos(page.getContent()),
                params.getPageRequest(),
                page.getTotalElements()
        );
    }

    @Override
    public TeamDto getById(Long aLong) {
        return super.getById(aLong);
    }
}
