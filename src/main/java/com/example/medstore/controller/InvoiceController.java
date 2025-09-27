package com.example.medstore.controller;

import org.springframework.web.bind.annotation.*;
import com.example.medstore.service.InvoiceService;
import com.example.medstore.model.Invoice;
import com.example.medstore.model.SoldProduct;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService service;
    public InvoiceController(InvoiceService service){this.service=service;}

    @GetMapping
    public List<Invoice> list(){ return service.findAll(); }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable Long id){ return service.findById(id); }

    // Accepts JSON { "invoice": { ... }, "soldProducts": [ {...}, {...} ] }
    public static class InvoiceRequest {
        public Invoice invoice;
        public List<SoldProduct> soldProducts;
    }

    @PostMapping
    public Invoice create(@RequestBody @Valid InvoiceRequest req){
        return service.createInvoice(req.invoice, req.soldProducts);
    }
}
