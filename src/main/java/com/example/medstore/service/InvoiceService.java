package com.example.medstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.medstore.repository.InvoiceRepository;
import com.example.medstore.repository.SoldProductRepository;
import com.example.medstore.repository.MedicineRepository;
import com.example.medstore.model.Invoice;
import com.example.medstore.model.SoldProduct;
import com.example.medstore.model.Medicine;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepo;
    private final SoldProductRepository soldRepo;
    private final MedicineRepository medicineRepo;

    public InvoiceService(InvoiceRepository invoiceRepo, SoldProductRepository soldRepo, MedicineRepository medicineRepo){
        this.invoiceRepo = invoiceRepo;
        this.soldRepo = soldRepo;
        this.medicineRepo = medicineRepo;
    }

    public List<Invoice> findAll(){ return invoiceRepo.findAll(); }
    public Invoice findById(Long id){ return invoiceRepo.findById(id).orElse(null); }

    @Transactional
    public Invoice createInvoice(Invoice invoice, List<SoldProduct> soldProducts){
        // persist invoice first
        Invoice saved = invoiceRepo.save(invoice);
        double total = 0.0;
        if(soldProducts != null){
            for(SoldProduct sp : soldProducts){
                // attach invoice
                sp.setInvoice(saved);
                // attach full medicine object if only id provided
                if(sp.getMedicine() != null && sp.getMedicine().getId() != null){
                    Medicine m = medicineRepo.findById(sp.getMedicine().getId()).orElse(null);
                    sp.setMedicine(m);
                    if(m != null && m.getQuantity() != null){
                        int remain = m.getQuantity() - (sp.getQuantity() == null ? 0 : sp.getQuantity());
                        m.setQuantity(Math.max(0, remain));
                        medicineRepo.save(m);
                    }
                }
                if(sp.getPrice() != null && sp.getQuantity() != null){
                    total += sp.getPrice() * sp.getQuantity();
                }
                soldRepo.save(sp);
            }
        }
        saved.setTotalAmount(total);
        return invoiceRepo.save(saved);
    }
}
