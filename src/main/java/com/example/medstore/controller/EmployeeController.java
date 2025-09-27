package com.example.medstore.controller;

import org.springframework.web.bind.annotation.*;
import com.example.medstore.service.EmployeeService;
import com.example.medstore.model.Employee;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service){this.service=service;}

    @GetMapping
    public List<Employee> list(){return service.findAll();}

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id){return service.findById(id);}

    @PostMapping
    public Employee create(@RequestBody @Valid Employee obj){return service.save(obj);}

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody @Valid Employee obj){
        Employee existing = service.findById(id);
        if(existing==null) return null;
        obj.setId(id);
        return service.save(obj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
