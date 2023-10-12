package com.PIdevv.PI.Controller;

import com.PIdevv.PI.Entities.Devise;
import com.PIdevv.PI.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devise")
@CrossOrigin(origins = "*")
public class DeviseController {
    @Autowired
    DeviceService deviceService;
    @GetMapping()
    public List<Devise> findAll() {
        return deviceService.findAll();
    }
    @GetMapping("/{id}")
    public Devise findById(@PathVariable("id") Long id) {
        return deviceService.findById(id);
    }
    @PostMapping()
    public Devise save(@RequestBody Devise devise) {
        return deviceService.save(devise);

    }
    @PutMapping()
    public Devise update( @RequestBody Devise devise) {
        return deviceService.update(devise);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id) {
        deviceService.delete(id);
    }

}
