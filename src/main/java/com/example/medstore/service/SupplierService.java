package com.example.medstore.service;

import org.springframework.stereotype.Service;
import com.example.medstore.repository.SupplierRepository;
import com.example.medstore.model.Supplier;
import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository repo;
    public SupplierService(SupplierRepository repo){this.repo=repo;}
    public List<Supplier> findAll(){return repo.findAll();}
    public Supplier findById(Long id){return repo.findById(id).orElse(null);}
    public Supplier save(Supplier obj){return repo.save(obj);}
    public void delete(Long id){repo.deleteById(id);}
}
