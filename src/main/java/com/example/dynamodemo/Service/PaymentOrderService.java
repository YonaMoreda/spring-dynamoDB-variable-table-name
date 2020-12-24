package com.example.dynamodemo.Service;

import com.example.dynamodemo.Model.PaymentOrder;
import com.example.dynamodemo.Repository.PaymentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentOrderService {

    private PaymentOrderRepository repository;

    @Autowired
    public void setRepository(PaymentOrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createPaymentOrder(PaymentOrder paymentOrder) {
        repository.save(paymentOrder);
    }

    public Iterable<PaymentOrder> getPaymentOrders() {
        return repository.findAll();
    }
}
