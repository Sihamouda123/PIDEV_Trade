package com.PIdevv.PI.Repository;

import com.PIdevv.PI.Entities.Devise;
import com.PIdevv.PI.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise,Long> {
}
