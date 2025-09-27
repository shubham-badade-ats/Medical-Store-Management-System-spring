package com.example.medstore.controller;

import org.springframework.web.bind.annotation.*;
import com.example.medstore.service.SoldProductService;
import com.example.medstore.model.SoldProduct;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sold-products")
public class SoldProductController {
    private final SoldProductService service;
    public SoldProductController(SoldProductService service){this.service=service;}

    @GetMapping
    public List<SoldProduct> list(){return service.findAll();}

    @GetMapping("/{id}")
    public SoldProduct get(@PathVariable Long id){return service.findById(id);}

    @PostMapping
    public SoldProduct create(@RequestBody @Valid SoldProduct obj){return service.save(obj);}

    @PutMapping("/{id}")
    public SoldProduct update(@PathVariable Long id, @RequestBody @Valid SoldProduct obj){
        SoldProduct existing = service.findById(id);
        if(existing==null) return null;
        obj.setId(id);
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
