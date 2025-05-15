package com.sbmtech.mms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbmtech.mms.models.PaymentOrderEntity;
import com.sbmtech.mms.repository.PaymentOrderRepository;

@Service
@Transactional
public class PaymentOrderService {

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

  
    public PaymentOrderEntity createOrder(PaymentOrderEntity paymentOrder) {
        return paymentOrderRepository.save(paymentOrder);
    }
}