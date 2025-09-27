package com.example.medstore.service;

import org.springframework.stereotype.Service;
import com.example.medstore.repository.EmployeeRepository;
import com.example.medstore.model.Employee;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo){this.repo=repo;}
    public List<Employee> findAll(){return repo.findAll();}
    public Employee findById(Long id){return repo.findById(id).orElse(null);}
    public Employee save(Employee obj){return repo.save(obj);}
    public void delete(Long id){repo.deleteById(id);}
}
