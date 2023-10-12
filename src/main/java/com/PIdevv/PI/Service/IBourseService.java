package com.PIdevv.PI.Service;

import com.PIdevv.PI.Entities.Bourse;
import com.PIdevv.PI.Entities.Devise;

import java.util.List;

public interface IBourseService {
    List<Bourse> findAll();

    Bourse save (Bourse bourse) ;

    Bourse update ( Bourse bourse);

    Bourse findById (Long id);

    void delete(Long id);
}
