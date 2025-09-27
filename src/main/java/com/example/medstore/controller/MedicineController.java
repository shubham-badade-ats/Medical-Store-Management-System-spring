package com.example.medstore.controller;

import org.springframework.web.bind.annotation.*;
import com.example.medstore.service.MedicineService;
import com.example.medstore.model.Medicine;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    private final MedicineService service;
    public MedicineController(MedicineService service){this.service=service;}

    @GetMapping
    public List<Medicine> list(){return service.findAll();}

    @GetMapping("/{id}")
    public Medicine get(@PathVariable Long id){return service.findById(id);}

    @PostMapping
    public Medicine create(@RequestBody @Valid Medicine medicine){return service.save(medicine);}

    @PutMapping("/{id}")
    public Medicine update(@PathVariable Long id, @RequestBody @Valid Medicine medicine){
        Medicine existing = service.findById(id);
        if(existing==null) return null;
        medicine.setId(id);
        return service.save(medicine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
