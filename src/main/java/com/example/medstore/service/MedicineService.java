package com.example.medstore.service;

import org.springframework.stereotype.Service;
import com.example.medstore.repository.MedicineRepository;
import com.example.medstore.model.Medicine;
import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository repo;
    public MedicineService(MedicineRepository repo){this.repo=repo;}
    public List<Medicine> findAll(){return repo.findAll();}
    public Medicine findById(Long id){return repo.findById(id).orElse(null);}
    public Medicine save(Medicine m){return repo.save(m);}
    public void delete(Long id){repo.deleteById(id);}
}
