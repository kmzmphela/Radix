package com.radix.assessment.payment.controller;

import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.payment.model.Payment;
import com.radix.assessment.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

 @PostMapping("/payments")
    public Loan payment(@RequestBody Payment payment){
     return paymentService.makePayment(payment);
 }
}
