package com.radix.assessment;

import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.loan.service.LoanService;
import com.radix.assessment.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBase {
    @Autowired
    private LoanService loanService;

    public long createLoan(int term, double amount){
        Loan loan = new Loan();
        loan.setLoanAmount(amount);
        loan.setTerm(term);
        return loanService.creatLoan(loan).getLoanId();
    }

    public Payment createPayment(double amount,long loanId){
        Payment payment = new Payment();
        payment.setPaymentAmount(amount);
        payment.setLoanId(loanId);
        return payment;
    }
}
