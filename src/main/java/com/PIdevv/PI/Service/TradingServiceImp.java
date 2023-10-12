package com.PIdevv.PI.Service;

import com.PIdevv.PI.Entities.Trading;
import com.PIdevv.PI.Repository.TradingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingServiceImp implements ITradingService{
    @Autowired
    TradingRepository tradingRepository;
    @Override
    public List<Trading> findAll() {
        return tradingRepository.findAll();
    }
    @Override
    public Trading save(Trading trading) {
        return tradingRepository.save(trading);
    }

    @Override
    public Trading update( Trading trading) {
        return tradingRepository.save(trading);
    }
    @Override
    public Trading findById(Long id) {
        return tradingRepository.findById(id).get();
    }
    @Override
    public void delete(Long id) {
        tradingRepository.deleteById(id);
    }

}
