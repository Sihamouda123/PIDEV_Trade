package com.PIdevv.PI.Service;

import com.PIdevv.PI.Entities.Trading;

import java.util.List;

public interface ITradingService {
    List<Trading> findAll();

    Trading save (Trading trading) ;

    Trading update ( Trading trading);

    Trading findById (Long id);

    void delete(Long id);
}
