package com.example.rsqlimplementation.reposirory;

import com.example.rsqlimplementation.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> ,EntityRepository<User,Long>{
    Optional<User> findByUsername(String userName);
}
