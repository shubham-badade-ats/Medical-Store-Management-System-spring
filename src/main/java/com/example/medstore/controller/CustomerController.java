package com.example.medstore.controller;

import org.springframework.web.bind.annotation.*;
import com.example.medstore.service.CustomerService;
import com.example.medstore.model.Customer;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service){this.service=service;}

    @GetMapping
    public List<Customer> list(){return service.findAll();}

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id){return service.findById(id);}

    @PostMapping
    public Customer create(@RequestBody @Valid Customer obj){return service.save(obj);}

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody @Valid Customer obj){
        Customer existing = service.findById(id);
        if(existing==null) return null;
        obj.setId(id);
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
