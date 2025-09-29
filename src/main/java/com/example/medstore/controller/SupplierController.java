package com.example.medstore.controller;

import org.springframework.web.bind.annotation.*;
import com.example.medstore.service.SupplierService;
import com.example.medstore.model.Supplier;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin
public class SupplierController {
    private final SupplierService service;
    public SupplierController(SupplierService service){this.service=service;}

    @GetMapping
    public List<Supplier> list(){
    	
    	System.out.println("In Contoller");
    	return service.findAll();}

    @GetMapping("/{id}")
    public Supplier get(@PathVariable Long id){return service.findById(id);}

    @PostMapping
    public Supplier create(@RequestBody @Valid Supplier obj){return service.save(obj);}

    @PutMapping("/{id}")
    public Supplier update(@PathVariable Long id, @RequestBody @Valid Supplier obj){
        Supplier existing = service.findById(id);
        if(existing==null) return null;
        obj.setId(id);
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
