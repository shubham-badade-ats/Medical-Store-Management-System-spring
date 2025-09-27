package com.example.medstore.service;

import org.springframework.stereotype.Service;
import com.example.medstore.repository.SoldProductRepository;
import com.example.medstore.model.SoldProduct;
import java.util.List;

@Service
public class SoldProductService {
    private final SoldProductRepository repo;
    public SoldProductService(SoldProductRepository repo){this.repo=repo;}
    public List<SoldProduct> findAll(){return repo.findAll();}
    public SoldProduct findById(Long id){return repo.findById(id).orElse(null);}
    public SoldProduct save(SoldProduct obj){return repo.save(obj);}
    public void delete(Long id){repo.deleteById(id);}
}
