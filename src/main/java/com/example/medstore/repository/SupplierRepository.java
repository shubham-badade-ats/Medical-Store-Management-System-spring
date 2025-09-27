package com.example.medstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medstore.model.Supplier;
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
