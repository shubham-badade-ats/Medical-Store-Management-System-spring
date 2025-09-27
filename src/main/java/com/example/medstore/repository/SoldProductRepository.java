package com.example.medstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medstore.model.SoldProduct;
public interface SoldProductRepository extends JpaRepository<SoldProduct, Long> {
}
