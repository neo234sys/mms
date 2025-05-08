package com.sbmtech.mms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sbmtech.mms.models.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    // You can define custom query methods here if needed
}