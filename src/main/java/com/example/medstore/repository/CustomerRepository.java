package com.example.medstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medstore.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
