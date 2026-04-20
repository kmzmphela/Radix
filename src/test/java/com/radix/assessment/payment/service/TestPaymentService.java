package com.radix.assessment.payment.service;

import com.radix.assessment.TestBase;
import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.loan.model.Status;
import com.radix.assessment.payment.model.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;


public class TestPaymentService extends TestBase {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testPayment(){
        long loanId = createLoan(12, 1000);
        Payment payment = createPayment(50, loanId);

        Loan loan = paymentService.makePayment(payment);

        assertEquals(loanId,loan.getLoanId());
        assertEquals(950,loan.getLoanAmount());

    }

    @Test
    public void testOverPay(){
        long loanId = createLoan(12, 1000.0);
        Payment payment = createPayment(50000.0, loanId);

        assertThrows(HttpClientErrorException.class, () -> paymentService.makePayment(payment));

    }

    @Test
    public void testSettlement(){

        long loanId = createLoan(12, 1000);
        Payment payment = createPayment(1000, loanId);

        Loan loan = paymentService.makePayment(payment);

        assertEquals(loanId,loan.getLoanId());
        assertEquals(Status.SETTLED,loan.getStatus());
    }


}
