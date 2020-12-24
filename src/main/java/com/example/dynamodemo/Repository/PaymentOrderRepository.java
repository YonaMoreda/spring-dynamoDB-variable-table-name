package com.example.dynamodemo.Repository;

import com.example.dynamodemo.Model.PaymentOrder;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableScan
public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, String> {

    Optional<PaymentOrder> findById(String id);
}
