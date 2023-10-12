package com.PIdevv.PI.Controller;

import com.PIdevv.PI.Entities.Trading;
import com.PIdevv.PI.Service.TradingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trading")
@CrossOrigin(origins = "*")
public class TradingController {
    @Autowired
    TradingServiceImp tradingServiceImp;
    @GetMapping()
    public List<Trading> findAll() {
        return tradingServiceImp.findAll();
    }
    @GetMapping("/{id}")
    public Trading findById(@PathVariable("id") Long id) {
        return tradingServiceImp.findById(id);
    }
    @PostMapping()
    public Trading save(@RequestBody Trading trading) {
        return tradingServiceImp.save(trading);

    }
    @PutMapping()
    public Trading update( @RequestBody Trading trading) {
        return tradingServiceImp.update(trading);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id) {
        tradingServiceImp.delete(id);
    }

}
