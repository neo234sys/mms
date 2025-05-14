package com.sbmtech.mms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.models.CustomIdGenerator;
import com.sbmtech.mms.models.Invoice;
import com.sbmtech.mms.repository.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private CustomIdGenerator idGenerator;
    
    
    @Transactional
    public Invoice createBuilding(String customerName) {
    	Invoice invoice = new Invoice();
        invoice.setCustomerName(customerName);

        String generatedId = idGenerator.generate("BUL"); // Reusable generator
        invoice.setId(generatedId);

        return invoiceRepository.save(invoice);
    }
    
    @Transactional
    public Invoice createTenant(String customerName) {
    	Invoice invoice = new Invoice();
        invoice.setCustomerName(customerName);

        String generatedId = idGenerator.generate("TEN"); // Reusable generator
        invoice.setId(generatedId);

        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice createUnit(String customerName) {
    	Invoice invoice = new Invoice();
        invoice.setCustomerName(customerName);

        String generatedId = idGenerator.generate("UNT"); // Reusable generator
        invoice.setId(generatedId);

        return invoiceRepository.save(invoice);
    }
    
    
//    @Transactional
//    public String generateCustomId(String prefix) {
//     //   EntityManager em = entityManager;
//
//        // Lock the row
//        Integer lastNumber = (Integer) em.createNativeQuery(
//            "SELECT last_number FROM id_sequence WHERE prefix = :prefix FOR UPDATE")
//            .setParameter("prefix", prefix)
//            .getSingleResult();
//
//        if (lastNumber == null) {
//            em.createNativeQuery("INSERT INTO id_sequence (prefix, last_number) VALUES (:prefix, 0)")
//                .setParameter("prefix", prefix)
//                .executeUpdate();
//            lastNumber = 0;
//        }
//
//        int newNumber = lastNumber + 1;
//
//        em.createNativeQuery("UPDATE id_sequence SET last_number = :val WHERE prefix = :prefix")
//            .setParameter("val", newNumber)
//            .setParameter("prefix", prefix)
//            .executeUpdate();
//
//        return prefix + String.format("%03d", newNumber);
//    }
}