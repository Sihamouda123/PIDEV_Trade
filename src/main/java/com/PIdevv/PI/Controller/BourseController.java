package com.PIdevv.PI.Controller;

import com.PIdevv.PI.Entities.Bourse;
import com.PIdevv.PI.Service.BourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bourse")
@CrossOrigin(origins = "*")
public class BourseController {
    @Autowired
    BourseService bourseService;
    @GetMapping()
    public List<Bourse> findAll() {
        return bourseService.findAll();
    }
    @GetMapping("/{id}")
    public Bourse findById(@PathVariable("id") Long id) {
        return bourseService.findById(id);
    }
    @PostMapping()
    public Bourse save(@RequestBody Bourse bourse) {
        return bourseService.save(bourse);

    }
    @PutMapping()
    public Bourse update( @RequestBody Bourse bourse) {
        return bourseService.update(bourse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id) {
        bourseService.delete(id);
    }

}
