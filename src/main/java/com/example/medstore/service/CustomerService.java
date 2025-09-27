package com.example.medstore.service;

import org.springframework.stereotype.Service;
import com.example.medstore.repository.CustomerRepository;
import com.example.medstore.model.Customer;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo){this.repo=repo;}
    public List<Customer> findAll(){return repo.findAll();}
    public Customer findById(Long id){return repo.findById(id).orElse(null);}
    public Customer save(Customer obj){return repo.save(obj);}
    public void delete(Long id){repo.deleteById(id);}
}
