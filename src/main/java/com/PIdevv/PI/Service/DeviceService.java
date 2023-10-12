package com.PIdevv.PI.Service;

import com.PIdevv.PI.Entities.Devise;
import com.PIdevv.PI.Repository.DeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceService implements IDeviseService {
    @Autowired
    DeviseRepository deviseRepository;
    @Override
    public List<Devise> findAll() {
        return deviseRepository.findAll();
    }
    @Override
    public Devise save(Devise devise) {
        return deviseRepository.save(devise);
    }

    @Override
    public Devise update( Devise devise) {
        return deviseRepository.save(devise);
    }
    @Override
    public Devise findById(Long id) {
        return deviseRepository.findById(id).get();
    }
    @Override
    public void delete(Long id) {
        deviseRepository.deleteById(id);
    }

}
