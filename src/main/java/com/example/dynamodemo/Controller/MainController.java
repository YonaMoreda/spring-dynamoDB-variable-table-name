package com.example.dynamodemo.Controller;

import com.example.dynamodemo.Model.PaymentOrder;
import com.example.dynamodemo.Service.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MainController {

    private PaymentOrderService service;

    @Autowired
    public void setService(PaymentOrderService service) {
        this.service = service;
    }

    @PostMapping("/dynamoDB/payment_orders")
    public ResponseEntity<PaymentOrder> insertIntoDynamoDB(@RequestBody PaymentOrder paymentOrder) {
        service.createPaymentOrder(paymentOrder);
        return new ResponseEntity<>(paymentOrder, HttpStatus.OK);
    }

    @GetMapping("/dynamoDB/payment_orders")
    public ResponseEntity<ArrayList<PaymentOrder>> getAllPaymentOrders() {
        ArrayList<PaymentOrder> paymentOrders = new ArrayList<>();
        service.getPaymentOrders().forEach(paymentOrders::add);
        return new ResponseEntity<>(paymentOrders, HttpStatus.OK);
    }
}
