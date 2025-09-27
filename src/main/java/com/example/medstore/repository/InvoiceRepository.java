package com.example.medstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medstore.model.Invoice;
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
