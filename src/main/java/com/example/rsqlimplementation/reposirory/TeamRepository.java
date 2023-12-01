package com.example.rsqlimplementation.reposirory;

import com.example.rsqlimplementation.model.domain.Team;
import com.example.rsqlimplementation.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long>, JpaSpecificationExecutor<Team>,EntityRepository<Team,Long>{
}
