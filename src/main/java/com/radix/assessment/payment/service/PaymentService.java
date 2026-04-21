package com.radix.assessment.payment.service;

import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.loan.model.Status;
import com.radix.assessment.loan.persistance.LoanRepository;
import com.radix.assessment.payment.model.Payment;
import com.radix.assessment.payment.persistance.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Loan makePayment(Payment payment) throws HttpClientErrorException{
        Loan loanToPay = loanRepository.getReferenceById(payment.getLoanId());
        if(loanToPay.getLoanAmount() >= payment.getPaymentAmount()){
            if(loanToPay.getLoanAmount() == payment.getPaymentAmount()){
                log.info("Loan settled: ",loanToPay.getLoanId());
                loanToPay.setStatus(Status.SETTLED);
                }
            loanToPay.setLoanAmount(loanToPay.getLoanAmount() - payment.getPaymentAmount());
            paymentRepository.save(payment);
             return loanRepository.save(loanToPay);
        }
        HttpClientErrorException httpClientErrorException = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Payment amount greater than Outstanding amount");
        log.error("Cannot make Payment",httpClientErrorException);
        throw httpClientErrorException;
    }
}
