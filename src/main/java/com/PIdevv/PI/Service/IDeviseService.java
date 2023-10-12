package com.PIdevv.PI.Service;

import com.PIdevv.PI.Entities.Devise;

import java.util.List;

public interface IDeviseService {
    List<Devise> findAll();

    Devise save (Devise devise) ;

    Devise update ( Devise setup);

    Devise findById (Long id);

    void delete(Long id);
}
