package com.PIdevv.PI.Repository;

import com.PIdevv.PI.Entities.Bourse;
import com.PIdevv.PI.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BourseRepository extends JpaRepository<Bourse,Long> {
}
