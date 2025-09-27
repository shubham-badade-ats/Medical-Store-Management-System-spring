package com.example.medstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medstore.model.Medicine;
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
