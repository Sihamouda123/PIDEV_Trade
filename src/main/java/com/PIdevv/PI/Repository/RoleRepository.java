package com.PIdevv.PI.Repository;


import com.PIdevv.PI.Entities.ERole;
import com.PIdevv.PI.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(ERole name);
}
