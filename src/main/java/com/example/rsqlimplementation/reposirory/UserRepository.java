package com.example.rsqlimplementation.reposirory;

import com.example.rsqlimplementation.model.domain.User;
import com.example.rsqlimplementation.model.type.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> ,EntityRepository<User,Long>{
    Optional<User> findByUsername(String userName);
    @Query("SELECT u.authorities FROM User u WHERE u.id = :userId")
    List<Role> findRolesByUserId(@Param("userId") Long userId);

    Set<User> findAllByIdIn(Set<Long> userId);
}
