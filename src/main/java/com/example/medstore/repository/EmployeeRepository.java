package com.example.medstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medstore.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
