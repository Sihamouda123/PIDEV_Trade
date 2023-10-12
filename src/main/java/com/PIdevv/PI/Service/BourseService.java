package com.PIdevv.PI.Service;

import com.PIdevv.PI.Entities.Bourse;
import com.PIdevv.PI.Repository.BourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BourseService implements  IBourseService {
    @Autowired
    BourseRepository bourseRepository;

    @Override
    public List<Bourse> findAll() {
        return bourseRepository.findAll();
    }
    @Override
    public Bourse save(Bourse bourse) {
        return bourseRepository.save(bourse);
    }

    @Override
    public Bourse update( Bourse bourse) {
        return bourseRepository.save(bourse);
    }
    @Override
    public Bourse findById(Long id) {
        return bourseRepository.findById(id).get();
    }
    @Override
    public void delete(Long id) {
        bourseRepository.deleteById(id);
    }

}

